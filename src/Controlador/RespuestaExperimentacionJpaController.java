/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Usuario;
import Modelo.Experimentacion;
import Modelo.Error;
import Modelo.RespuestaExperimentacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eduardo
 */
public class RespuestaExperimentacionJpaController implements Serializable {

    public RespuestaExperimentacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaExperimentacion respuestaExperimentacion) {
        if (respuestaExperimentacion.getErrorList() == null) {
            respuestaExperimentacion.setErrorList(new ArrayList<Error>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioidusuario = respuestaExperimentacion.getUsuarioidusuario();
            if (usuarioidusuario != null) {
                usuarioidusuario = em.getReference(usuarioidusuario.getClass(), usuarioidusuario.getIdusuario());
                respuestaExperimentacion.setUsuarioidusuario(usuarioidusuario);
            }
            Experimentacion experimentacionidExperimentacion = respuestaExperimentacion.getExperimentacionidExperimentacion();
            if (experimentacionidExperimentacion != null) {
                experimentacionidExperimentacion = em.getReference(experimentacionidExperimentacion.getClass(), experimentacionidExperimentacion.getIdExperimentacion());
                respuestaExperimentacion.setExperimentacionidExperimentacion(experimentacionidExperimentacion);
            }
            List<Error> attachedErrorList = new ArrayList<Error>();
            for (Error errorListErrorToAttach : respuestaExperimentacion.getErrorList()) {
                errorListErrorToAttach = em.getReference(errorListErrorToAttach.getClass(), errorListErrorToAttach.getIdError());
                attachedErrorList.add(errorListErrorToAttach);
            }
            respuestaExperimentacion.setErrorList(attachedErrorList);
            em.persist(respuestaExperimentacion);
            if (usuarioidusuario != null) {
                usuarioidusuario.getRespuestaExperimentacionList().add(respuestaExperimentacion);
                usuarioidusuario = em.merge(usuarioidusuario);
            }
            if (experimentacionidExperimentacion != null) {
                experimentacionidExperimentacion.getRespuestaExperimentacionList().add(respuestaExperimentacion);
                experimentacionidExperimentacion = em.merge(experimentacionidExperimentacion);
            }
            for (Error errorListError : respuestaExperimentacion.getErrorList()) {
                RespuestaExperimentacion oldRespuestaExperimentacionidOfErrorListError = errorListError.getRespuestaExperimentacionid();
                errorListError.setRespuestaExperimentacionid(respuestaExperimentacion);
                errorListError = em.merge(errorListError);
                if (oldRespuestaExperimentacionidOfErrorListError != null) {
                    oldRespuestaExperimentacionidOfErrorListError.getErrorList().remove(errorListError);
                    oldRespuestaExperimentacionidOfErrorListError = em.merge(oldRespuestaExperimentacionidOfErrorListError);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaExperimentacion respuestaExperimentacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaExperimentacion persistentRespuestaExperimentacion = em.find(RespuestaExperimentacion.class, respuestaExperimentacion.getId());
            Usuario usuarioidusuarioOld = persistentRespuestaExperimentacion.getUsuarioidusuario();
            Usuario usuarioidusuarioNew = respuestaExperimentacion.getUsuarioidusuario();
            Experimentacion experimentacionidExperimentacionOld = persistentRespuestaExperimentacion.getExperimentacionidExperimentacion();
            Experimentacion experimentacionidExperimentacionNew = respuestaExperimentacion.getExperimentacionidExperimentacion();
            List<Error> errorListOld = persistentRespuestaExperimentacion.getErrorList();
            List<Error> errorListNew = respuestaExperimentacion.getErrorList();
            List<String> illegalOrphanMessages = null;
            for (Error errorListOldError : errorListOld) {
                if (!errorListNew.contains(errorListOldError)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Error " + errorListOldError + " since its respuestaExperimentacionid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioidusuarioNew != null) {
                usuarioidusuarioNew = em.getReference(usuarioidusuarioNew.getClass(), usuarioidusuarioNew.getIdusuario());
                respuestaExperimentacion.setUsuarioidusuario(usuarioidusuarioNew);
            }
            if (experimentacionidExperimentacionNew != null) {
                experimentacionidExperimentacionNew = em.getReference(experimentacionidExperimentacionNew.getClass(), experimentacionidExperimentacionNew.getIdExperimentacion());
                respuestaExperimentacion.setExperimentacionidExperimentacion(experimentacionidExperimentacionNew);
            }
            List<Error> attachedErrorListNew = new ArrayList<Error>();
            for (Error errorListNewErrorToAttach : errorListNew) {
                errorListNewErrorToAttach = em.getReference(errorListNewErrorToAttach.getClass(), errorListNewErrorToAttach.getIdError());
                attachedErrorListNew.add(errorListNewErrorToAttach);
            }
            errorListNew = attachedErrorListNew;
            respuestaExperimentacion.setErrorList(errorListNew);
            respuestaExperimentacion = em.merge(respuestaExperimentacion);
            if (usuarioidusuarioOld != null && !usuarioidusuarioOld.equals(usuarioidusuarioNew)) {
                usuarioidusuarioOld.getRespuestaExperimentacionList().remove(respuestaExperimentacion);
                usuarioidusuarioOld = em.merge(usuarioidusuarioOld);
            }
            if (usuarioidusuarioNew != null && !usuarioidusuarioNew.equals(usuarioidusuarioOld)) {
                usuarioidusuarioNew.getRespuestaExperimentacionList().add(respuestaExperimentacion);
                usuarioidusuarioNew = em.merge(usuarioidusuarioNew);
            }
            if (experimentacionidExperimentacionOld != null && !experimentacionidExperimentacionOld.equals(experimentacionidExperimentacionNew)) {
                experimentacionidExperimentacionOld.getRespuestaExperimentacionList().remove(respuestaExperimentacion);
                experimentacionidExperimentacionOld = em.merge(experimentacionidExperimentacionOld);
            }
            if (experimentacionidExperimentacionNew != null && !experimentacionidExperimentacionNew.equals(experimentacionidExperimentacionOld)) {
                experimentacionidExperimentacionNew.getRespuestaExperimentacionList().add(respuestaExperimentacion);
                experimentacionidExperimentacionNew = em.merge(experimentacionidExperimentacionNew);
            }
            for (Error errorListNewError : errorListNew) {
                if (!errorListOld.contains(errorListNewError)) {
                    RespuestaExperimentacion oldRespuestaExperimentacionidOfErrorListNewError = errorListNewError.getRespuestaExperimentacionid();
                    errorListNewError.setRespuestaExperimentacionid(respuestaExperimentacion);
                    errorListNewError = em.merge(errorListNewError);
                    if (oldRespuestaExperimentacionidOfErrorListNewError != null && !oldRespuestaExperimentacionidOfErrorListNewError.equals(respuestaExperimentacion)) {
                        oldRespuestaExperimentacionidOfErrorListNewError.getErrorList().remove(errorListNewError);
                        oldRespuestaExperimentacionidOfErrorListNewError = em.merge(oldRespuestaExperimentacionidOfErrorListNewError);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestaExperimentacion.getId();
                if (findRespuestaExperimentacion(id) == null) {
                    throw new NonexistentEntityException("The respuestaExperimentacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RespuestaExperimentacion respuestaExperimentacion;
            try {
                respuestaExperimentacion = em.getReference(RespuestaExperimentacion.class, id);
                respuestaExperimentacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaExperimentacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Error> errorListOrphanCheck = respuestaExperimentacion.getErrorList();
            for (Error errorListOrphanCheckError : errorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RespuestaExperimentacion (" + respuestaExperimentacion + ") cannot be destroyed since the Error " + errorListOrphanCheckError + " in its errorList field has a non-nullable respuestaExperimentacionid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioidusuario = respuestaExperimentacion.getUsuarioidusuario();
            if (usuarioidusuario != null) {
                usuarioidusuario.getRespuestaExperimentacionList().remove(respuestaExperimentacion);
                usuarioidusuario = em.merge(usuarioidusuario);
            }
            Experimentacion experimentacionidExperimentacion = respuestaExperimentacion.getExperimentacionidExperimentacion();
            if (experimentacionidExperimentacion != null) {
                experimentacionidExperimentacion.getRespuestaExperimentacionList().remove(respuestaExperimentacion);
                experimentacionidExperimentacion = em.merge(experimentacionidExperimentacion);
            }
            em.remove(respuestaExperimentacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaExperimentacion> findRespuestaExperimentacionEntities() {
        return findRespuestaExperimentacionEntities(true, -1, -1);
    }

    public List<RespuestaExperimentacion> findRespuestaExperimentacionEntities(int maxResults, int firstResult) {
        return findRespuestaExperimentacionEntities(false, maxResults, firstResult);
    }

    private List<RespuestaExperimentacion> findRespuestaExperimentacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RespuestaExperimentacion.class));
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

    public RespuestaExperimentacion findRespuestaExperimentacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaExperimentacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaExperimentacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RespuestaExperimentacion> rt = cq.from(RespuestaExperimentacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
