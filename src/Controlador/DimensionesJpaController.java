/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.Dimensiones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Posturas;
import Modelo.PosicionesDescripcion;
import Modelo.Puntantrp;
import Modelo.Instrumento;
import Modelo.DimensionesRelevantes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author adian
 */
public class DimensionesJpaController implements Serializable {

    public DimensionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dimensiones dimensiones) {
        if (dimensiones.getDimensionesRelevantesList() == null) {
            dimensiones.setDimensionesRelevantesList(new ArrayList<DimensionesRelevantes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Posturas posturasIdposturas = dimensiones.getPosturasIdposturas();
            if (posturasIdposturas != null) {
                posturasIdposturas = em.getReference(posturasIdposturas.getClass(), posturasIdposturas.getIdposturas());
                dimensiones.setPosturasIdposturas(posturasIdposturas);
            }
            PosicionesDescripcion idposiciondescripcion = dimensiones.getIdposiciondescripcion();
            if (idposiciondescripcion != null) {
                idposiciondescripcion = em.getReference(idposiciondescripcion.getClass(), idposiciondescripcion.getIdposicionesDescripcion());
                dimensiones.setIdposiciondescripcion(idposiciondescripcion);
            }
            Puntantrp idpuntantrop = dimensiones.getIdpuntantrop();
            if (idpuntantrop != null) {
                idpuntantrop = em.getReference(idpuntantrop.getClass(), idpuntantrop.getIdpuntantrp());
                dimensiones.setIdpuntantrop(idpuntantrop);
            }
            Instrumento idinstrumento = dimensiones.getIdinstrumento();
            if (idinstrumento != null) {
                idinstrumento = em.getReference(idinstrumento.getClass(), idinstrumento.getIdinstrumento());
                dimensiones.setIdinstrumento(idinstrumento);
            }
            List<DimensionesRelevantes> attachedDimensionesRelevantesList = new ArrayList<DimensionesRelevantes>();
            for (DimensionesRelevantes dimensionesRelevantesListDimensionesRelevantesToAttach : dimensiones.getDimensionesRelevantesList()) {
                dimensionesRelevantesListDimensionesRelevantesToAttach = em.getReference(dimensionesRelevantesListDimensionesRelevantesToAttach.getClass(), dimensionesRelevantesListDimensionesRelevantesToAttach.getIddimensionesRelevantes());
                attachedDimensionesRelevantesList.add(dimensionesRelevantesListDimensionesRelevantesToAttach);
            }
            dimensiones.setDimensionesRelevantesList(attachedDimensionesRelevantesList);
            em.persist(dimensiones);
            if (posturasIdposturas != null) {
                posturasIdposturas.getDimensionesList().add(dimensiones);
                posturasIdposturas = em.merge(posturasIdposturas);
            }
            if (idposiciondescripcion != null) {
                idposiciondescripcion.getDimensionesList().add(dimensiones);
                idposiciondescripcion = em.merge(idposiciondescripcion);
            }
            if (idpuntantrop != null) {
                idpuntantrop.getDimensionesList().add(dimensiones);
                idpuntantrop = em.merge(idpuntantrop);
            }
            if (idinstrumento != null) {
                idinstrumento.getDimensionesList().add(dimensiones);
                idinstrumento = em.merge(idinstrumento);
            }
            for (DimensionesRelevantes dimensionesRelevantesListDimensionesRelevantes : dimensiones.getDimensionesRelevantesList()) {
                dimensionesRelevantesListDimensionesRelevantes.getDimensionesList().add(dimensiones);
                dimensionesRelevantesListDimensionesRelevantes = em.merge(dimensionesRelevantesListDimensionesRelevantes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dimensiones dimensiones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dimensiones persistentDimensiones = em.find(Dimensiones.class, dimensiones.getIddimensiones());
            Posturas posturasIdposturasOld = persistentDimensiones.getPosturasIdposturas();
            Posturas posturasIdposturasNew = dimensiones.getPosturasIdposturas();
            PosicionesDescripcion idposiciondescripcionOld = persistentDimensiones.getIdposiciondescripcion();
            PosicionesDescripcion idposiciondescripcionNew = dimensiones.getIdposiciondescripcion();
            Puntantrp idpuntantropOld = persistentDimensiones.getIdpuntantrop();
            Puntantrp idpuntantropNew = dimensiones.getIdpuntantrop();
            Instrumento idinstrumentoOld = persistentDimensiones.getIdinstrumento();
            Instrumento idinstrumentoNew = dimensiones.getIdinstrumento();
            List<DimensionesRelevantes> dimensionesRelevantesListOld = persistentDimensiones.getDimensionesRelevantesList();
            List<DimensionesRelevantes> dimensionesRelevantesListNew = dimensiones.getDimensionesRelevantesList();
            if (posturasIdposturasNew != null) {
                posturasIdposturasNew = em.getReference(posturasIdposturasNew.getClass(), posturasIdposturasNew.getIdposturas());
                dimensiones.setPosturasIdposturas(posturasIdposturasNew);
            }
            if (idposiciondescripcionNew != null) {
                idposiciondescripcionNew = em.getReference(idposiciondescripcionNew.getClass(), idposiciondescripcionNew.getIdposicionesDescripcion());
                dimensiones.setIdposiciondescripcion(idposiciondescripcionNew);
            }
            if (idpuntantropNew != null) {
                idpuntantropNew = em.getReference(idpuntantropNew.getClass(), idpuntantropNew.getIdpuntantrp());
                dimensiones.setIdpuntantrop(idpuntantropNew);
            }
            if (idinstrumentoNew != null) {
                idinstrumentoNew = em.getReference(idinstrumentoNew.getClass(), idinstrumentoNew.getIdinstrumento());
                dimensiones.setIdinstrumento(idinstrumentoNew);
            }
            List<DimensionesRelevantes> attachedDimensionesRelevantesListNew = new ArrayList<DimensionesRelevantes>();
            for (DimensionesRelevantes dimensionesRelevantesListNewDimensionesRelevantesToAttach : dimensionesRelevantesListNew) {
                dimensionesRelevantesListNewDimensionesRelevantesToAttach = em.getReference(dimensionesRelevantesListNewDimensionesRelevantesToAttach.getClass(), dimensionesRelevantesListNewDimensionesRelevantesToAttach.getIddimensionesRelevantes());
                attachedDimensionesRelevantesListNew.add(dimensionesRelevantesListNewDimensionesRelevantesToAttach);
            }
            dimensionesRelevantesListNew = attachedDimensionesRelevantesListNew;
            dimensiones.setDimensionesRelevantesList(dimensionesRelevantesListNew);
            dimensiones = em.merge(dimensiones);
            if (posturasIdposturasOld != null && !posturasIdposturasOld.equals(posturasIdposturasNew)) {
                posturasIdposturasOld.getDimensionesList().remove(dimensiones);
                posturasIdposturasOld = em.merge(posturasIdposturasOld);
            }
            if (posturasIdposturasNew != null && !posturasIdposturasNew.equals(posturasIdposturasOld)) {
                posturasIdposturasNew.getDimensionesList().add(dimensiones);
                posturasIdposturasNew = em.merge(posturasIdposturasNew);
            }
            if (idposiciondescripcionOld != null && !idposiciondescripcionOld.equals(idposiciondescripcionNew)) {
                idposiciondescripcionOld.getDimensionesList().remove(dimensiones);
                idposiciondescripcionOld = em.merge(idposiciondescripcionOld);
            }
            if (idposiciondescripcionNew != null && !idposiciondescripcionNew.equals(idposiciondescripcionOld)) {
                idposiciondescripcionNew.getDimensionesList().add(dimensiones);
                idposiciondescripcionNew = em.merge(idposiciondescripcionNew);
            }
            if (idpuntantropOld != null && !idpuntantropOld.equals(idpuntantropNew)) {
                idpuntantropOld.getDimensionesList().remove(dimensiones);
                idpuntantropOld = em.merge(idpuntantropOld);
            }
            if (idpuntantropNew != null && !idpuntantropNew.equals(idpuntantropOld)) {
                idpuntantropNew.getDimensionesList().add(dimensiones);
                idpuntantropNew = em.merge(idpuntantropNew);
            }
            if (idinstrumentoOld != null && !idinstrumentoOld.equals(idinstrumentoNew)) {
                idinstrumentoOld.getDimensionesList().remove(dimensiones);
                idinstrumentoOld = em.merge(idinstrumentoOld);
            }
            if (idinstrumentoNew != null && !idinstrumentoNew.equals(idinstrumentoOld)) {
                idinstrumentoNew.getDimensionesList().add(dimensiones);
                idinstrumentoNew = em.merge(idinstrumentoNew);
            }
            for (DimensionesRelevantes dimensionesRelevantesListOldDimensionesRelevantes : dimensionesRelevantesListOld) {
                if (!dimensionesRelevantesListNew.contains(dimensionesRelevantesListOldDimensionesRelevantes)) {
                    dimensionesRelevantesListOldDimensionesRelevantes.getDimensionesList().remove(dimensiones);
                    dimensionesRelevantesListOldDimensionesRelevantes = em.merge(dimensionesRelevantesListOldDimensionesRelevantes);
                }
            }
            for (DimensionesRelevantes dimensionesRelevantesListNewDimensionesRelevantes : dimensionesRelevantesListNew) {
                if (!dimensionesRelevantesListOld.contains(dimensionesRelevantesListNewDimensionesRelevantes)) {
                    dimensionesRelevantesListNewDimensionesRelevantes.getDimensionesList().add(dimensiones);
                    dimensionesRelevantesListNewDimensionesRelevantes = em.merge(dimensionesRelevantesListNewDimensionesRelevantes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dimensiones.getIddimensiones();
                if (findDimensiones(id) == null) {
                    throw new NonexistentEntityException("The dimensiones with id " + id + " no longer exists.");
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
            Dimensiones dimensiones;
            try {
                dimensiones = em.getReference(Dimensiones.class, id);
                dimensiones.getIddimensiones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dimensiones with id " + id + " no longer exists.", enfe);
            }
            Posturas posturasIdposturas = dimensiones.getPosturasIdposturas();
            if (posturasIdposturas != null) {
                posturasIdposturas.getDimensionesList().remove(dimensiones);
                posturasIdposturas = em.merge(posturasIdposturas);
            }
            PosicionesDescripcion idposiciondescripcion = dimensiones.getIdposiciondescripcion();
            if (idposiciondescripcion != null) {
                idposiciondescripcion.getDimensionesList().remove(dimensiones);
                idposiciondescripcion = em.merge(idposiciondescripcion);
            }
            Puntantrp idpuntantrop = dimensiones.getIdpuntantrop();
            if (idpuntantrop != null) {
                idpuntantrop.getDimensionesList().remove(dimensiones);
                idpuntantrop = em.merge(idpuntantrop);
            }
            Instrumento idinstrumento = dimensiones.getIdinstrumento();
            if (idinstrumento != null) {
                idinstrumento.getDimensionesList().remove(dimensiones);
                idinstrumento = em.merge(idinstrumento);
            }
            List<DimensionesRelevantes> dimensionesRelevantesList = dimensiones.getDimensionesRelevantesList();
            for (DimensionesRelevantes dimensionesRelevantesListDimensionesRelevantes : dimensionesRelevantesList) {
                dimensionesRelevantesListDimensionesRelevantes.getDimensionesList().remove(dimensiones);
                dimensionesRelevantesListDimensionesRelevantes = em.merge(dimensionesRelevantesListDimensionesRelevantes);
            }
            em.remove(dimensiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dimensiones> findDimensionesEntities() {
        return findDimensionesEntities(true, -1, -1);
    }

    public List<Dimensiones> findDimensionesEntities(int maxResults, int firstResult) {
        return findDimensionesEntities(false, maxResults, firstResult);
    }

    private List<Dimensiones> findDimensionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dimensiones.class));
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

    public Dimensiones findDimensiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dimensiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getDimensionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dimensiones> rt = cq.from(Dimensiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Dimensiones Mostrar(String nombre) {
        try {
            Dimensiones aux;
            EntityManager em = getEntityManager();
            aux = (Dimensiones) em.createNamedQuery("Dimensiones.findByNombmedid").setParameter("nombmedid", nombre).getSingleResult();
            return aux;
        } catch (NoResultException ex) {
            return null;
        }
    }

}
