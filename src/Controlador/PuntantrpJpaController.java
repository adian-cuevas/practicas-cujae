/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Puntosteoria;
import Modelo.Dimensiones;
import Modelo.Puntantrp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author adian
 */
public class PuntantrpJpaController implements Serializable {

    public PuntantrpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntantrp puntantrp) {
        if (puntantrp.getDimensionesList() == null) {
            puntantrp.setDimensionesList(new ArrayList<Dimensiones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntosteoria idpuntteoria = puntantrp.getIdpuntteoria();
            if (idpuntteoria != null) {
                idpuntteoria = em.getReference(idpuntteoria.getClass(), idpuntteoria.getIdpuntosteoria());
                puntantrp.setIdpuntteoria(idpuntteoria);
            }
            List<Dimensiones> attachedDimensionesList = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListDimensionesToAttach : puntantrp.getDimensionesList()) {
                dimensionesListDimensionesToAttach = em.getReference(dimensionesListDimensionesToAttach.getClass(), dimensionesListDimensionesToAttach.getIddimensiones());
                attachedDimensionesList.add(dimensionesListDimensionesToAttach);
            }
            puntantrp.setDimensionesList(attachedDimensionesList);
            em.persist(puntantrp);
            if (idpuntteoria != null) {
                idpuntteoria.getPuntantrpList().add(puntantrp);
                idpuntteoria = em.merge(idpuntteoria);
            }
            for (Dimensiones dimensionesListDimensiones : puntantrp.getDimensionesList()) {
                Puntantrp oldIdpuntantropOfDimensionesListDimensiones = dimensionesListDimensiones.getIdpuntantrop();
                dimensionesListDimensiones.setIdpuntantrop(puntantrp);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
                if (oldIdpuntantropOfDimensionesListDimensiones != null) {
                    oldIdpuntantropOfDimensionesListDimensiones.getDimensionesList().remove(dimensionesListDimensiones);
                    oldIdpuntantropOfDimensionesListDimensiones = em.merge(oldIdpuntantropOfDimensionesListDimensiones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntantrp puntantrp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntantrp persistentPuntantrp = em.find(Puntantrp.class, puntantrp.getIdpuntantrp());
            Puntosteoria idpuntteoriaOld = persistentPuntantrp.getIdpuntteoria();
            Puntosteoria idpuntteoriaNew = puntantrp.getIdpuntteoria();
            List<Dimensiones> dimensionesListOld = persistentPuntantrp.getDimensionesList();
            List<Dimensiones> dimensionesListNew = puntantrp.getDimensionesList();
            if (idpuntteoriaNew != null) {
                idpuntteoriaNew = em.getReference(idpuntteoriaNew.getClass(), idpuntteoriaNew.getIdpuntosteoria());
                puntantrp.setIdpuntteoria(idpuntteoriaNew);
            }
            List<Dimensiones> attachedDimensionesListNew = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListNewDimensionesToAttach : dimensionesListNew) {
                dimensionesListNewDimensionesToAttach = em.getReference(dimensionesListNewDimensionesToAttach.getClass(), dimensionesListNewDimensionesToAttach.getIddimensiones());
                attachedDimensionesListNew.add(dimensionesListNewDimensionesToAttach);
            }
            dimensionesListNew = attachedDimensionesListNew;
            puntantrp.setDimensionesList(dimensionesListNew);
            puntantrp = em.merge(puntantrp);
            if (idpuntteoriaOld != null && !idpuntteoriaOld.equals(idpuntteoriaNew)) {
                idpuntteoriaOld.getPuntantrpList().remove(puntantrp);
                idpuntteoriaOld = em.merge(idpuntteoriaOld);
            }
            if (idpuntteoriaNew != null && !idpuntteoriaNew.equals(idpuntteoriaOld)) {
                idpuntteoriaNew.getPuntantrpList().add(puntantrp);
                idpuntteoriaNew = em.merge(idpuntteoriaNew);
            }
            for (Dimensiones dimensionesListOldDimensiones : dimensionesListOld) {
                if (!dimensionesListNew.contains(dimensionesListOldDimensiones)) {
                    dimensionesListOldDimensiones.setIdpuntantrop(null);
                    dimensionesListOldDimensiones = em.merge(dimensionesListOldDimensiones);
                }
            }
            for (Dimensiones dimensionesListNewDimensiones : dimensionesListNew) {
                if (!dimensionesListOld.contains(dimensionesListNewDimensiones)) {
                    Puntantrp oldIdpuntantropOfDimensionesListNewDimensiones = dimensionesListNewDimensiones.getIdpuntantrop();
                    dimensionesListNewDimensiones.setIdpuntantrop(puntantrp);
                    dimensionesListNewDimensiones = em.merge(dimensionesListNewDimensiones);
                    if (oldIdpuntantropOfDimensionesListNewDimensiones != null && !oldIdpuntantropOfDimensionesListNewDimensiones.equals(puntantrp)) {
                        oldIdpuntantropOfDimensionesListNewDimensiones.getDimensionesList().remove(dimensionesListNewDimensiones);
                        oldIdpuntantropOfDimensionesListNewDimensiones = em.merge(oldIdpuntantropOfDimensionesListNewDimensiones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = puntantrp.getIdpuntantrp();
                if (findPuntantrp(id) == null) {
                    throw new NonexistentEntityException("The puntantrp with id " + id + " no longer exists.");
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
            Puntantrp puntantrp;
            try {
                puntantrp = em.getReference(Puntantrp.class, id);
                puntantrp.getIdpuntantrp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puntantrp with id " + id + " no longer exists.", enfe);
            }
            Puntosteoria idpuntteoria = puntantrp.getIdpuntteoria();
            if (idpuntteoria != null) {
                idpuntteoria.getPuntantrpList().remove(puntantrp);
                idpuntteoria = em.merge(idpuntteoria);
            }
            List<Dimensiones> dimensionesList = puntantrp.getDimensionesList();
            for (Dimensiones dimensionesListDimensiones : dimensionesList) {
                dimensionesListDimensiones.setIdpuntantrop(null);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
            }
            em.remove(puntantrp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntantrp> findPuntantrpEntities() {
        return findPuntantrpEntities(true, -1, -1);
    }

    public List<Puntantrp> findPuntantrpEntities(int maxResults, int firstResult) {
        return findPuntantrpEntities(false, maxResults, firstResult);
    }

    private List<Puntantrp> findPuntantrpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntantrp.class));
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

    public Puntantrp findPuntantrp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntantrp.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntantrpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntantrp> rt = cq.from(Puntantrp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
