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
import Modelo.Dimensiones;
import Modelo.DimensionesRelevantes;
import java.util.ArrayList;
import java.util.List;
import Modelo.Experimentacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Eduardo
 */
public class DimensionesRelevantesJpaController implements Serializable {

    public DimensionesRelevantesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DimensionesRelevantes dimensionesRelevantes) {
        if (dimensionesRelevantes.getDimensionesList() == null) {
            dimensionesRelevantes.setDimensionesList(new ArrayList<Dimensiones>());
        }
        if (dimensionesRelevantes.getExperimentacionList() == null) {
            dimensionesRelevantes.setExperimentacionList(new ArrayList<Experimentacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dimensiones> attachedDimensionesList = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListDimensionesToAttach : dimensionesRelevantes.getDimensionesList()) {
                dimensionesListDimensionesToAttach = em.getReference(dimensionesListDimensionesToAttach.getClass(), dimensionesListDimensionesToAttach.getIddimensiones());
                attachedDimensionesList.add(dimensionesListDimensionesToAttach);
            }
            dimensionesRelevantes.setDimensionesList(attachedDimensionesList);
            List<Experimentacion> attachedExperimentacionList = new ArrayList<Experimentacion>();
            for (Experimentacion experimentacionListExperimentacionToAttach : dimensionesRelevantes.getExperimentacionList()) {
                experimentacionListExperimentacionToAttach = em.getReference(experimentacionListExperimentacionToAttach.getClass(), experimentacionListExperimentacionToAttach.getIdExperimentacion());
                attachedExperimentacionList.add(experimentacionListExperimentacionToAttach);
            }
            dimensionesRelevantes.setExperimentacionList(attachedExperimentacionList);
            em.persist(dimensionesRelevantes);
            for (Dimensiones dimensionesListDimensiones : dimensionesRelevantes.getDimensionesList()) {
                dimensionesListDimensiones.getDimensionesRelevantesList().add(dimensionesRelevantes);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
            }
            for (Experimentacion experimentacionListExperimentacion : dimensionesRelevantes.getExperimentacionList()) {
                experimentacionListExperimentacion.getDimensionesRelevantesList().add(dimensionesRelevantes);
                experimentacionListExperimentacion = em.merge(experimentacionListExperimentacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DimensionesRelevantes dimensionesRelevantes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DimensionesRelevantes persistentDimensionesRelevantes = em.find(DimensionesRelevantes.class, dimensionesRelevantes.getIddimensionesRelevantes());
            List<Dimensiones> dimensionesListOld = persistentDimensionesRelevantes.getDimensionesList();
            List<Dimensiones> dimensionesListNew = dimensionesRelevantes.getDimensionesList();
            List<Experimentacion> experimentacionListOld = persistentDimensionesRelevantes.getExperimentacionList();
            List<Experimentacion> experimentacionListNew = dimensionesRelevantes.getExperimentacionList();
            List<Dimensiones> attachedDimensionesListNew = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListNewDimensionesToAttach : dimensionesListNew) {
                dimensionesListNewDimensionesToAttach = em.getReference(dimensionesListNewDimensionesToAttach.getClass(), dimensionesListNewDimensionesToAttach.getIddimensiones());
                attachedDimensionesListNew.add(dimensionesListNewDimensionesToAttach);
            }
            dimensionesListNew = attachedDimensionesListNew;
            dimensionesRelevantes.setDimensionesList(dimensionesListNew);
            List<Experimentacion> attachedExperimentacionListNew = new ArrayList<Experimentacion>();
            for (Experimentacion experimentacionListNewExperimentacionToAttach : experimentacionListNew) {
                experimentacionListNewExperimentacionToAttach = em.getReference(experimentacionListNewExperimentacionToAttach.getClass(), experimentacionListNewExperimentacionToAttach.getIdExperimentacion());
                attachedExperimentacionListNew.add(experimentacionListNewExperimentacionToAttach);
            }
            experimentacionListNew = attachedExperimentacionListNew;
            dimensionesRelevantes.setExperimentacionList(experimentacionListNew);
            dimensionesRelevantes = em.merge(dimensionesRelevantes);
            for (Dimensiones dimensionesListOldDimensiones : dimensionesListOld) {
                if (!dimensionesListNew.contains(dimensionesListOldDimensiones)) {
                    dimensionesListOldDimensiones.getDimensionesRelevantesList().remove(dimensionesRelevantes);
                    dimensionesListOldDimensiones = em.merge(dimensionesListOldDimensiones);
                }
            }
            for (Dimensiones dimensionesListNewDimensiones : dimensionesListNew) {
                if (!dimensionesListOld.contains(dimensionesListNewDimensiones)) {
                    dimensionesListNewDimensiones.getDimensionesRelevantesList().add(dimensionesRelevantes);
                    dimensionesListNewDimensiones = em.merge(dimensionesListNewDimensiones);
                }
            }
            for (Experimentacion experimentacionListOldExperimentacion : experimentacionListOld) {
                if (!experimentacionListNew.contains(experimentacionListOldExperimentacion)) {
                    experimentacionListOldExperimentacion.getDimensionesRelevantesList().remove(dimensionesRelevantes);
                    experimentacionListOldExperimentacion = em.merge(experimentacionListOldExperimentacion);
                }
            }
            for (Experimentacion experimentacionListNewExperimentacion : experimentacionListNew) {
                if (!experimentacionListOld.contains(experimentacionListNewExperimentacion)) {
                    experimentacionListNewExperimentacion.getDimensionesRelevantesList().add(dimensionesRelevantes);
                    experimentacionListNewExperimentacion = em.merge(experimentacionListNewExperimentacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dimensionesRelevantes.getIddimensionesRelevantes();
                if (findDimensionesRelevantes(id) == null) {
                    throw new NonexistentEntityException("The dimensionesRelevantes with id " + id + " no longer exists.");
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
            DimensionesRelevantes dimensionesRelevantes;
            try {
                dimensionesRelevantes = em.getReference(DimensionesRelevantes.class, id);
                dimensionesRelevantes.getIddimensionesRelevantes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dimensionesRelevantes with id " + id + " no longer exists.", enfe);
            }
            List<Dimensiones> dimensionesList = dimensionesRelevantes.getDimensionesList();
            for (Dimensiones dimensionesListDimensiones : dimensionesList) {
                dimensionesListDimensiones.getDimensionesRelevantesList().remove(dimensionesRelevantes);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
            }
            List<Experimentacion> experimentacionList = dimensionesRelevantes.getExperimentacionList();
            for (Experimentacion experimentacionListExperimentacion : experimentacionList) {
                experimentacionListExperimentacion.getDimensionesRelevantesList().remove(dimensionesRelevantes);
                experimentacionListExperimentacion = em.merge(experimentacionListExperimentacion);
            }
            em.remove(dimensionesRelevantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DimensionesRelevantes> findDimensionesRelevantesEntities() {
        return findDimensionesRelevantesEntities(true, -1, -1);
    }

    public List<DimensionesRelevantes> findDimensionesRelevantesEntities(int maxResults, int firstResult) {
        return findDimensionesRelevantesEntities(false, maxResults, firstResult);
    }

    private List<DimensionesRelevantes> findDimensionesRelevantesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DimensionesRelevantes.class));
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

    public DimensionesRelevantes findDimensionesRelevantes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DimensionesRelevantes.class, id);
        } finally {
            em.close();
        }
    }

    public int getDimensionesRelevantesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DimensionesRelevantes> rt = cq.from(DimensionesRelevantes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public DimensionesRelevantes findByName(String name) {
        try {
            EntityManager em = getEntityManager();
            DimensionesRelevantes a = null;
            a = (DimensionesRelevantes) em.createNamedQuery("DimensionesRelevantes.findByNombDimension").setParameter("nombDimension", name).getSingleResult();
            return a;
        } catch (NoResultException ex) {
            return null;
        }

    }
}
