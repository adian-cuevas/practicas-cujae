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
import Modelo.PosicionesDescripcion;
import Modelo.Sujeto;
import Modelo.PosicionesDimensiones;
import Modelo.SujetoPosiciones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eduardo
 */
public class SujetoPosicionesJpaController implements Serializable {

    public SujetoPosicionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SujetoPosiciones sujetoPosiciones) {
        if (sujetoPosiciones.getPosicionesDimensionesList() == null) {
            sujetoPosiciones.setPosicionesDimensionesList(new ArrayList<PosicionesDimensiones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PosicionesDescripcion idposicionesDescripcion = sujetoPosiciones.getIdposicionesDescripcion();
            if (idposicionesDescripcion != null) {
                idposicionesDescripcion = em.getReference(idposicionesDescripcion.getClass(), idposicionesDescripcion.getIdposicionesDescripcion());
                sujetoPosiciones.setIdposicionesDescripcion(idposicionesDescripcion);
            }
            Sujeto idsujeto = sujetoPosiciones.getIdsujeto();
            if (idsujeto != null) {
                idsujeto = em.getReference(idsujeto.getClass(), idsujeto.getIdsujeto());
                sujetoPosiciones.setIdsujeto(idsujeto);
            }
            List<PosicionesDimensiones> attachedPosicionesDimensionesList = new ArrayList<PosicionesDimensiones>();
            for (PosicionesDimensiones posicionesDimensionesListPosicionesDimensionesToAttach : sujetoPosiciones.getPosicionesDimensionesList()) {
                posicionesDimensionesListPosicionesDimensionesToAttach = em.getReference(posicionesDimensionesListPosicionesDimensionesToAttach.getClass(), posicionesDimensionesListPosicionesDimensionesToAttach.getIdposicionesDimensiones());
                attachedPosicionesDimensionesList.add(posicionesDimensionesListPosicionesDimensionesToAttach);
            }
            sujetoPosiciones.setPosicionesDimensionesList(attachedPosicionesDimensionesList);
            em.persist(sujetoPosiciones);
            if (idposicionesDescripcion != null) {
                idposicionesDescripcion.getSujetoPosicionesList().add(sujetoPosiciones);
                idposicionesDescripcion = em.merge(idposicionesDescripcion);
            }
            if (idsujeto != null) {
                idsujeto.getSujetoPosicionesList().add(sujetoPosiciones);
                idsujeto = em.merge(idsujeto);
            }
            for (PosicionesDimensiones posicionesDimensionesListPosicionesDimensiones : sujetoPosiciones.getPosicionesDimensionesList()) {
                SujetoPosiciones oldIdsujetoPosicionesOfPosicionesDimensionesListPosicionesDimensiones = posicionesDimensionesListPosicionesDimensiones.getIdsujetoPosiciones();
                posicionesDimensionesListPosicionesDimensiones.setIdsujetoPosiciones(sujetoPosiciones);
                posicionesDimensionesListPosicionesDimensiones = em.merge(posicionesDimensionesListPosicionesDimensiones);
                if (oldIdsujetoPosicionesOfPosicionesDimensionesListPosicionesDimensiones != null) {
                    oldIdsujetoPosicionesOfPosicionesDimensionesListPosicionesDimensiones.getPosicionesDimensionesList().remove(posicionesDimensionesListPosicionesDimensiones);
                    oldIdsujetoPosicionesOfPosicionesDimensionesListPosicionesDimensiones = em.merge(oldIdsujetoPosicionesOfPosicionesDimensionesListPosicionesDimensiones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SujetoPosiciones sujetoPosiciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SujetoPosiciones persistentSujetoPosiciones = em.find(SujetoPosiciones.class, sujetoPosiciones.getIdsujetoPosiciones());
            PosicionesDescripcion idposicionesDescripcionOld = persistentSujetoPosiciones.getIdposicionesDescripcion();
            PosicionesDescripcion idposicionesDescripcionNew = sujetoPosiciones.getIdposicionesDescripcion();
            Sujeto idsujetoOld = persistentSujetoPosiciones.getIdsujeto();
            Sujeto idsujetoNew = sujetoPosiciones.getIdsujeto();
            List<PosicionesDimensiones> posicionesDimensionesListOld = persistentSujetoPosiciones.getPosicionesDimensionesList();
            List<PosicionesDimensiones> posicionesDimensionesListNew = sujetoPosiciones.getPosicionesDimensionesList();
            List<String> illegalOrphanMessages = null;
            for (PosicionesDimensiones posicionesDimensionesListOldPosicionesDimensiones : posicionesDimensionesListOld) {
                if (!posicionesDimensionesListNew.contains(posicionesDimensionesListOldPosicionesDimensiones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PosicionesDimensiones " + posicionesDimensionesListOldPosicionesDimensiones + " since its idsujetoPosiciones field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idposicionesDescripcionNew != null) {
                idposicionesDescripcionNew = em.getReference(idposicionesDescripcionNew.getClass(), idposicionesDescripcionNew.getIdposicionesDescripcion());
                sujetoPosiciones.setIdposicionesDescripcion(idposicionesDescripcionNew);
            }
            if (idsujetoNew != null) {
                idsujetoNew = em.getReference(idsujetoNew.getClass(), idsujetoNew.getIdsujeto());
                sujetoPosiciones.setIdsujeto(idsujetoNew);
            }
            List<PosicionesDimensiones> attachedPosicionesDimensionesListNew = new ArrayList<PosicionesDimensiones>();
            for (PosicionesDimensiones posicionesDimensionesListNewPosicionesDimensionesToAttach : posicionesDimensionesListNew) {
                posicionesDimensionesListNewPosicionesDimensionesToAttach = em.getReference(posicionesDimensionesListNewPosicionesDimensionesToAttach.getClass(), posicionesDimensionesListNewPosicionesDimensionesToAttach.getIdposicionesDimensiones());
                attachedPosicionesDimensionesListNew.add(posicionesDimensionesListNewPosicionesDimensionesToAttach);
            }
            posicionesDimensionesListNew = attachedPosicionesDimensionesListNew;
            sujetoPosiciones.setPosicionesDimensionesList(posicionesDimensionesListNew);
            sujetoPosiciones = em.merge(sujetoPosiciones);
            if (idposicionesDescripcionOld != null && !idposicionesDescripcionOld.equals(idposicionesDescripcionNew)) {
                idposicionesDescripcionOld.getSujetoPosicionesList().remove(sujetoPosiciones);
                idposicionesDescripcionOld = em.merge(idposicionesDescripcionOld);
            }
            if (idposicionesDescripcionNew != null && !idposicionesDescripcionNew.equals(idposicionesDescripcionOld)) {
                idposicionesDescripcionNew.getSujetoPosicionesList().add(sujetoPosiciones);
                idposicionesDescripcionNew = em.merge(idposicionesDescripcionNew);
            }
            if (idsujetoOld != null && !idsujetoOld.equals(idsujetoNew)) {
                idsujetoOld.getSujetoPosicionesList().remove(sujetoPosiciones);
                idsujetoOld = em.merge(idsujetoOld);
            }
            if (idsujetoNew != null && !idsujetoNew.equals(idsujetoOld)) {
                idsujetoNew.getSujetoPosicionesList().add(sujetoPosiciones);
                idsujetoNew = em.merge(idsujetoNew);
            }
            for (PosicionesDimensiones posicionesDimensionesListNewPosicionesDimensiones : posicionesDimensionesListNew) {
                if (!posicionesDimensionesListOld.contains(posicionesDimensionesListNewPosicionesDimensiones)) {
                    SujetoPosiciones oldIdsujetoPosicionesOfPosicionesDimensionesListNewPosicionesDimensiones = posicionesDimensionesListNewPosicionesDimensiones.getIdsujetoPosiciones();
                    posicionesDimensionesListNewPosicionesDimensiones.setIdsujetoPosiciones(sujetoPosiciones);
                    posicionesDimensionesListNewPosicionesDimensiones = em.merge(posicionesDimensionesListNewPosicionesDimensiones);
                    if (oldIdsujetoPosicionesOfPosicionesDimensionesListNewPosicionesDimensiones != null && !oldIdsujetoPosicionesOfPosicionesDimensionesListNewPosicionesDimensiones.equals(sujetoPosiciones)) {
                        oldIdsujetoPosicionesOfPosicionesDimensionesListNewPosicionesDimensiones.getPosicionesDimensionesList().remove(posicionesDimensionesListNewPosicionesDimensiones);
                        oldIdsujetoPosicionesOfPosicionesDimensionesListNewPosicionesDimensiones = em.merge(oldIdsujetoPosicionesOfPosicionesDimensionesListNewPosicionesDimensiones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sujetoPosiciones.getIdsujetoPosiciones();
                if (findSujetoPosiciones(id) == null) {
                    throw new NonexistentEntityException("The sujetoPosiciones with id " + id + " no longer exists.");
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
            SujetoPosiciones sujetoPosiciones;
            try {
                sujetoPosiciones = em.getReference(SujetoPosiciones.class, id);
                sujetoPosiciones.getIdsujetoPosiciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sujetoPosiciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PosicionesDimensiones> posicionesDimensionesListOrphanCheck = sujetoPosiciones.getPosicionesDimensionesList();
            for (PosicionesDimensiones posicionesDimensionesListOrphanCheckPosicionesDimensiones : posicionesDimensionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SujetoPosiciones (" + sujetoPosiciones + ") cannot be destroyed since the PosicionesDimensiones " + posicionesDimensionesListOrphanCheckPosicionesDimensiones + " in its posicionesDimensionesList field has a non-nullable idsujetoPosiciones field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            PosicionesDescripcion idposicionesDescripcion = sujetoPosiciones.getIdposicionesDescripcion();
            if (idposicionesDescripcion != null) {
                idposicionesDescripcion.getSujetoPosicionesList().remove(sujetoPosiciones);
                idposicionesDescripcion = em.merge(idposicionesDescripcion);
            }
            Sujeto idsujeto = sujetoPosiciones.getIdsujeto();
            if (idsujeto != null) {
                idsujeto.getSujetoPosicionesList().remove(sujetoPosiciones);
                idsujeto = em.merge(idsujeto);
            }
            em.remove(sujetoPosiciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SujetoPosiciones> findSujetoPosicionesEntities() {
        return findSujetoPosicionesEntities(true, -1, -1);
    }

    public List<SujetoPosiciones> findSujetoPosicionesEntities(int maxResults, int firstResult) {
        return findSujetoPosicionesEntities(false, maxResults, firstResult);
    }

    private List<SujetoPosiciones> findSujetoPosicionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SujetoPosiciones.class));
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

    public SujetoPosiciones findSujetoPosiciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SujetoPosiciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getSujetoPosicionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SujetoPosiciones> rt = cq.from(SujetoPosiciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public int Posicion(int position_descripcion, int idsujeto ){
        EntityManager em=getEntityManager();
        Query q=em.createQuery("SELECT s.idsujetoPosiciones FROM SujetoPosiciones s WHERE s.idposicionesDescripcion:=posicionD AND s.idsujeto:= idsujet");
        q.setParameter("posicionD",position_descripcion);
        q.setParameter("idsujet",idsujeto);
        int result=(int) q.getSingleResult();
        return result;
    }
}
