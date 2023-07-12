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
import Modelo.Dimensiones;
import Modelo.PosicionesDescripcion;
import java.util.ArrayList;
import java.util.List;
import Modelo.SujetoPosiciones;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author adian
 */
public class PosicionesDescripcionJpaController implements Serializable {

    public PosicionesDescripcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PosicionesDescripcion posicionesDescripcion) {
        if (posicionesDescripcion.getDimensionesList() == null) {
            posicionesDescripcion.setDimensionesList(new ArrayList<Dimensiones>());
        }
        if (posicionesDescripcion.getSujetoPosicionesList() == null) {
            posicionesDescripcion.setSujetoPosicionesList(new ArrayList<SujetoPosiciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dimensiones> attachedDimensionesList = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListDimensionesToAttach : posicionesDescripcion.getDimensionesList()) {
                dimensionesListDimensionesToAttach = em.getReference(dimensionesListDimensionesToAttach.getClass(), dimensionesListDimensionesToAttach.getIddimensiones());
                attachedDimensionesList.add(dimensionesListDimensionesToAttach);
            }
            posicionesDescripcion.setDimensionesList(attachedDimensionesList);
            List<SujetoPosiciones> attachedSujetoPosicionesList = new ArrayList<SujetoPosiciones>();
            for (SujetoPosiciones sujetoPosicionesListSujetoPosicionesToAttach : posicionesDescripcion.getSujetoPosicionesList()) {
                sujetoPosicionesListSujetoPosicionesToAttach = em.getReference(sujetoPosicionesListSujetoPosicionesToAttach.getClass(), sujetoPosicionesListSujetoPosicionesToAttach.getIdsujetoPosiciones());
                attachedSujetoPosicionesList.add(sujetoPosicionesListSujetoPosicionesToAttach);
            }
            posicionesDescripcion.setSujetoPosicionesList(attachedSujetoPosicionesList);
            em.persist(posicionesDescripcion);
            for (Dimensiones dimensionesListDimensiones : posicionesDescripcion.getDimensionesList()) {
                PosicionesDescripcion oldIdposiciondescripcionOfDimensionesListDimensiones = dimensionesListDimensiones.getIdposiciondescripcion();
                dimensionesListDimensiones.setIdposiciondescripcion(posicionesDescripcion);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
                if (oldIdposiciondescripcionOfDimensionesListDimensiones != null) {
                    oldIdposiciondescripcionOfDimensionesListDimensiones.getDimensionesList().remove(dimensionesListDimensiones);
                    oldIdposiciondescripcionOfDimensionesListDimensiones = em.merge(oldIdposiciondescripcionOfDimensionesListDimensiones);
                }
            }
            for (SujetoPosiciones sujetoPosicionesListSujetoPosiciones : posicionesDescripcion.getSujetoPosicionesList()) {
                PosicionesDescripcion oldIdposicionesDescripcionOfSujetoPosicionesListSujetoPosiciones = sujetoPosicionesListSujetoPosiciones.getIdposicionesDescripcion();
                sujetoPosicionesListSujetoPosiciones.setIdposicionesDescripcion(posicionesDescripcion);
                sujetoPosicionesListSujetoPosiciones = em.merge(sujetoPosicionesListSujetoPosiciones);
                if (oldIdposicionesDescripcionOfSujetoPosicionesListSujetoPosiciones != null) {
                    oldIdposicionesDescripcionOfSujetoPosicionesListSujetoPosiciones.getSujetoPosicionesList().remove(sujetoPosicionesListSujetoPosiciones);
                    oldIdposicionesDescripcionOfSujetoPosicionesListSujetoPosiciones = em.merge(oldIdposicionesDescripcionOfSujetoPosicionesListSujetoPosiciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PosicionesDescripcion posicionesDescripcion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PosicionesDescripcion persistentPosicionesDescripcion = em.find(PosicionesDescripcion.class, posicionesDescripcion.getIdposicionesDescripcion());
            List<Dimensiones> dimensionesListOld = persistentPosicionesDescripcion.getDimensionesList();
            List<Dimensiones> dimensionesListNew = posicionesDescripcion.getDimensionesList();
            List<SujetoPosiciones> sujetoPosicionesListOld = persistentPosicionesDescripcion.getSujetoPosicionesList();
            List<SujetoPosiciones> sujetoPosicionesListNew = posicionesDescripcion.getSujetoPosicionesList();
            List<String> illegalOrphanMessages = null;
            for (SujetoPosiciones sujetoPosicionesListOldSujetoPosiciones : sujetoPosicionesListOld) {
                if (!sujetoPosicionesListNew.contains(sujetoPosicionesListOldSujetoPosiciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SujetoPosiciones " + sujetoPosicionesListOldSujetoPosiciones + " since its idposicionesDescripcion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Dimensiones> attachedDimensionesListNew = new ArrayList<Dimensiones>();
            for (Dimensiones dimensionesListNewDimensionesToAttach : dimensionesListNew) {
                dimensionesListNewDimensionesToAttach = em.getReference(dimensionesListNewDimensionesToAttach.getClass(), dimensionesListNewDimensionesToAttach.getIddimensiones());
                attachedDimensionesListNew.add(dimensionesListNewDimensionesToAttach);
            }
            dimensionesListNew = attachedDimensionesListNew;
            posicionesDescripcion.setDimensionesList(dimensionesListNew);
            List<SujetoPosiciones> attachedSujetoPosicionesListNew = new ArrayList<SujetoPosiciones>();
            for (SujetoPosiciones sujetoPosicionesListNewSujetoPosicionesToAttach : sujetoPosicionesListNew) {
                sujetoPosicionesListNewSujetoPosicionesToAttach = em.getReference(sujetoPosicionesListNewSujetoPosicionesToAttach.getClass(), sujetoPosicionesListNewSujetoPosicionesToAttach.getIdsujetoPosiciones());
                attachedSujetoPosicionesListNew.add(sujetoPosicionesListNewSujetoPosicionesToAttach);
            }
            sujetoPosicionesListNew = attachedSujetoPosicionesListNew;
            posicionesDescripcion.setSujetoPosicionesList(sujetoPosicionesListNew);
            posicionesDescripcion = em.merge(posicionesDescripcion);
            for (Dimensiones dimensionesListOldDimensiones : dimensionesListOld) {
                if (!dimensionesListNew.contains(dimensionesListOldDimensiones)) {
                    dimensionesListOldDimensiones.setIdposiciondescripcion(null);
                    dimensionesListOldDimensiones = em.merge(dimensionesListOldDimensiones);
                }
            }
            for (Dimensiones dimensionesListNewDimensiones : dimensionesListNew) {
                if (!dimensionesListOld.contains(dimensionesListNewDimensiones)) {
                    PosicionesDescripcion oldIdposiciondescripcionOfDimensionesListNewDimensiones = dimensionesListNewDimensiones.getIdposiciondescripcion();
                    dimensionesListNewDimensiones.setIdposiciondescripcion(posicionesDescripcion);
                    dimensionesListNewDimensiones = em.merge(dimensionesListNewDimensiones);
                    if (oldIdposiciondescripcionOfDimensionesListNewDimensiones != null && !oldIdposiciondescripcionOfDimensionesListNewDimensiones.equals(posicionesDescripcion)) {
                        oldIdposiciondescripcionOfDimensionesListNewDimensiones.getDimensionesList().remove(dimensionesListNewDimensiones);
                        oldIdposiciondescripcionOfDimensionesListNewDimensiones = em.merge(oldIdposiciondescripcionOfDimensionesListNewDimensiones);
                    }
                }
            }
            for (SujetoPosiciones sujetoPosicionesListNewSujetoPosiciones : sujetoPosicionesListNew) {
                if (!sujetoPosicionesListOld.contains(sujetoPosicionesListNewSujetoPosiciones)) {
                    PosicionesDescripcion oldIdposicionesDescripcionOfSujetoPosicionesListNewSujetoPosiciones = sujetoPosicionesListNewSujetoPosiciones.getIdposicionesDescripcion();
                    sujetoPosicionesListNewSujetoPosiciones.setIdposicionesDescripcion(posicionesDescripcion);
                    sujetoPosicionesListNewSujetoPosiciones = em.merge(sujetoPosicionesListNewSujetoPosiciones);
                    if (oldIdposicionesDescripcionOfSujetoPosicionesListNewSujetoPosiciones != null && !oldIdposicionesDescripcionOfSujetoPosicionesListNewSujetoPosiciones.equals(posicionesDescripcion)) {
                        oldIdposicionesDescripcionOfSujetoPosicionesListNewSujetoPosiciones.getSujetoPosicionesList().remove(sujetoPosicionesListNewSujetoPosiciones);
                        oldIdposicionesDescripcionOfSujetoPosicionesListNewSujetoPosiciones = em.merge(oldIdposicionesDescripcionOfSujetoPosicionesListNewSujetoPosiciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = posicionesDescripcion.getIdposicionesDescripcion();
                if (findPosicionesDescripcion(id) == null) {
                    throw new NonexistentEntityException("The posicionesDescripcion with id " + id + " no longer exists.");
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
            PosicionesDescripcion posicionesDescripcion;
            try {
                posicionesDescripcion = em.getReference(PosicionesDescripcion.class, id);
                posicionesDescripcion.getIdposicionesDescripcion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The posicionesDescripcion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SujetoPosiciones> sujetoPosicionesListOrphanCheck = posicionesDescripcion.getSujetoPosicionesList();
            for (SujetoPosiciones sujetoPosicionesListOrphanCheckSujetoPosiciones : sujetoPosicionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PosicionesDescripcion (" + posicionesDescripcion + ") cannot be destroyed since the SujetoPosiciones " + sujetoPosicionesListOrphanCheckSujetoPosiciones + " in its sujetoPosicionesList field has a non-nullable idposicionesDescripcion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Dimensiones> dimensionesList = posicionesDescripcion.getDimensionesList();
            for (Dimensiones dimensionesListDimensiones : dimensionesList) {
                dimensionesListDimensiones.setIdposiciondescripcion(null);
                dimensionesListDimensiones = em.merge(dimensionesListDimensiones);
            }
            em.remove(posicionesDescripcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PosicionesDescripcion> findPosicionesDescripcionEntities() {
        return findPosicionesDescripcionEntities(true, -1, -1);
    }

    public List<PosicionesDescripcion> findPosicionesDescripcionEntities(int maxResults, int firstResult) {
        return findPosicionesDescripcionEntities(false, maxResults, firstResult);
    }

    private List<PosicionesDescripcion> findPosicionesDescripcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PosicionesDescripcion.class));
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

    public PosicionesDescripcion findPosicionesDescripcion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PosicionesDescripcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPosicionesDescripcionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PosicionesDescripcion> rt = cq.from(PosicionesDescripcion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
