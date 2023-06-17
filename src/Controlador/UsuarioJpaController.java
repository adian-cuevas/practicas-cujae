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
import Modelo.Rol;
import Modelo.UsuarioHasEjercicio;
import java.util.ArrayList;
import java.util.List;
import Modelo.RespuestaExperimentacion;
import Modelo.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Eduardo
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getUsuarioHasEjercicioList() == null) {
            usuario.setUsuarioHasEjercicioList(new ArrayList<UsuarioHasEjercicio>());
        }
        if (usuario.getRespuestaExperimentacionList() == null) {
            usuario.setRespuestaExperimentacionList(new ArrayList<RespuestaExperimentacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rolIdrol = usuario.getRolIdrol();
            if (rolIdrol != null) {
                rolIdrol = em.getReference(rolIdrol.getClass(), rolIdrol.getIdrol());
                usuario.setRolIdrol(rolIdrol);
            }
            List<UsuarioHasEjercicio> attachedUsuarioHasEjercicioList = new ArrayList<UsuarioHasEjercicio>();
            for (UsuarioHasEjercicio usuarioHasEjercicioListUsuarioHasEjercicioToAttach : usuario.getUsuarioHasEjercicioList()) {
                usuarioHasEjercicioListUsuarioHasEjercicioToAttach = em.getReference(usuarioHasEjercicioListUsuarioHasEjercicioToAttach.getClass(), usuarioHasEjercicioListUsuarioHasEjercicioToAttach.getIdrespejerc());
                attachedUsuarioHasEjercicioList.add(usuarioHasEjercicioListUsuarioHasEjercicioToAttach);
            }
            usuario.setUsuarioHasEjercicioList(attachedUsuarioHasEjercicioList);
            List<RespuestaExperimentacion> attachedRespuestaExperimentacionList = new ArrayList<RespuestaExperimentacion>();
            for (RespuestaExperimentacion respuestaExperimentacionListRespuestaExperimentacionToAttach : usuario.getRespuestaExperimentacionList()) {
                respuestaExperimentacionListRespuestaExperimentacionToAttach = em.getReference(respuestaExperimentacionListRespuestaExperimentacionToAttach.getClass(), respuestaExperimentacionListRespuestaExperimentacionToAttach.getId());
                attachedRespuestaExperimentacionList.add(respuestaExperimentacionListRespuestaExperimentacionToAttach);
            }
            usuario.setRespuestaExperimentacionList(attachedRespuestaExperimentacionList);
            em.persist(usuario);
            if (rolIdrol != null) {
                rolIdrol.getUsuarioList().add(usuario);
                rolIdrol = em.merge(rolIdrol);
            }
            for (UsuarioHasEjercicio usuarioHasEjercicioListUsuarioHasEjercicio : usuario.getUsuarioHasEjercicioList()) {
                Usuario oldUsuarioIdusuarioOfUsuarioHasEjercicioListUsuarioHasEjercicio = usuarioHasEjercicioListUsuarioHasEjercicio.getUsuarioIdusuario();
                usuarioHasEjercicioListUsuarioHasEjercicio.setUsuarioIdusuario(usuario);
                usuarioHasEjercicioListUsuarioHasEjercicio = em.merge(usuarioHasEjercicioListUsuarioHasEjercicio);
                if (oldUsuarioIdusuarioOfUsuarioHasEjercicioListUsuarioHasEjercicio != null) {
                    oldUsuarioIdusuarioOfUsuarioHasEjercicioListUsuarioHasEjercicio.getUsuarioHasEjercicioList().remove(usuarioHasEjercicioListUsuarioHasEjercicio);
                    oldUsuarioIdusuarioOfUsuarioHasEjercicioListUsuarioHasEjercicio = em.merge(oldUsuarioIdusuarioOfUsuarioHasEjercicioListUsuarioHasEjercicio);
                }
            }
            for (RespuestaExperimentacion respuestaExperimentacionListRespuestaExperimentacion : usuario.getRespuestaExperimentacionList()) {
                Usuario oldUsuarioidusuarioOfRespuestaExperimentacionListRespuestaExperimentacion = respuestaExperimentacionListRespuestaExperimentacion.getUsuarioidusuario();
                respuestaExperimentacionListRespuestaExperimentacion.setUsuarioidusuario(usuario);
                respuestaExperimentacionListRespuestaExperimentacion = em.merge(respuestaExperimentacionListRespuestaExperimentacion);
                if (oldUsuarioidusuarioOfRespuestaExperimentacionListRespuestaExperimentacion != null) {
                    oldUsuarioidusuarioOfRespuestaExperimentacionListRespuestaExperimentacion.getRespuestaExperimentacionList().remove(respuestaExperimentacionListRespuestaExperimentacion);
                    oldUsuarioidusuarioOfRespuestaExperimentacionListRespuestaExperimentacion = em.merge(oldUsuarioidusuarioOfRespuestaExperimentacionListRespuestaExperimentacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdusuario());
            Rol rolIdrolOld = persistentUsuario.getRolIdrol();
            Rol rolIdrolNew = usuario.getRolIdrol();
            List<UsuarioHasEjercicio> usuarioHasEjercicioListOld = persistentUsuario.getUsuarioHasEjercicioList();
            List<UsuarioHasEjercicio> usuarioHasEjercicioListNew = usuario.getUsuarioHasEjercicioList();
            List<RespuestaExperimentacion> respuestaExperimentacionListOld = persistentUsuario.getRespuestaExperimentacionList();
            List<RespuestaExperimentacion> respuestaExperimentacionListNew = usuario.getRespuestaExperimentacionList();
            List<String> illegalOrphanMessages = null;
            for (UsuarioHasEjercicio usuarioHasEjercicioListOldUsuarioHasEjercicio : usuarioHasEjercicioListOld) {
                if (!usuarioHasEjercicioListNew.contains(usuarioHasEjercicioListOldUsuarioHasEjercicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioHasEjercicio " + usuarioHasEjercicioListOldUsuarioHasEjercicio + " since its usuarioIdusuario field is not nullable.");
                }
            }
            for (RespuestaExperimentacion respuestaExperimentacionListOldRespuestaExperimentacion : respuestaExperimentacionListOld) {
                if (!respuestaExperimentacionListNew.contains(respuestaExperimentacionListOldRespuestaExperimentacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RespuestaExperimentacion " + respuestaExperimentacionListOldRespuestaExperimentacion + " since its usuarioidusuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (rolIdrolNew != null) {
                rolIdrolNew = em.getReference(rolIdrolNew.getClass(), rolIdrolNew.getIdrol());
                usuario.setRolIdrol(rolIdrolNew);
            }
            List<UsuarioHasEjercicio> attachedUsuarioHasEjercicioListNew = new ArrayList<UsuarioHasEjercicio>();
            for (UsuarioHasEjercicio usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach : usuarioHasEjercicioListNew) {
                usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach = em.getReference(usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach.getClass(), usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach.getIdrespejerc());
                attachedUsuarioHasEjercicioListNew.add(usuarioHasEjercicioListNewUsuarioHasEjercicioToAttach);
            }
            usuarioHasEjercicioListNew = attachedUsuarioHasEjercicioListNew;
            usuario.setUsuarioHasEjercicioList(usuarioHasEjercicioListNew);
            List<RespuestaExperimentacion> attachedRespuestaExperimentacionListNew = new ArrayList<RespuestaExperimentacion>();
            for (RespuestaExperimentacion respuestaExperimentacionListNewRespuestaExperimentacionToAttach : respuestaExperimentacionListNew) {
                respuestaExperimentacionListNewRespuestaExperimentacionToAttach = em.getReference(respuestaExperimentacionListNewRespuestaExperimentacionToAttach.getClass(), respuestaExperimentacionListNewRespuestaExperimentacionToAttach.getId());
                attachedRespuestaExperimentacionListNew.add(respuestaExperimentacionListNewRespuestaExperimentacionToAttach);
            }
            respuestaExperimentacionListNew = attachedRespuestaExperimentacionListNew;
            usuario.setRespuestaExperimentacionList(respuestaExperimentacionListNew);
            usuario = em.merge(usuario);
            if (rolIdrolOld != null && !rolIdrolOld.equals(rolIdrolNew)) {
                rolIdrolOld.getUsuarioList().remove(usuario);
                rolIdrolOld = em.merge(rolIdrolOld);
            }
            if (rolIdrolNew != null && !rolIdrolNew.equals(rolIdrolOld)) {
                rolIdrolNew.getUsuarioList().add(usuario);
                rolIdrolNew = em.merge(rolIdrolNew);
            }
            for (UsuarioHasEjercicio usuarioHasEjercicioListNewUsuarioHasEjercicio : usuarioHasEjercicioListNew) {
                if (!usuarioHasEjercicioListOld.contains(usuarioHasEjercicioListNewUsuarioHasEjercicio)) {
                    Usuario oldUsuarioIdusuarioOfUsuarioHasEjercicioListNewUsuarioHasEjercicio = usuarioHasEjercicioListNewUsuarioHasEjercicio.getUsuarioIdusuario();
                    usuarioHasEjercicioListNewUsuarioHasEjercicio.setUsuarioIdusuario(usuario);
                    usuarioHasEjercicioListNewUsuarioHasEjercicio = em.merge(usuarioHasEjercicioListNewUsuarioHasEjercicio);
                    if (oldUsuarioIdusuarioOfUsuarioHasEjercicioListNewUsuarioHasEjercicio != null && !oldUsuarioIdusuarioOfUsuarioHasEjercicioListNewUsuarioHasEjercicio.equals(usuario)) {
                        oldUsuarioIdusuarioOfUsuarioHasEjercicioListNewUsuarioHasEjercicio.getUsuarioHasEjercicioList().remove(usuarioHasEjercicioListNewUsuarioHasEjercicio);
                        oldUsuarioIdusuarioOfUsuarioHasEjercicioListNewUsuarioHasEjercicio = em.merge(oldUsuarioIdusuarioOfUsuarioHasEjercicioListNewUsuarioHasEjercicio);
                    }
                }
            }
            for (RespuestaExperimentacion respuestaExperimentacionListNewRespuestaExperimentacion : respuestaExperimentacionListNew) {
                if (!respuestaExperimentacionListOld.contains(respuestaExperimentacionListNewRespuestaExperimentacion)) {
                    Usuario oldUsuarioidusuarioOfRespuestaExperimentacionListNewRespuestaExperimentacion = respuestaExperimentacionListNewRespuestaExperimentacion.getUsuarioidusuario();
                    respuestaExperimentacionListNewRespuestaExperimentacion.setUsuarioidusuario(usuario);
                    respuestaExperimentacionListNewRespuestaExperimentacion = em.merge(respuestaExperimentacionListNewRespuestaExperimentacion);
                    if (oldUsuarioidusuarioOfRespuestaExperimentacionListNewRespuestaExperimentacion != null && !oldUsuarioidusuarioOfRespuestaExperimentacionListNewRespuestaExperimentacion.equals(usuario)) {
                        oldUsuarioidusuarioOfRespuestaExperimentacionListNewRespuestaExperimentacion.getRespuestaExperimentacionList().remove(respuestaExperimentacionListNewRespuestaExperimentacion);
                        oldUsuarioidusuarioOfRespuestaExperimentacionListNewRespuestaExperimentacion = em.merge(oldUsuarioidusuarioOfRespuestaExperimentacionListNewRespuestaExperimentacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdusuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UsuarioHasEjercicio> usuarioHasEjercicioListOrphanCheck = usuario.getUsuarioHasEjercicioList();
            for (UsuarioHasEjercicio usuarioHasEjercicioListOrphanCheckUsuarioHasEjercicio : usuarioHasEjercicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the UsuarioHasEjercicio " + usuarioHasEjercicioListOrphanCheckUsuarioHasEjercicio + " in its usuarioHasEjercicioList field has a non-nullable usuarioIdusuario field.");
            }
            List<RespuestaExperimentacion> respuestaExperimentacionListOrphanCheck = usuario.getRespuestaExperimentacionList();
            for (RespuestaExperimentacion respuestaExperimentacionListOrphanCheckRespuestaExperimentacion : respuestaExperimentacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the RespuestaExperimentacion " + respuestaExperimentacionListOrphanCheckRespuestaExperimentacion + " in its respuestaExperimentacionList field has a non-nullable usuarioidusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rol rolIdrol = usuario.getRolIdrol();
            if (rolIdrol != null) {
                rolIdrol.getUsuarioList().remove(usuario);
                rolIdrol = em.merge(rolIdrol);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Usuario login(String usuario, String password) throws NoResultException {
        Usuario usuarioLogueado = null;
        EntityManager em = getEntityManager();
        Query a = em.createQuery("SELECT a FROM Usuario a WHERE a.nombUsuario =:usuarioPas AND a.contraseña =:password");
        a.setParameter("usuarioPas", usuario);
        a.setParameter("password", password);
        usuarioLogueado = (Usuario) a.getSingleResult();
        return usuarioLogueado;

    }

    public ArrayList<String> UsuariosExistentes() {
        ArrayList<String> listaux = new ArrayList<String>();
        List<Usuario> list = findUsuarioEntities();
        for (Usuario usuario : list) {
            listaux.add(usuario.getNombUsuario());
        }
        return listaux;
    }

    public Usuario findByName(String name) {
        try {
            EntityManager em = getEntityManager();
            Usuario a = null;
            a = (Usuario) em.createNamedQuery("Usuario.findByNombUsuario").setParameter("nombUsuario", name).getSingleResult();
            return a;

        } catch (NoResultException ex) {
            return null;
        }

    }
}
