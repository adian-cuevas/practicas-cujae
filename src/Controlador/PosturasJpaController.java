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
import Modelo.Posturas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author adian
 */
public class PosturasJpaController implements Serializable {

    public PosturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Posturas posturas) {
        if (posturas.getDimensionesList() == null) {
            posturas.setDimensionesList(new ArrayList<Dimensiones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dimensiones> attachedDimensionesList = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListDimensionesToAttach : posturas.getDimensionesList()) {
                dimensionesListDimensionesToAttach = em.getReference(dimensionesListDimensionesToAttach.getClass(), dimensionesListDimensionesToAttach.getIddimensiones());
                attachedDimensionesList.add(dimensionesListDimensionesToAttach);
            }
            posturas.setDimensionesList(attachedDimensionesList);
            em.persist(posturas);
            for (Dimensiones dimensionesListDimensiones : posturas.getDimensionesList()) {
                Posturas oldPosturasIdposturasOfDimensionesListDimensiones = dimensionesListDimensiones.getPosturasIdposturas();
                dimensionesListDimensiones.setPosturasIdposturas(posturas);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
                if (oldPosturasIdposturasOfDimensionesListDimensiones != null) {
                    oldPosturasIdposturasOfDimensionesListDimensiones.getDimensionesList().remove(dimensionesListDimensiones);
                    oldPosturasIdposturasOfDimensionesListDimensiones = em.merge(oldPosturasIdposturasOfDimensionesListDimensiones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Posturas posturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Posturas persistentPosturas = em.find(Posturas.class, posturas.getIdposturas());
            List<Dimensiones> dimensionesListOld = persistentPosturas.getDimensionesList();
            List<Dimensiones> dimensionesListNew = posturas.getDimensionesList();
            List<Dimensiones> attachedDimensionesListNew = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListNewDimensionesToAttach : dimensionesListNew) {
                dimensionesListNewDimensionesToAttach = em.getReference(dimensionesListNewDimensionesToAttach.getClass(), dimensionesListNewDimensionesToAttach.getIddimensiones());
                attachedDimensionesListNew.add(dimensionesListNewDimensionesToAttach);
            }
            dimensionesListNew = attachedDimensionesListNew;
            posturas.setDimensionesList(dimensionesListNew);
            posturas = em.merge(posturas);
            for (Dimensiones dimensionesListOldDimensiones : dimensionesListOld) {
                if (!dimensionesListNew.contains(dimensionesListOldDimensiones)) {
                    dimensionesListOldDimensiones.setPosturasIdposturas(null);
                    dimensionesListOldDimensiones = em.merge(dimensionesListOldDimensiones);
                }
            }
            for (Dimensiones dimensionesListNewDimensiones : dimensionesListNew) {
                if (!dimensionesListOld.contains(dimensionesListNewDimensiones)) {
                    Posturas oldPosturasIdposturasOfDimensionesListNewDimensiones = dimensionesListNewDimensiones.getPosturasIdposturas();
                    dimensionesListNewDimensiones.setPosturasIdposturas(posturas);
                    dimensionesListNewDimensiones = em.merge(dimensionesListNewDimensiones);
                    if (oldPosturasIdposturasOfDimensionesListNewDimensiones != null && !oldPosturasIdposturasOfDimensionesListNewDimensiones.equals(posturas)) {
                        oldPosturasIdposturasOfDimensionesListNewDimensiones.getDimensionesList().remove(dimensionesListNewDimensiones);
                        oldPosturasIdposturasOfDimensionesListNewDimensiones = em.merge(oldPosturasIdposturasOfDimensionesListNewDimensiones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = posturas.getIdposturas();
                if (findPosturas(id) == null) {
                    throw new NonexistentEntityException("The posturas with id " + id + " no longer exists.");
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
            Posturas posturas;
            try {
                posturas = em.getReference(Posturas.class, id);
                posturas.getIdposturas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The posturas with id " + id + " no longer exists.", enfe);
            }
            List<Dimensiones> dimensionesList = posturas.getDimensionesList();
            for (Dimensiones dimensionesListDimensiones : dimensionesList) {
                dimensionesListDimensiones.setPosturasIdposturas(null);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
            }
            em.remove(posturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Posturas> findPosturasEntities() {
        return findPosturasEntities(true, -1, -1);
    }

    public List<Posturas> findPosturasEntities(int maxResults, int firstResult) {
        return findPosturasEntities(false, maxResults, firstResult);
    }

    private List<Posturas> findPosturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Posturas.class));
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

    public Posturas findPosturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Posturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPosturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Posturas> rt = cq.from(Posturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
