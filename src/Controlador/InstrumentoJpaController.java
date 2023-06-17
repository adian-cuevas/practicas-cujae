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
import Modelo.Instrumento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Eduardo
 */
public class InstrumentoJpaController implements Serializable {

    public InstrumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Instrumento instrumento) {
        if (instrumento.getDimensionesList() == null) {
            instrumento.setDimensionesList(new ArrayList<Dimensiones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dimensiones> attachedDimensionesList = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListDimensionesToAttach : instrumento.getDimensionesList()) {
                dimensionesListDimensionesToAttach = em.getReference(dimensionesListDimensionesToAttach.getClass(), dimensionesListDimensionesToAttach.getIddimensiones());
                attachedDimensionesList.add(dimensionesListDimensionesToAttach);
            }
            instrumento.setDimensionesList(attachedDimensionesList);
            em.persist(instrumento);
            for (Dimensiones dimensionesListDimensiones : instrumento.getDimensionesList()) {
                Instrumento oldIdinstrumentoOfDimensionesListDimensiones = dimensionesListDimensiones.getIdinstrumento();
                dimensionesListDimensiones.setIdinstrumento(instrumento);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
                if (oldIdinstrumentoOfDimensionesListDimensiones != null) {
                    oldIdinstrumentoOfDimensionesListDimensiones.getDimensionesList().remove(dimensionesListDimensiones);
                    oldIdinstrumentoOfDimensionesListDimensiones = em.merge(oldIdinstrumentoOfDimensionesListDimensiones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Instrumento instrumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Instrumento persistentInstrumento = em.find(Instrumento.class, instrumento.getIdinstrumento());
            List<Dimensiones> dimensionesListOld = persistentInstrumento.getDimensionesList();
            List<Dimensiones> dimensionesListNew = instrumento.getDimensionesList();
            List<Dimensiones> attachedDimensionesListNew = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListNewDimensionesToAttach : dimensionesListNew) {
                dimensionesListNewDimensionesToAttach = em.getReference(dimensionesListNewDimensionesToAttach.getClass(), dimensionesListNewDimensionesToAttach.getIddimensiones());
                attachedDimensionesListNew.add(dimensionesListNewDimensionesToAttach);
            }
            dimensionesListNew = attachedDimensionesListNew;
            instrumento.setDimensionesList(dimensionesListNew);
            instrumento = em.merge(instrumento);
            for (Dimensiones dimensionesListOldDimensiones : dimensionesListOld) {
                if (!dimensionesListNew.contains(dimensionesListOldDimensiones)) {
                    dimensionesListOldDimensiones.setIdinstrumento(null);
                    dimensionesListOldDimensiones = em.merge(dimensionesListOldDimensiones);
                }
            }
            for (Dimensiones dimensionesListNewDimensiones : dimensionesListNew) {
                if (!dimensionesListOld.contains(dimensionesListNewDimensiones)) {
                    Instrumento oldIdinstrumentoOfDimensionesListNewDimensiones = dimensionesListNewDimensiones.getIdinstrumento();
                    dimensionesListNewDimensiones.setIdinstrumento(instrumento);
                    dimensionesListNewDimensiones = em.merge(dimensionesListNewDimensiones);
                    if (oldIdinstrumentoOfDimensionesListNewDimensiones != null && !oldIdinstrumentoOfDimensionesListNewDimensiones.equals(instrumento)) {
                        oldIdinstrumentoOfDimensionesListNewDimensiones.getDimensionesList().remove(dimensionesListNewDimensiones);
                        oldIdinstrumentoOfDimensionesListNewDimensiones = em.merge(oldIdinstrumentoOfDimensionesListNewDimensiones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = instrumento.getIdinstrumento();
                if (findInstrumento(id) == null) {
                    throw new NonexistentEntityException("The instrumento with id " + id + " no longer exists.");
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
            Instrumento instrumento;
            try {
                instrumento = em.getReference(Instrumento.class, id);
                instrumento.getIdinstrumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The instrumento with id " + id + " no longer exists.", enfe);
            }
            List<Dimensiones> dimensionesList = instrumento.getDimensionesList();
            for (Dimensiones dimensionesListDimensiones : dimensionesList) {
                dimensionesListDimensiones.setIdinstrumento(null);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
            }
            em.remove(instrumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Instrumento> findInstrumentoEntities() {
        return findInstrumentoEntities(true, -1, -1);
    }

    public List<Instrumento> findInstrumentoEntities(int maxResults, int firstResult) {
        return findInstrumentoEntities(false, maxResults, firstResult);
    }

    private List<Instrumento> findInstrumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Instrumento.class));
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

    public Instrumento findInstrumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Instrumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstrumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Instrumento> rt = cq.from(Instrumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Instrumento Mostrar(String nomb) {
        try {
            Instrumento experim;
            EntityManager em = getEntityManager();
            experim = (Instrumento) em.createNamedQuery("Instrumento.findByNombInstrumento").setParameter("nombInstrumento", nomb).getSingleResult();
            return experim;
        } catch (NoResultException ex) {
            return null;
        }

    }
}
