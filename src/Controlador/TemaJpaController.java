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
import Modelo.Ejercicio;
import Modelo.Tema;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Eduardo
 */
public class TemaJpaController implements Serializable {

    public TemaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tema tema) {
        if (tema.getEjercicioList() == null) {
            tema.setEjercicioList(new ArrayList<Ejercicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejercicio> attachedEjercicioList = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListEjercicioToAttach : tema.getEjercicioList()) {
                ejercicioListEjercicioToAttach = em.getReference(ejercicioListEjercicioToAttach.getClass(), ejercicioListEjercicioToAttach.getIdejercicio());
                attachedEjercicioList.add(ejercicioListEjercicioToAttach);
            }
            tema.setEjercicioList(attachedEjercicioList);
            em.persist(tema);
            for (Ejercicio ejercicioListEjercicio : tema.getEjercicioList()) {
                Tema oldIdtemaOfEjercicioListEjercicio = ejercicioListEjercicio.getIdtema();
                ejercicioListEjercicio.setIdtema(tema);
                ejercicioListEjercicio = em.merge(ejercicioListEjercicio);
                if (oldIdtemaOfEjercicioListEjercicio != null) {
                    oldIdtemaOfEjercicioListEjercicio.getEjercicioList().remove(ejercicioListEjercicio);
                    oldIdtemaOfEjercicioListEjercicio = em.merge(oldIdtemaOfEjercicioListEjercicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tema tema) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tema persistentTema = em.find(Tema.class, tema.getIdtema());
            List<Ejercicio> ejercicioListOld = persistentTema.getEjercicioList();
            List<Ejercicio> ejercicioListNew = tema.getEjercicioList();
            List<Ejercicio> attachedEjercicioListNew = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListNewEjercicioToAttach : ejercicioListNew) {
                ejercicioListNewEjercicioToAttach = em.getReference(ejercicioListNewEjercicioToAttach.getClass(), ejercicioListNewEjercicioToAttach.getIdejercicio());
                attachedEjercicioListNew.add(ejercicioListNewEjercicioToAttach);
            }
            ejercicioListNew = attachedEjercicioListNew;
            tema.setEjercicioList(ejercicioListNew);
            tema = em.merge(tema);
            for (Ejercicio ejercicioListOldEjercicio : ejercicioListOld) {
                if (!ejercicioListNew.contains(ejercicioListOldEjercicio)) {
                    ejercicioListOldEjercicio.setIdtema(null);
                    ejercicioListOldEjercicio = em.merge(ejercicioListOldEjercicio);
                }
            }
            for (Ejercicio ejercicioListNewEjercicio : ejercicioListNew) {
                if (!ejercicioListOld.contains(ejercicioListNewEjercicio)) {
                    Tema oldIdtemaOfEjercicioListNewEjercicio = ejercicioListNewEjercicio.getIdtema();
                    ejercicioListNewEjercicio.setIdtema(tema);
                    ejercicioListNewEjercicio = em.merge(ejercicioListNewEjercicio);
                    if (oldIdtemaOfEjercicioListNewEjercicio != null && !oldIdtemaOfEjercicioListNewEjercicio.equals(tema)) {
                        oldIdtemaOfEjercicioListNewEjercicio.getEjercicioList().remove(ejercicioListNewEjercicio);
                        oldIdtemaOfEjercicioListNewEjercicio = em.merge(oldIdtemaOfEjercicioListNewEjercicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tema.getIdtema();
                if (findTema(id) == null) {
                    throw new NonexistentEntityException("The tema with id " + id + " no longer exists.");
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
            Tema tema;
            try {
                tema = em.getReference(Tema.class, id);
                tema.getIdtema();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tema with id " + id + " no longer exists.", enfe);
            }
            List<Ejercicio> ejercicioList = tema.getEjercicioList();
            for (Ejercicio ejercicioListEjercicio : ejercicioList) {
                ejercicioListEjercicio.setIdtema(null);
                ejercicioListEjercicio = em.merge(ejercicioListEjercicio);
            }
            em.remove(tema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tema> findTemaEntities() {
        return findTemaEntities(true, -1, -1);
    }

    public List<Tema> findTemaEntities(int maxResults, int firstResult) {
        return findTemaEntities(false, maxResults, firstResult);
    }

    private List<Tema> findTemaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tema.class));
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

    public Tema findTema(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tema.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tema> rt = cq.from(Tema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Tema findByName(String name) {
        try {
            EntityManager em = getEntityManager();
            Tema a = null;
            a = (Tema) em.createNamedQuery("Tema.findByDescripcion").setParameter("descripcion", name).getSingleResult();
            return a;
        } catch (NoResultException ex) {
            return null;
        }

    }
}
