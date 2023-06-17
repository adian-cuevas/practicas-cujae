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
import Modelo.Ejercicio;
import Modelo.Tipoejercicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Eduardo
 */
public class TipoejercicioJpaController implements Serializable {

    public TipoejercicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoejercicio tipoejercicio) {
        if (tipoejercicio.getEjercicioList() == null) {
            tipoejercicio.setEjercicioList(new ArrayList<Ejercicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejercicio> attachedEjercicioList = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListEjercicioToAttach : tipoejercicio.getEjercicioList()) {
                ejercicioListEjercicioToAttach = em.getReference(ejercicioListEjercicioToAttach.getClass(), ejercicioListEjercicioToAttach.getIdejercicio());
                attachedEjercicioList.add(ejercicioListEjercicioToAttach);
            }
            tipoejercicio.setEjercicioList(attachedEjercicioList);
            em.persist(tipoejercicio);
            for (Ejercicio ejercicioListEjercicio : tipoejercicio.getEjercicioList()) {
                Tipoejercicio oldIdtipoejercicioOfEjercicioListEjercicio = ejercicioListEjercicio.getIdtipoejercicio();
                ejercicioListEjercicio.setIdtipoejercicio(tipoejercicio);
                ejercicioListEjercicio = em.merge(ejercicioListEjercicio);
                if (oldIdtipoejercicioOfEjercicioListEjercicio != null) {
                    oldIdtipoejercicioOfEjercicioListEjercicio.getEjercicioList().remove(ejercicioListEjercicio);
                    oldIdtipoejercicioOfEjercicioListEjercicio = em.merge(oldIdtipoejercicioOfEjercicioListEjercicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoejercicio tipoejercicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoejercicio persistentTipoejercicio = em.find(Tipoejercicio.class, tipoejercicio.getIdtipoejercicio());
            List<Ejercicio> ejercicioListOld = persistentTipoejercicio.getEjercicioList();
            List<Ejercicio> ejercicioListNew = tipoejercicio.getEjercicioList();
            List<Ejercicio> attachedEjercicioListNew = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListNewEjercicioToAttach : ejercicioListNew) {
                ejercicioListNewEjercicioToAttach = em.getReference(ejercicioListNewEjercicioToAttach.getClass(), ejercicioListNewEjercicioToAttach.getIdejercicio());
                attachedEjercicioListNew.add(ejercicioListNewEjercicioToAttach);
            }
            ejercicioListNew = attachedEjercicioListNew;
            tipoejercicio.setEjercicioList(ejercicioListNew);
            tipoejercicio = em.merge(tipoejercicio);
            for (Ejercicio ejercicioListOldEjercicio : ejercicioListOld) {
                if (!ejercicioListNew.contains(ejercicioListOldEjercicio)) {
                    ejercicioListOldEjercicio.setIdtipoejercicio(null);
                    ejercicioListOldEjercicio = em.merge(ejercicioListOldEjercicio);
                }
            }
            for (Ejercicio ejercicioListNewEjercicio : ejercicioListNew) {
                if (!ejercicioListOld.contains(ejercicioListNewEjercicio)) {
                    Tipoejercicio oldIdtipoejercicioOfEjercicioListNewEjercicio = ejercicioListNewEjercicio.getIdtipoejercicio();
                    ejercicioListNewEjercicio.setIdtipoejercicio(tipoejercicio);
                    ejercicioListNewEjercicio = em.merge(ejercicioListNewEjercicio);
                    if (oldIdtipoejercicioOfEjercicioListNewEjercicio != null && !oldIdtipoejercicioOfEjercicioListNewEjercicio.equals(tipoejercicio)) {
                        oldIdtipoejercicioOfEjercicioListNewEjercicio.getEjercicioList().remove(ejercicioListNewEjercicio);
                        oldIdtipoejercicioOfEjercicioListNewEjercicio = em.merge(oldIdtipoejercicioOfEjercicioListNewEjercicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoejercicio.getIdtipoejercicio();
                if (findTipoejercicio(id) == null) {
                    throw new NonexistentEntityException("The tipoejercicio with id " + id + " no longer exists.");
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
            Tipoejercicio tipoejercicio;
            try {
                tipoejercicio = em.getReference(Tipoejercicio.class, id);
                tipoejercicio.getIdtipoejercicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoejercicio with id " + id + " no longer exists.", enfe);
            }
            List<Ejercicio> ejercicioList = tipoejercicio.getEjercicioList();
            for (Ejercicio ejercicioListEjercicio : ejercicioList) {
                ejercicioListEjercicio.setIdtipoejercicio(null);
                ejercicioListEjercicio = em.merge(ejercicioListEjercicio);
            }
            em.remove(tipoejercicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoejercicio> findTipoejercicioEntities() {
        return findTipoejercicioEntities(true, -1, -1);
    }

    public List<Tipoejercicio> findTipoejercicioEntities(int maxResults, int firstResult) {
        return findTipoejercicioEntities(false, maxResults, firstResult);
    }

    private List<Tipoejercicio> findTipoejercicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoejercicio.class));
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

    public Tipoejercicio findTipoejercicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoejercicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoejercicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoejercicio> rt = cq.from(Tipoejercicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Tipoejercicio findByName(String name) {
        try {
            EntityManager em = getEntityManager();
            Tipoejercicio a = null;
            a = (Tipoejercicio) em.createNamedQuery("Tipoejercicio.findByNombTipoejerc").setParameter("nombTipoejerc", name).getSingleResult();
            return a;
        } catch (NoResultException ex) {
            return null;
        }

    }

}
