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
import Modelo.Posicionespuestotrabajo;
import java.util.ArrayList;
import java.util.List;
import Modelo.DimensionesRelevantes;
import Modelo.Experimentacion;
import Modelo.RespuestaExperimentacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Eduardo
 */
public class ExperimentacionJpaController implements Serializable {

    public ExperimentacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Experimentacion experimentacion) {
        if (experimentacion.getPosicionespuestotrabajoList() == null) {
            experimentacion.setPosicionespuestotrabajoList(new ArrayList<Posicionespuestotrabajo>());
        }
        if (experimentacion.getDimensionesRelevantesList() == null) {
            experimentacion.setDimensionesRelevantesList(new ArrayList<DimensionesRelevantes>());
        }
        if (experimentacion.getRespuestaExperimentacionList() == null) {
            experimentacion.setRespuestaExperimentacionList(new ArrayList<RespuestaExperimentacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Posicionespuestotrabajo> attachedPosicionespuestotrabajoList = new ArrayList<Posicionespuestotrabajo>();
            for (Posicionespuestotrabajo posicionespuestotrabajoListPosicionespuestotrabajoToAttach : experimentacion.getPosicionespuestotrabajoList()) {
                posicionespuestotrabajoListPosicionespuestotrabajoToAttach = em.getReference(posicionespuestotrabajoListPosicionespuestotrabajoToAttach.getClass(), posicionespuestotrabajoListPosicionespuestotrabajoToAttach.getIdposicionespuesto());
                attachedPosicionespuestotrabajoList.add(posicionespuestotrabajoListPosicionespuestotrabajoToAttach);
            }
            experimentacion.setPosicionespuestotrabajoList(attachedPosicionespuestotrabajoList);
            List<DimensionesRelevantes> attachedDimensionesRelevantesList = new ArrayList<DimensionesRelevantes>();
            for (DimensionesRelevantes dimensionesRelevantesListDimensionesRelevantesToAttach : experimentacion.getDimensionesRelevantesList()) {
                dimensionesRelevantesListDimensionesRelevantesToAttach = em.getReference(dimensionesRelevantesListDimensionesRelevantesToAttach.getClass(), dimensionesRelevantesListDimensionesRelevantesToAttach.getIddimensionesRelevantes());
                attachedDimensionesRelevantesList.add(dimensionesRelevantesListDimensionesRelevantesToAttach);
            }
            experimentacion.setDimensionesRelevantesList(attachedDimensionesRelevantesList);
            List<RespuestaExperimentacion> attachedRespuestaExperimentacionList = new ArrayList<RespuestaExperimentacion>();
            for (RespuestaExperimentacion respuestaExperimentacionListRespuestaExperimentacionToAttach : experimentacion.getRespuestaExperimentacionList()) {
                respuestaExperimentacionListRespuestaExperimentacionToAttach = em.getReference(respuestaExperimentacionListRespuestaExperimentacionToAttach.getClass(), respuestaExperimentacionListRespuestaExperimentacionToAttach.getId());
                attachedRespuestaExperimentacionList.add(respuestaExperimentacionListRespuestaExperimentacionToAttach);
            }
            experimentacion.setRespuestaExperimentacionList(attachedRespuestaExperimentacionList);
            em.persist(experimentacion);
            for (Posicionespuestotrabajo posicionespuestotrabajoListPosicionespuestotrabajo : experimentacion.getPosicionespuestotrabajoList()) {
                posicionespuestotrabajoListPosicionespuestotrabajo.getExperimentacionList().add(experimentacion);
                posicionespuestotrabajoListPosicionespuestotrabajo = em.merge(posicionespuestotrabajoListPosicionespuestotrabajo);
            }
            for (DimensionesRelevantes dimensionesRelevantesListDimensionesRelevantes : experimentacion.getDimensionesRelevantesList()) {
                dimensionesRelevantesListDimensionesRelevantes.getExperimentacionList().add(experimentacion);
                dimensionesRelevantesListDimensionesRelevantes = em.merge(dimensionesRelevantesListDimensionesRelevantes);
            }
            for (RespuestaExperimentacion respuestaExperimentacionListRespuestaExperimentacion : experimentacion.getRespuestaExperimentacionList()) {
                Experimentacion oldExperimentacionidExperimentacionOfRespuestaExperimentacionListRespuestaExperimentacion = respuestaExperimentacionListRespuestaExperimentacion.getExperimentacionidExperimentacion();
                respuestaExperimentacionListRespuestaExperimentacion.setExperimentacionidExperimentacion(experimentacion);
                respuestaExperimentacionListRespuestaExperimentacion = em.merge(respuestaExperimentacionListRespuestaExperimentacion);
                if (oldExperimentacionidExperimentacionOfRespuestaExperimentacionListRespuestaExperimentacion != null) {
                    oldExperimentacionidExperimentacionOfRespuestaExperimentacionListRespuestaExperimentacion.getRespuestaExperimentacionList().remove(respuestaExperimentacionListRespuestaExperimentacion);
                    oldExperimentacionidExperimentacionOfRespuestaExperimentacionListRespuestaExperimentacion = em.merge(oldExperimentacionidExperimentacionOfRespuestaExperimentacionListRespuestaExperimentacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Experimentacion experimentacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Experimentacion persistentExperimentacion = em.find(Experimentacion.class, experimentacion.getIdExperimentacion());
            List<Posicionespuestotrabajo> posicionespuestotrabajoListOld = persistentExperimentacion.getPosicionespuestotrabajoList();
            List<Posicionespuestotrabajo> posicionespuestotrabajoListNew = experimentacion.getPosicionespuestotrabajoList();
            List<DimensionesRelevantes> dimensionesRelevantesListOld = persistentExperimentacion.getDimensionesRelevantesList();
            List<DimensionesRelevantes> dimensionesRelevantesListNew = experimentacion.getDimensionesRelevantesList();
            List<RespuestaExperimentacion> respuestaExperimentacionListOld = persistentExperimentacion.getRespuestaExperimentacionList();
            List<RespuestaExperimentacion> respuestaExperimentacionListNew = experimentacion.getRespuestaExperimentacionList();
            List<String> illegalOrphanMessages = null;
            for (RespuestaExperimentacion respuestaExperimentacionListOldRespuestaExperimentacion : respuestaExperimentacionListOld) {
                if (!respuestaExperimentacionListNew.contains(respuestaExperimentacionListOldRespuestaExperimentacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RespuestaExperimentacion " + respuestaExperimentacionListOldRespuestaExperimentacion + " since its experimentacionidExperimentacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Posicionespuestotrabajo> attachedPosicionespuestotrabajoListNew = new ArrayList<Posicionespuestotrabajo>();
            for (Posicionespuestotrabajo posicionespuestotrabajoListNewPosicionespuestotrabajoToAttach : posicionespuestotrabajoListNew) {
                posicionespuestotrabajoListNewPosicionespuestotrabajoToAttach = em.getReference(posicionespuestotrabajoListNewPosicionespuestotrabajoToAttach.getClass(), posicionespuestotrabajoListNewPosicionespuestotrabajoToAttach.getIdposicionespuesto());
                attachedPosicionespuestotrabajoListNew.add(posicionespuestotrabajoListNewPosicionespuestotrabajoToAttach);
            }
            posicionespuestotrabajoListNew = attachedPosicionespuestotrabajoListNew;
            experimentacion.setPosicionespuestotrabajoList(posicionespuestotrabajoListNew);
            List<DimensionesRelevantes> attachedDimensionesRelevantesListNew = new ArrayList<DimensionesRelevantes>();
            for (DimensionesRelevantes dimensionesRelevantesListNewDimensionesRelevantesToAttach : dimensionesRelevantesListNew) {
                dimensionesRelevantesListNewDimensionesRelevantesToAttach = em.getReference(dimensionesRelevantesListNewDimensionesRelevantesToAttach.getClass(), dimensionesRelevantesListNewDimensionesRelevantesToAttach.getIddimensionesRelevantes());
                attachedDimensionesRelevantesListNew.add(dimensionesRelevantesListNewDimensionesRelevantesToAttach);
            }
            dimensionesRelevantesListNew = attachedDimensionesRelevantesListNew;
            experimentacion.setDimensionesRelevantesList(dimensionesRelevantesListNew);
            List<RespuestaExperimentacion> attachedRespuestaExperimentacionListNew = new ArrayList<RespuestaExperimentacion>();
            for (RespuestaExperimentacion respuestaExperimentacionListNewRespuestaExperimentacionToAttach : respuestaExperimentacionListNew) {
                respuestaExperimentacionListNewRespuestaExperimentacionToAttach = em.getReference(respuestaExperimentacionListNewRespuestaExperimentacionToAttach.getClass(), respuestaExperimentacionListNewRespuestaExperimentacionToAttach.getId());
                attachedRespuestaExperimentacionListNew.add(respuestaExperimentacionListNewRespuestaExperimentacionToAttach);
            }
            respuestaExperimentacionListNew = attachedRespuestaExperimentacionListNew;
            experimentacion.setRespuestaExperimentacionList(respuestaExperimentacionListNew);
            experimentacion = em.merge(experimentacion);
            for (Posicionespuestotrabajo posicionespuestotrabajoListOldPosicionespuestotrabajo : posicionespuestotrabajoListOld) {
                if (!posicionespuestotrabajoListNew.contains(posicionespuestotrabajoListOldPosicionespuestotrabajo)) {
                    posicionespuestotrabajoListOldPosicionespuestotrabajo.getExperimentacionList().remove(experimentacion);
                    posicionespuestotrabajoListOldPosicionespuestotrabajo = em.merge(posicionespuestotrabajoListOldPosicionespuestotrabajo);
                }
            }
            for (Posicionespuestotrabajo posicionespuestotrabajoListNewPosicionespuestotrabajo : posicionespuestotrabajoListNew) {
                if (!posicionespuestotrabajoListOld.contains(posicionespuestotrabajoListNewPosicionespuestotrabajo)) {
                    posicionespuestotrabajoListNewPosicionespuestotrabajo.getExperimentacionList().add(experimentacion);
                    posicionespuestotrabajoListNewPosicionespuestotrabajo = em.merge(posicionespuestotrabajoListNewPosicionespuestotrabajo);
                }
            }
            for (DimensionesRelevantes dimensionesRelevantesListOldDimensionesRelevantes : dimensionesRelevantesListOld) {
                if (!dimensionesRelevantesListNew.contains(dimensionesRelevantesListOldDimensionesRelevantes)) {
                    dimensionesRelevantesListOldDimensionesRelevantes.getExperimentacionList().remove(experimentacion);
                    dimensionesRelevantesListOldDimensionesRelevantes = em.merge(dimensionesRelevantesListOldDimensionesRelevantes);
                }
            }
            for (DimensionesRelevantes dimensionesRelevantesListNewDimensionesRelevantes : dimensionesRelevantesListNew) {
                if (!dimensionesRelevantesListOld.contains(dimensionesRelevantesListNewDimensionesRelevantes)) {
                    dimensionesRelevantesListNewDimensionesRelevantes.getExperimentacionList().add(experimentacion);
                    dimensionesRelevantesListNewDimensionesRelevantes = em.merge(dimensionesRelevantesListNewDimensionesRelevantes);
                }
            }
            for (RespuestaExperimentacion respuestaExperimentacionListNewRespuestaExperimentacion : respuestaExperimentacionListNew) {
                if (!respuestaExperimentacionListOld.contains(respuestaExperimentacionListNewRespuestaExperimentacion)) {
                    Experimentacion oldExperimentacionidExperimentacionOfRespuestaExperimentacionListNewRespuestaExperimentacion = respuestaExperimentacionListNewRespuestaExperimentacion.getExperimentacionidExperimentacion();
                    respuestaExperimentacionListNewRespuestaExperimentacion.setExperimentacionidExperimentacion(experimentacion);
                    respuestaExperimentacionListNewRespuestaExperimentacion = em.merge(respuestaExperimentacionListNewRespuestaExperimentacion);
                    if (oldExperimentacionidExperimentacionOfRespuestaExperimentacionListNewRespuestaExperimentacion != null && !oldExperimentacionidExperimentacionOfRespuestaExperimentacionListNewRespuestaExperimentacion.equals(experimentacion)) {
                        oldExperimentacionidExperimentacionOfRespuestaExperimentacionListNewRespuestaExperimentacion.getRespuestaExperimentacionList().remove(respuestaExperimentacionListNewRespuestaExperimentacion);
                        oldExperimentacionidExperimentacionOfRespuestaExperimentacionListNewRespuestaExperimentacion = em.merge(oldExperimentacionidExperimentacionOfRespuestaExperimentacionListNewRespuestaExperimentacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = experimentacion.getIdExperimentacion();
                if (findExperimentacion(id) == null) {
                    throw new NonexistentEntityException("The experimentacion with id " + id + " no longer exists.");
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
            Experimentacion experimentacion;
            try {
                experimentacion = em.getReference(Experimentacion.class, id);
                experimentacion.getIdExperimentacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The experimentacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RespuestaExperimentacion> respuestaExperimentacionListOrphanCheck = experimentacion.getRespuestaExperimentacionList();
            for (RespuestaExperimentacion respuestaExperimentacionListOrphanCheckRespuestaExperimentacion : respuestaExperimentacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Experimentacion (" + experimentacion + ") cannot be destroyed since the RespuestaExperimentacion " + respuestaExperimentacionListOrphanCheckRespuestaExperimentacion + " in its respuestaExperimentacionList field has a non-nullable experimentacionidExperimentacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Posicionespuestotrabajo> posicionespuestotrabajoList = experimentacion.getPosicionespuestotrabajoList();
            for (Posicionespuestotrabajo posicionespuestotrabajoListPosicionespuestotrabajo : posicionespuestotrabajoList) {
                posicionespuestotrabajoListPosicionespuestotrabajo.getExperimentacionList().remove(experimentacion);
                posicionespuestotrabajoListPosicionespuestotrabajo = em.merge(posicionespuestotrabajoListPosicionespuestotrabajo);
            }
            List<DimensionesRelevantes> dimensionesRelevantesList = experimentacion.getDimensionesRelevantesList();
            for (DimensionesRelevantes dimensionesRelevantesListDimensionesRelevantes : dimensionesRelevantesList) {
                dimensionesRelevantesListDimensionesRelevantes.getExperimentacionList().remove(experimentacion);
                dimensionesRelevantesListDimensionesRelevantes = em.merge(dimensionesRelevantesListDimensionesRelevantes);
            }
            em.remove(experimentacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Experimentacion> findExperimentacionEntities() {
        return findExperimentacionEntities(true, -1, -1);
    }

    public List<Experimentacion> findExperimentacionEntities(int maxResults, int firstResult) {
        return findExperimentacionEntities(false, maxResults, firstResult);
    }

    private List<Experimentacion> findExperimentacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Experimentacion.class));
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

    public Experimentacion findExperimentacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Experimentacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getExperimentacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Experimentacion> rt = cq.from(Experimentacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Experimentacion Mostrar(String nomb) {
        try {
            Experimentacion experim;
            EntityManager em = getEntityManager();
            experim = (Experimentacion) em.createNamedQuery("Experimentacion.findByNombre").setParameter("nombre", nomb).getSingleResult();
            return experim;
        } catch (NoResultException ex) {
            return null;
        }
    }
//    
//    public ArrayList<String> posicionesPT(int idepx){
//        ArrayList<String> lista=new ArrayList<>();
//        Experimentacion aux=findExperimentacion(idepx);
//        aux.
//        return lista;
//    }
}
