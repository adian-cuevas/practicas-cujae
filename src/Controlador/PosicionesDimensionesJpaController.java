/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.PosicionesDimensiones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.SujetoPosiciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Eduardo
 */
public class PosicionesDimensionesJpaController implements Serializable {

    public PosicionesDimensionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PosicionesDimensiones posicionesDimensiones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SujetoPosiciones idsujetoPosiciones = posicionesDimensiones.getIdsujetoPosiciones();
            if (idsujetoPosiciones != null) {
                idsujetoPosiciones = em.getReference(idsujetoPosiciones.getClass(), idsujetoPosiciones.getIdsujetoPosiciones());
                posicionesDimensiones.setIdsujetoPosiciones(idsujetoPosiciones);
            }
            em.persist(posicionesDimensiones);
            if (idsujetoPosiciones != null) {
                idsujetoPosiciones.getPosicionesDimensionesList().add(posicionesDimensiones);
                idsujetoPosiciones = em.merge(idsujetoPosiciones);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PosicionesDimensiones posicionesDimensiones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PosicionesDimensiones persistentPosicionesDimensiones = em.find(PosicionesDimensiones.class, posicionesDimensiones.getIdposicionesDimensiones());
            SujetoPosiciones idsujetoPosicionesOld = persistentPosicionesDimensiones.getIdsujetoPosiciones();
            SujetoPosiciones idsujetoPosicionesNew = posicionesDimensiones.getIdsujetoPosiciones();
            if (idsujetoPosicionesNew != null) {
                idsujetoPosicionesNew = em.getReference(idsujetoPosicionesNew.getClass(), idsujetoPosicionesNew.getIdsujetoPosiciones());
                posicionesDimensiones.setIdsujetoPosiciones(idsujetoPosicionesNew);
            }
            posicionesDimensiones = em.merge(posicionesDimensiones);
            if (idsujetoPosicionesOld != null && !idsujetoPosicionesOld.equals(idsujetoPosicionesNew)) {
                idsujetoPosicionesOld.getPosicionesDimensionesList().remove(posicionesDimensiones);
                idsujetoPosicionesOld = em.merge(idsujetoPosicionesOld);
            }
            if (idsujetoPosicionesNew != null && !idsujetoPosicionesNew.equals(idsujetoPosicionesOld)) {
                idsujetoPosicionesNew.getPosicionesDimensionesList().add(posicionesDimensiones);
                idsujetoPosicionesNew = em.merge(idsujetoPosicionesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = posicionesDimensiones.getIdposicionesDimensiones();
                if (findPosicionesDimensiones(id) == null) {
                    throw new NonexistentEntityException("The posicionesDimensiones with id " + id + " no longer exists.");
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
            PosicionesDimensiones posicionesDimensiones;
            try {
                posicionesDimensiones = em.getReference(PosicionesDimensiones.class, id);
                posicionesDimensiones.getIdposicionesDimensiones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The posicionesDimensiones with id " + id + " no longer exists.", enfe);
            }
            SujetoPosiciones idsujetoPosiciones = posicionesDimensiones.getIdsujetoPosiciones();
            if (idsujetoPosiciones != null) {
                idsujetoPosiciones.getPosicionesDimensionesList().remove(posicionesDimensiones);
                idsujetoPosiciones = em.merge(idsujetoPosiciones);
            }
            em.remove(posicionesDimensiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PosicionesDimensiones> findPosicionesDimensionesEntities() {
        return findPosicionesDimensionesEntities(true, -1, -1);
    }

    public List<PosicionesDimensiones> findPosicionesDimensionesEntities(int maxResults, int firstResult) {
        return findPosicionesDimensionesEntities(false, maxResults, firstResult);
    }

    private List<PosicionesDimensiones> findPosicionesDimensionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PosicionesDimensiones.class));
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

    public PosicionesDimensiones findPosicionesDimensiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PosicionesDimensiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getPosicionesDimensionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PosicionesDimensiones> rt = cq.from(PosicionesDimensiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public PosicionesDimensiones coordenadasxPosiciones(String dimension,int idsujeto,int iddescripcion)throws NoResultException{
        PosicionesDimensiones posicion=null;
        EntityManager em=getEntityManager();
        Query q=em.createQuery("SELECT pd FROM PosicionesDimensiones pd WHERE pd.nombDimension = ?1 AND pd.idsujetoPosiciones.idsujeto.idsujeto = ?2 AND pd.idsujetoPosiciones.idposicionesDescripcion.idposicionesDescripcion = ?3");
        q.setParameter(1,dimension);
        q.setParameter(2, idsujeto);
        q.setParameter(3, iddescripcion);
        posicion=(PosicionesDimensiones) q.getSingleResult();
        return posicion;
    }
}
