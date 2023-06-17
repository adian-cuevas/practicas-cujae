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
import Modelo.ComplementosPregunta;
import Modelo.Ejercicio;
import Modelo.Tipoejercicio;
import Modelo.Repuestas;
import Modelo.Tema;
import Modelo.UsuarioHasEjercicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eduardo
 */
public class EjercicioJpaController implements Serializable {

    public EjercicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejercicio ejercicio) {
        if (ejercicio.getUsuarioHasEjercicioList() == null) {
            ejercicio.setUsuarioHasEjercicioList(new ArrayList<UsuarioHasEjercicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComplementosPregunta complementosPreguntaIdcomplementosPregunta = ejercicio.getComplementosPreguntaIdcomplementosPregunta();
            if (complementosPreguntaIdcomplementosPregunta != null) {
                complementosPreguntaIdcomplementosPregunta = em.getReference(complementosPreguntaIdcomplementosPregunta.getClass(), complementosPreguntaIdcomplementosPregunta.getIdcomplementosPregunta());
                ejercicio.setComplementosPreguntaIdcomplementosPregunta(complementosPreguntaIdcomplementosPregunta);
            }
            Tipoejercicio idtipoejercicio = ejercicio.getIdtipoejercicio();
            if (idtipoejercicio != null) {
                idtipoejercicio = em.getReference(idtipoejercicio.getClass(), idtipoejercicio.getIdtipoejercicio());
                ejercicio.setIdtipoejercicio(idtipoejercicio);
            }
            Repuestas idcomplementosRespuesta = ejercicio.getIdcomplementosRespuesta();
            if (idcomplementosRespuesta != null) {
                idcomplementosRespuesta = em.getReference(idcomplementosRespuesta.getClass(), idcomplementosRespuesta.getIdrespuestas());
                ejercicio.setIdcomplementosRespuesta(idcomplementosRespuesta);
            }
            Tema idtema = ejercicio.getIdtema();
            if (idtema != null) {
                idtema = em.getReference(idtema.getClass(), idtema.getIdtema());
                ejercicio.setIdtema(idtema);
            }
            List<UsuarioHasEjercicio> attachedUsuarioHasEjercicioList = new ArrayList<UsuarioHasEjercicio>();
            for (UsuarioHasEjercicio usuarioHasEjercicioListUsuarioHasEjercicioToAttach : ejercicio.getUsuarioHasEjercicioList()) {
                usuarioHasEjercicioListUsuarioHasEjercicioToAttach = em.getReference(usuarioHasEjercicioListUsuarioHasEjercicioToAttach.getClass(), usuarioHasEjercicioListUsuarioHasEjercicioToAttach.getIdrespejerc());
                attachedUsuarioHasEjercicioList.add(usuarioHasEjercicioListUsuarioHasEjercicioToAttach);
            }
            ejercicio.setUsuarioHasEjercicioList(attachedUsuarioHasEjercicioList);
            em.persist(ejercicio);
            if (complementosPreguntaIdcomplementosPregunta != null) {
                complementosPreguntaIdcomplementosPregunta.getEjercicioList().add(ejercicio);
                complementosPreguntaIdcomplementosPregunta = em.merge(complementosPreguntaIdcomplementosPregunta);
            }
            if (idtipoejercicio != null) {
                idtipoejercicio.getEjercicioList().add(ejercicio);
                idtipoejercicio = em.merge(idtipoejercicio);
            }
            if (idcomplementosRespuesta != null) {
                idcomplementosRespuesta.getEjercicioList().add(ejercicio);
                idcomplementosRespuesta = em.merge(idcomplementosRespuesta);
            }
            if (idtema != null) {
                idtema.getEjercicioList().add(ejercicio);
                idtema = em.merge(idtema);
            }
            for (UsuarioHasEjercicio usuarioHasEjercicioListUsuarioHasEjercicio : ejercicio.getUsuarioHasEjercicioList()) {
                Ejercicio oldIdejerciciosOfUsuarioHasEjercicioListUsuarioHasEjercicio = usuarioHasEjercicioListUsuarioHasEjercicio.getIdejercicios();
                usuarioHasEjercicioListUsuarioHasEjercicio.setIdejercicios(ejercicio);
                usuarioHasEjercicioListUsuarioHasEjercicio = em.merge(usuarioHasEjercicioListUsuarioHasEjercicio);
                if (oldIdejerciciosOfUsuarioHasEjercicioListUsuarioHasEjercicio != null) {
                    oldIdejerciciosOfUsuarioHasEjercicioListUsuarioHasEjercicio.getUsuarioHasEjercicioList().remove(usuarioHasEjercicioListUsuarioHasEjercicio);
                    oldIdejerciciosOfUsuarioHasEjercicioListUsuarioHasEjercicio = em.merge(oldIdejerciciosOfUsuarioHasEjercicioListUsuarioHasEjercicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejercicio ejercicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejercicio persistentEjercicio = em.find(Ejercicio.class, ejercicio.getIdejercicio());
            ComplementosPregunta complementosPreguntaIdcomplementosPreguntaOld = persistentEjercicio.getComplementosPreguntaIdcomplementosPregunta();
            ComplementosPregunta complementosPreguntaIdcomplementosPreguntaNew = ejercicio.getComplementosPreguntaIdcomplementosPregunta();
            Tipoejercicio idtipoejercicioOld = persistentEjercicio.getIdtipoejercicio();
            Tipoejercicio idtipoejercicioNew = ejercicio.getIdtipoejercicio();
            Repuestas idcomplementosRespuestaOld = persistentEjercicio.getIdcomplementosRespuesta();
            Repuestas idcomplementosRespuestaNew = ejercicio.getIdcomplementosRespuesta();
            Tema idtemaOld = persistentEjercicio.getIdtema();
            Tema idtemaNew = ejercicio.getIdtema();
            List<UsuarioHasEjercicio> usuarioHasEjercicioListOld = persistentEjercicio.getUsuarioHasEjercicioList();
            List<UsuarioHasEjercicio> usuarioHasEjercicioListNew = ejercicio.getUsuarioHasEjercicioList();
            if (complementosPreguntaIdcomplementosPreguntaNew != null) {
                complementosPreguntaIdcomplementosPreguntaNew = em.getReference(complementosPreguntaIdcomplementosPreguntaNew.getClass(), complementosPreguntaIdcomplementosPreguntaNew.getIdcomplementosPregunta());
                ejercicio.setComplementosPreguntaIdcomplementosPregunta(complementosPreguntaIdcomplementosPreguntaNew);
            }
            if (idtipoejercicioNew != null) {
                idtipoejercicioNew = em.getReference(idtipoejercicioNew.getClass(), idtipoejercicioNew.getIdtipoejercicio());
                ejercicio.setIdtipoejercicio(idtipoejercicioNew);
            }
            if (idcomplementosRespuestaNew != null) {
                idcomplementosRespuestaNew = em.getReference(idcomplementosRespuestaNew.getClass(), idcomplementosRespuestaNew.getIdrespuestas());
                ejercicio.setIdcomplementosRespuesta(idcomplementosRespuestaNew);
            }
            if (idtemaNew != null) {
                idtemaNew = em.getReference(idtemaNew.getClass(), idtemaNew.getIdtema());
                ejercicio.setIdtema(idtemaNew);
            }
            List<UsuarioHasEjercicio> attachedUsuarioHasEjercicioListNew = new ArrayList<UsuarioHasEjercicio>();
            for (UsuarioHasEjercicio usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach : usuarioHasEjercicioListNew) {
                usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach = em.getReference(usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach.getClass(), usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach.getIdrespejerc());
                attachedUsuarioHasEjercicioListNew.add(usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach);
            }
            usuarioHasEjercicioListNew = attachedUsuarioHasEjercicioListNew;
            ejercicio.setUsuarioHasEjercicioList(usuarioHasEjercicioListNew);
            ejercicio = em.merge(ejercicio);
            if (complementosPreguntaIdcomplementosPreguntaOld != null && !complementosPreguntaIdcomplementosPreguntaOld.equals(complementosPreguntaIdcomplementosPreguntaNew)) {
                complementosPreguntaIdcomplementosPreguntaOld.getEjercicioList().remove(ejercicio);
                complementosPreguntaIdcomplementosPreguntaOld = em.merge(complementosPreguntaIdcomplementosPreguntaOld);
            }
            if (complementosPreguntaIdcomplementosPreguntaNew != null && !complementosPreguntaIdcomplementosPreguntaNew.equals(complementosPreguntaIdcomplementosPreguntaOld)) {
                complementosPreguntaIdcomplementosPreguntaNew.getEjercicioList().add(ejercicio);
                complementosPreguntaIdcomplementosPreguntaNew = em.merge(complementosPreguntaIdcomplementosPreguntaNew);
            }
            if (idtipoejercicioOld != null && !idtipoejercicioOld.equals(idtipoejercicioNew)) {
                idtipoejercicioOld.getEjercicioList().remove(ejercicio);
                idtipoejercicioOld = em.merge(idtipoejercicioOld);
            }
            if (idtipoejercicioNew != null && !idtipoejercicioNew.equals(idtipoejercicioOld)) {
                idtipoejercicioNew.getEjercicioList().add(ejercicio);
                idtipoejercicioNew = em.merge(idtipoejercicioNew);
            }
            if (idcomplementosRespuestaOld != null && !idcomplementosRespuestaOld.equals(idcomplementosRespuestaNew)) {
                idcomplementosRespuestaOld.getEjercicioList().remove(ejercicio);
                idcomplementosRespuestaOld = em.merge(idcomplementosRespuestaOld);
            }
            if (idcomplementosRespuestaNew != null && !idcomplementosRespuestaNew.equals(idcomplementosRespuestaOld)) {
                idcomplementosRespuestaNew.getEjercicioList().add(ejercicio);
                idcomplementosRespuestaNew = em.merge(idcomplementosRespuestaNew);
            }
            if (idtemaOld != null && !idtemaOld.equals(idtemaNew)) {
                idtemaOld.getEjercicioList().remove(ejercicio);
                idtemaOld = em.merge(idtemaOld);
            }
            if (idtemaNew != null && !idtemaNew.equals(idtemaOld)) {
                idtemaNew.getEjercicioList().add(ejercicio);
                idtemaNew = em.merge(idtemaNew);
            }
            for (UsuarioHasEjercicio usuarioHasEjercicioListOldUsuarioHasEjercicio : usuarioHasEjercicioListOld) {
                if (!usuarioHasEjercicioListNew.contains(usuarioHasEjercicioListOldUsuarioHasEjercicio)) {
                    usuarioHasEjercicioListOldUsuarioHasEjercicio.setIdejercicios(null);
                    usuarioHasEjercicioListOldUsuarioHasEjercicio = em.merge(usuarioHasEjercicioListOldUsuarioHasEjercicio);
                }
            }
            for (UsuarioHasEjercicio usuarioHasEjercicioListNewUsuarioHasEjercicio : usuarioHasEjercicioListNew) {
                if (!usuarioHasEjercicioListOld.contains(usuarioHasEjercicioListNewUsuarioHasEjercicio)) {
                    Ejercicio oldIdejerciciosOfUsuarioHasEjercicioListNewUsuarioHasEjercicio = usuarioHasEjercicioListNewUsuarioHasEjercicio.getIdejercicios();
                    usuarioHasEjercicioListNewUsuarioHasEjercicio.setIdejercicios(ejercicio);
                    usuarioHasEjercicioListNewUsuarioHasEjercicio = em.merge(usuarioHasEjercicioListNewUsuarioHasEjercicio);
                    if (oldIdejerciciosOfUsuarioHasEjercicioListNewUsuarioHasEjercicio != null && !oldIdejerciciosOfUsuarioHasEjercicioListNewUsuarioHasEjercicio.equals(ejercicio)) {
                        oldIdejerciciosOfUsuarioHasEjercicioListNewUsuarioHasEjercicio.getUsuarioHasEjercicioList().remove(usuarioHasEjercicioListNewUsuarioHasEjercicio);
                        oldIdejerciciosOfUsuarioHasEjercicioListNewUsuarioHasEjercicio = em.merge(oldIdejerciciosOfUsuarioHasEjercicioListNewUsuarioHasEjercicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ejercicio.getIdejercicio();
                if (findEjercicio(id) == null) {
                    throw new NonexistentEntityException("The ejercicio with id " + id + " no longer exists.");
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
            Ejercicio ejercicio;
            try {
                ejercicio = em.getReference(Ejercicio.class, id);
                ejercicio.getIdejercicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejercicio with id " + id + " no longer exists.", enfe);
            }
            ComplementosPregunta complementosPreguntaIdcomplementosPregunta = ejercicio.getComplementosPreguntaIdcomplementosPregunta();
            if (complementosPreguntaIdcomplementosPregunta != null) {
                complementosPreguntaIdcomplementosPregunta.getEjercicioList().remove(ejercicio);
                complementosPreguntaIdcomplementosPregunta = em.merge(complementosPreguntaIdcomplementosPregunta);
            }
            Tipoejercicio idtipoejercicio = ejercicio.getIdtipoejercicio();
            if (idtipoejercicio != null) {
                idtipoejercicio.getEjercicioList().remove(ejercicio);
                idtipoejercicio = em.merge(idtipoejercicio);
            }
            Repuestas idcomplementosRespuesta = ejercicio.getIdcomplementosRespuesta();
            if (idcomplementosRespuesta != null) {
                idcomplementosRespuesta.getEjercicioList().remove(ejercicio);
                idcomplementosRespuesta = em.merge(idcomplementosRespuesta);
            }
            Tema idtema = ejercicio.getIdtema();
            if (idtema != null) {
                idtema.getEjercicioList().remove(ejercicio);
                idtema = em.merge(idtema);
            }
            List<UsuarioHasEjercicio> usuarioHasEjercicioList = ejercicio.getUsuarioHasEjercicioList();
            for (UsuarioHasEjercicio usuarioHasEjercicioListUsuarioHasEjercicio : usuarioHasEjercicioList) {
                usuarioHasEjercicioListUsuarioHasEjercicio.setIdejercicios(null);
                usuarioHasEjercicioListUsuarioHasEjercicio = em.merge(usuarioHasEjercicioListUsuarioHasEjercicio);
            }
            em.remove(ejercicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejercicio> findEjercicioEntities() {
        return findEjercicioEntities(true, -1, -1);
    }

    public List<Ejercicio> findEjercicioEntities(int maxResults, int firstResult) {
        return findEjercicioEntities(false, maxResults, firstResult);
    }

    private List<Ejercicio> findEjercicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejercicio.class));
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

    public Ejercicio findEjercicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejercicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjercicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejercicio> rt = cq.from(Ejercicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public Ejercicio Mostrar(int post){
        Ejercicio ejerc;
        EntityManager em = getEntityManager();
        List<Ejercicio> listaejercicio=findEjercicioEntities();
        ejerc=listaejercicio.get(post);
        return ejerc;
    }
}
