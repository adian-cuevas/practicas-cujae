/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.Error;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.RespuestaExperimentacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eduardo
 */
public class ErrorJpaController implements Serializable {

    public ErrorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Error error) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaExperimentacion respuestaExperimentacionid = error.getRespuestaExperimentacionid();
            if (respuestaExperimentacionid != null) {
                respuestaExperimentacionid = em.getReference(respuestaExperimentacionid.getClass(), respuestaExperimentacionid.getId());
                error.setRespuestaExperimentacionid(respuestaExperimentacionid);
            }
            em.persist(error);
            if (respuestaExperimentacionid != null) {
                respuestaExperimentacionid.getErrorList().add(error);
                respuestaExperimentacionid = em.merge(respuestaExperimentacionid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Error error) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Error persistentError = em.find(Error.class, error.getIdError());
            RespuestaExperimentacion respuestaExperimentacionidOld = persistentError.getRespuestaExperimentacionid();
            RespuestaExperimentacion respuestaExperimentacionidNew = error.getRespuestaExperimentacionid();
            if (respuestaExperimentacionidNew != null) {
                respuestaExperimentacionidNew = em.getReference(respuestaExperimentacionidNew.getClass(), respuestaExperimentacionidNew.getId());
                error.setRespuestaExperimentacionid(respuestaExperimentacionidNew);
            }
            error = em.merge(error);
            if (respuestaExperimentacionidOld != null && !respuestaExperimentacionidOld.equals(respuestaExperimentacionidNew)) {
                respuestaExperimentacionidOld.getErrorList().remove(error);
                respuestaExperimentacionidOld = em.merge(respuestaExperimentacionidOld);
            }
            if (respuestaExperimentacionidNew != null && !respuestaExperimentacionidNew.equals(respuestaExperimentacionidOld)) {
                respuestaExperimentacionidNew.getErrorList().add(error);
                respuestaExperimentacionidNew = em.merge(respuestaExperimentacionidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = error.getIdError();
                if (findError(id) == null) {
                    throw new NonexistentEntityException("The error with id " + id + " no longer exists.");
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
            Error error;
            try {
                error = em.getReference(Error.class, id);
                error.getIdError();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The error with id " + id + " no longer exists.", enfe);
            }
            RespuestaExperimentacion respuestaExperimentacionid = error.getRespuestaExperimentacionid();
            if (respuestaExperimentacionid != null) {
                respuestaExperimentacionid.getErrorList().remove(error);
                respuestaExperimentacionid = em.merge(respuestaExperimentacionid);
            }
            em.remove(error);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Error> findErrorEntities() {
        return findErrorEntities(true, -1, -1);
    }

    public List<Error> findErrorEntities(int maxResults, int firstResult) {
        return findErrorEntities(false, maxResults, firstResult);
    }

    private List<Error> findErrorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Error.class));
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

    public Error findError(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Error.class, id);
        } finally {
            em.close();
        }
    }

    public int getErrorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Error> rt = cq.from(Error.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
