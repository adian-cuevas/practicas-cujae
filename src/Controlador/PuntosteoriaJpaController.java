/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Puntantrp;
import Modelo.Puntosteoria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author adian
 */
public class PuntosteoriaJpaController implements Serializable {

    public PuntosteoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntosteoria puntosteoria) throws PreexistingEntityException, Exception {
        if (puntosteoria.getPuntantrpList() == null) {
            puntosteoria.setPuntantrpList(new ArrayList<Puntantrp>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Puntantrp> attachedPuntantrpList = new ArrayList<Puntantrp>();
            for (Puntantrp puntantrpListPuntantrpToAttach : puntosteoria.getPuntantrpList()) {
                puntantrpListPuntantrpToAttach = em.getReference(puntantrpListPuntantrpToAttach.getClass(), puntantrpListPuntantrpToAttach.getIdpuntantrp());
                attachedPuntantrpList.add(puntantrpListPuntantrpToAttach);
            }
            puntosteoria.setPuntantrpList(attachedPuntantrpList);
            em.persist(puntosteoria);
            for (Puntantrp puntantrpListPuntantrp : puntosteoria.getPuntantrpList()) {
                Puntosteoria oldIdpuntteoriaOfPuntantrpListPuntantrp = puntantrpListPuntantrp.getIdpuntteoria();
                puntantrpListPuntantrp.setIdpuntteoria(puntosteoria);
                puntantrpListPuntantrp = em.merge(puntantrpListPuntantrp);
                if (oldIdpuntteoriaOfPuntantrpListPuntantrp != null) {
                    oldIdpuntteoriaOfPuntantrpListPuntantrp.getPuntantrpList().remove(puntantrpListPuntantrp);
                    oldIdpuntteoriaOfPuntantrpListPuntantrp = em.merge(oldIdpuntteoriaOfPuntantrpListPuntantrp);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntosteoria(puntosteoria.getIdpuntosteoria()) != null) {
                throw new PreexistingEntityException("Puntosteoria " + puntosteoria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntosteoria puntosteoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntosteoria persistentPuntosteoria = em.find(Puntosteoria.class, puntosteoria.getIdpuntosteoria());
            List<Puntantrp> puntantrpListOld = persistentPuntosteoria.getPuntantrpList();
            List<Puntantrp> puntantrpListNew = puntosteoria.getPuntantrpList();
            List<Puntantrp> attachedPuntantrpListNew = new ArrayList<Puntantrp>();
            for (Puntantrp puntantrpListNewPuntantrpToAttach : puntantrpListNew) {
                puntantrpListNewPuntantrpToAttach = em.getReference(puntantrpListNewPuntantrpToAttach.getClass(), puntantrpListNewPuntantrpToAttach.getIdpuntantrp());
                attachedPuntantrpListNew.add(puntantrpListNewPuntantrpToAttach);
            }
            puntantrpListNew = attachedPuntantrpListNew;
            puntosteoria.setPuntantrpList(puntantrpListNew);
            puntosteoria = em.merge(puntosteoria);
            for (Puntantrp puntantrpListOldPuntantrp : puntantrpListOld) {
                if (!puntantrpListNew.contains(puntantrpListOldPuntantrp)) {
                    puntantrpListOldPuntantrp.setIdpuntteoria(null);
                    puntantrpListOldPuntantrp = em.merge(puntantrpListOldPuntantrp);
                }
            }
            for (Puntantrp puntantrpListNewPuntantrp : puntantrpListNew) {
                if (!puntantrpListOld.contains(puntantrpListNewPuntantrp)) {
                    Puntosteoria oldIdpuntteoriaOfPuntantrpListNewPuntantrp = puntantrpListNewPuntantrp.getIdpuntteoria();
                    puntantrpListNewPuntantrp.setIdpuntteoria(puntosteoria);
                    puntantrpListNewPuntantrp = em.merge(puntantrpListNewPuntantrp);
                    if (oldIdpuntteoriaOfPuntantrpListNewPuntantrp != null && !oldIdpuntteoriaOfPuntantrpListNewPuntantrp.equals(puntosteoria)) {
                        oldIdpuntteoriaOfPuntantrpListNewPuntantrp.getPuntantrpList().remove(puntantrpListNewPuntantrp);
                        oldIdpuntteoriaOfPuntantrpListNewPuntantrp = em.merge(oldIdpuntteoriaOfPuntantrpListNewPuntantrp);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = puntosteoria.getIdpuntosteoria();
                if (findPuntosteoria(id) == null) {
                    throw new NonexistentEntityException("The puntosteoria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntosteoria puntosteoria;
            try {
                puntosteoria = em.getReference(Puntosteoria.class, id);
                puntosteoria.getIdpuntosteoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puntosteoria with id " + id + " no longer exists.", enfe);
            }
            List<Puntantrp> puntantrpList = puntosteoria.getPuntantrpList();
            for (Puntantrp puntantrpListPuntantrp : puntantrpList) {
                puntantrpListPuntantrp.setIdpuntteoria(null);
                puntantrpListPuntantrp = em.merge(puntantrpListPuntantrp);
            }
            em.remove(puntosteoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntosteoria> findPuntosteoriaEntities() {
        return findPuntosteoriaEntities(true, -1, -1);
    }

    public List<Puntosteoria> findPuntosteoriaEntities(int maxResults, int firstResult) {
        return findPuntosteoriaEntities(false, maxResults, firstResult);
    }

    private List<Puntosteoria> findPuntosteoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntosteoria.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Puntosteoria findPuntosteoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntosteoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntosteoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntosteoria> rt = cq.from(Puntosteoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
