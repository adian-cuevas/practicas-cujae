/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Usuario;
import Modelo.Ejercicio;
import Modelo.UsuarioHasEjercicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eduardo
 */
public class UsuarioHasEjercicioJpaController implements Serializable {

    public UsuarioHasEjercicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioHasEjercicio usuarioHasEjercicio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioIdusuario = usuarioHasEjercicio.getUsuarioIdusuario();
            if (usuarioIdusuario != null) {
                usuarioIdusuario = em.getReference(usuarioIdusuario.getClass(), usuarioIdusuario.getIdusuario());
                usuarioHasEjercicio.setUsuarioIdusuario(usuarioIdusuario);
            }
            Ejercicio idejercicios = usuarioHasEjercicio.getIdejercicios();
            if (idejercicios != null) {
                idejercicios = em.getReference(idejercicios.getClass(), idejercicios.getIdejercicio());
                usuarioHasEjercicio.setIdejercicios(idejercicios);
            }
            em.persist(usuarioHasEjercicio);
            if (usuarioIdusuario != null) {
                usuarioIdusuario.getUsuarioHasEjercicioList().add(usuarioHasEjercicio);
                usuarioIdusuario = em.merge(usuarioIdusuario);
            }
            if (idejercicios != null) {
                idejercicios.getUsuarioHasEjercicioList().add(usuarioHasEjercicio);
                idejercicios = em.merge(idejercicios);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioHasEjercicio(usuarioHasEjercicio.getIdrespejerc()) != null) {
                throw new PreexistingEntityException("UsuarioHasEjercicio " + usuarioHasEjercicio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioHasEjercicio usuarioHasEjercicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioHasEjercicio persistentUsuarioHasEjercicio = em.find(UsuarioHasEjercicio.class, usuarioHasEjercicio.getIdrespejerc());
            Usuario usuarioIdusuarioOld = persistentUsuarioHasEjercicio.getUsuarioIdusuario();
            Usuario usuarioIdusuarioNew = usuarioHasEjercicio.getUsuarioIdusuario();
            Ejercicio idejerciciosOld = persistentUsuarioHasEjercicio.getIdejercicios();
            Ejercicio idejerciciosNew = usuarioHasEjercicio.getIdejercicios();
            if (usuarioIdusuarioNew != null) {
                usuarioIdusuarioNew = em.getReference(usuarioIdusuarioNew.getClass(), usuarioIdusuarioNew.getIdusuario());
                usuarioHasEjercicio.setUsuarioIdusuario(usuarioIdusuarioNew);
            }
            if (idejerciciosNew != null) {
                idejerciciosNew = em.getReference(idejerciciosNew.getClass(), idejerciciosNew.getIdejercicio());
                usuarioHasEjercicio.setIdejercicios(idejerciciosNew);
            }
            usuarioHasEjercicio = em.merge(usuarioHasEjercicio);
            if (usuarioIdusuarioOld != null && !usuarioIdusuarioOld.equals(usuarioIdusuarioNew)) {
                usuarioIdusuarioOld.getUsuarioHasEjercicioList().remove(usuarioHasEjercicio);
                usuarioIdusuarioOld = em.merge(usuarioIdusuarioOld);
            }
            if (usuarioIdusuarioNew != null && !usuarioIdusuarioNew.equals(usuarioIdusuarioOld)) {
                usuarioIdusuarioNew.getUsuarioHasEjercicioList().add(usuarioHasEjercicio);
                usuarioIdusuarioNew = em.merge(usuarioIdusuarioNew);
            }
            if (idejerciciosOld != null && !idejerciciosOld.equals(idejerciciosNew)) {
                idejerciciosOld.getUsuarioHasEjercicioList().remove(usuarioHasEjercicio);
                idejerciciosOld = em.merge(idejerciciosOld);
            }
            if (idejerciciosNew != null && !idejerciciosNew.equals(idejerciciosOld)) {
                idejerciciosNew.getUsuarioHasEjercicioList().add(usuarioHasEjercicio);
                idejerciciosNew = em.merge(idejerciciosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarioHasEjercicio.getIdrespejerc();
                if (findUsuarioHasEjercicio(id) == null) {
                    throw new NonexistentEntityException("The usuarioHasEjercicio with id " + id + " no longer exists.");
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
            UsuarioHasEjercicio usuarioHasEjercicio;
            try {
                usuarioHasEjercicio = em.getReference(UsuarioHasEjercicio.class, id);
                usuarioHasEjercicio.getIdrespejerc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioHasEjercicio with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioIdusuario = usuarioHasEjercicio.getUsuarioIdusuario();
            if (usuarioIdusuario != null) {
                usuarioIdusuario.getUsuarioHasEjercicioList().remove(usuarioHasEjercicio);
                usuarioIdusuario = em.merge(usuarioIdusuario);
            }
            Ejercicio idejercicios = usuarioHasEjercicio.getIdejercicios();
            if (idejercicios != null) {
                idejercicios.getUsuarioHasEjercicioList().remove(usuarioHasEjercicio);
                idejercicios = em.merge(idejercicios);
            }
            em.remove(usuarioHasEjercicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioHasEjercicio> findUsuarioHasEjercicioEntities() {
        return findUsuarioHasEjercicioEntities(true, -1, -1);
    }

    public List<UsuarioHasEjercicio> findUsuarioHasEjercicioEntities(int maxResults, int firstResult) {
        return findUsuarioHasEjercicioEntities(false, maxResults, firstResult);
    }

    private List<UsuarioHasEjercicio> findUsuarioHasEjercicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioHasEjercicio.class));
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

    public UsuarioHasEjercicio findUsuarioHasEjercicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioHasEjercicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioHasEjercicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioHasEjercicio> rt = cq.from(UsuarioHasEjercicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
