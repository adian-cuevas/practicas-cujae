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
import Modelo.Experimentacion;
import Modelo.Posicionespuestotrabajo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Eduardo
 */
public class PosicionespuestotrabajoJpaController implements Serializable {

    public PosicionespuestotrabajoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Posicionespuestotrabajo posicionespuestotrabajo) {
        if (posicionespuestotrabajo.getExperimentacionList() == null) {
            posicionespuestotrabajo.setExperimentacionList(new ArrayList<Experimentacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Experimentacion> attachedExperimentacionList = new ArrayList<Experimentacion>();
            for (Experimentacion experimentacionListExperimentacionToAttach : posicionespuestotrabajo.getExperimentacionList()) {
                experimentacionListExperimentacionToAttach = em.getReference(experimentacionListExperimentacionToAttach.getClass(), experimentacionListExperimentacionToAttach.getIdExperimentacion());
                attachedExperimentacionList.add(experimentacionListExperimentacionToAttach);
            }
            posicionespuestotrabajo.setExperimentacionList(attachedExperimentacionList);
            em.persist(posicionespuestotrabajo);
            for (Experimentacion experimentacionListExperimentacion : posicionespuestotrabajo.getExperimentacionList()) {
                experimentacionListExperimentacion.getPosicionespuestotrabajoList().add(posicionespuestotrabajo);
                experimentacionListExperimentacion = em.merge(experimentacionListExperimentacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Posicionespuestotrabajo posicionespuestotrabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Posicionespuestotrabajo persistentPosicionespuestotrabajo = em.find(Posicionespuestotrabajo.class, posicionespuestotrabajo.getIdposicionespuesto());
            List<Experimentacion> experimentacionListOld = persistentPosicionespuestotrabajo.getExperimentacionList();
            List<Experimentacion> experimentacionListNew = posicionespuestotrabajo.getExperimentacionList();
            List<Experimentacion> attachedExperimentacionListNew = new ArrayList<Experimentacion>();
            for (Experimentacion experimentacionListNewExperimentacionToAttach : experimentacionListNew) {
                experimentacionListNewExperimentacionToAttach = em.getReference(experimentacionListNewExperimentacionToAttach.getClass(), experimentacionListNewExperimentacionToAttach.getIdExperimentacion());
                attachedExperimentacionListNew.add(experimentacionListNewExperimentacionToAttach);
            }
            experimentacionListNew = attachedExperimentacionListNew;
            posicionespuestotrabajo.setExperimentacionList(experimentacionListNew);
            posicionespuestotrabajo = em.merge(posicionespuestotrabajo);
            for (Experimentacion experimentacionListOldExperimentacion : experimentacionListOld) {
                if (!experimentacionListNew.contains(experimentacionListOldExperimentacion)) {
                    experimentacionListOldExperimentacion.getPosicionespuestotrabajoList().remove(posicionespuestotrabajo);
                    experimentacionListOldExperimentacion = em.merge(experimentacionListOldExperimentacion);
                }
            }
            for (Experimentacion experimentacionListNewExperimentacion : experimentacionListNew) {
                if (!experimentacionListOld.contains(experimentacionListNewExperimentacion)) {
                    experimentacionListNewExperimentacion.getPosicionespuestotrabajoList().add(posicionespuestotrabajo);
                    experimentacionListNewExperimentacion = em.merge(experimentacionListNewExperimentacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = posicionespuestotrabajo.getIdposicionespuesto();
                if (findPosicionespuestotrabajo(id) == null) {
                    throw new NonexistentEntityException("The posicionespuestotrabajo with id " + id + " no longer exists.");
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
            Posicionespuestotrabajo posicionespuestotrabajo;
            try {
                posicionespuestotrabajo = em.getReference(Posicionespuestotrabajo.class, id);
                posicionespuestotrabajo.getIdposicionespuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The posicionespuestotrabajo with id " + id + " no longer exists.", enfe);
            }
            List<Experimentacion> experimentacionList = posicionespuestotrabajo.getExperimentacionList();
            for (Experimentacion experimentacionListExperimentacion : experimentacionList) {
                experimentacionListExperimentacion.getPosicionespuestotrabajoList().remove(posicionespuestotrabajo);
                experimentacionListExperimentacion = em.merge(experimentacionListExperimentacion);
            }
            em.remove(posicionespuestotrabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Posicionespuestotrabajo> findPosicionespuestotrabajoEntities() {
        return findPosicionespuestotrabajoEntities(true, -1, -1);
    }

    public List<Posicionespuestotrabajo> findPosicionespuestotrabajoEntities(int maxResults, int firstResult) {
        return findPosicionespuestotrabajoEntities(false, maxResults, firstResult);
    }

    private List<Posicionespuestotrabajo> findPosicionespuestotrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Posicionespuestotrabajo.class));
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

    public Posicionespuestotrabajo findPosicionespuestotrabajo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Posicionespuestotrabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPosicionespuestotrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Posicionespuestotrabajo> rt = cq.from(Posicionespuestotrabajo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Posicionespuestotrabajo findByName(String name) {
        try {
            EntityManager em = getEntityManager();
            Posicionespuestotrabajo a = null;
            a = (Posicionespuestotrabajo) em.createNamedQuery("Posicionespuestotrabajo.findByDescripcion").setParameter("descripcion", name).getSingleResult();
            return a;
        } catch (NoResultException ex) {
            return null;
        }

    }
}
