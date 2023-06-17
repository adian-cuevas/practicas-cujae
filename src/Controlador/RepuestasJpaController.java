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
import Modelo.Ejercicio;
import Modelo.Repuestas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eduardo
 */
public class RepuestasJpaController implements Serializable {

    public RepuestasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Repuestas repuestas) {
        if (repuestas.getEjercicioList() == null) {
            repuestas.setEjercicioList(new ArrayList<Ejercicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejercicio> attachedEjercicioList = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListEjercicioToAttach : repuestas.getEjercicioList()) {
                ejercicioListEjercicioToAttach = em.getReference(ejercicioListEjercicioToAttach.getClass(), ejercicioListEjercicioToAttach.getIdejercicio());
                attachedEjercicioList.add(ejercicioListEjercicioToAttach);
            }
            repuestas.setEjercicioList(attachedEjercicioList);
            em.persist(repuestas);
            for (Ejercicio ejercicioListEjercicio : repuestas.getEjercicioList()) {
                Repuestas oldIdcomplementosRespuestaOfEjercicioListEjercicio = ejercicioListEjercicio.getIdcomplementosRespuesta();
                ejercicioListEjercicio.setIdcomplementosRespuesta(repuestas);
                ejercicioListEjercicio = em.merge(ejercicioListEjercicio);
                if (oldIdcomplementosRespuestaOfEjercicioListEjercicio != null) {
                    oldIdcomplementosRespuestaOfEjercicioListEjercicio.getEjercicioList().remove(ejercicioListEjercicio);
                    oldIdcomplementosRespuestaOfEjercicioListEjercicio = em.merge(oldIdcomplementosRespuestaOfEjercicioListEjercicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Repuestas repuestas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Repuestas persistentRepuestas = em.find(Repuestas.class, repuestas.getIdrespuestas());
            List<Ejercicio> ejercicioListOld = persistentRepuestas.getEjercicioList();
            List<Ejercicio> ejercicioListNew = repuestas.getEjercicioList();
            List<String> illegalOrphanMessages = null;
            for (Ejercicio ejercicioListOldEjercicio : ejercicioListOld) {
                if (!ejercicioListNew.contains(ejercicioListOldEjercicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ejercicio " + ejercicioListOldEjercicio + " since its idcomplementosRespuesta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Ejercicio> attachedEjercicioListNew = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListNewEjercicioToAttach : ejercicioListNew) {
                ejercicioListNewEjercicioToAttach = em.getReference(ejercicioListNewEjercicioToAttach.getClass(), ejercicioListNewEjercicioToAttach.getIdejercicio());
                attachedEjercicioListNew.add(ejercicioListNewEjercicioToAttach);
            }
            ejercicioListNew = attachedEjercicioListNew;
            repuestas.setEjercicioList(ejercicioListNew);
            repuestas = em.merge(repuestas);
            for (Ejercicio ejercicioListNewEjercicio : ejercicioListNew) {
                if (!ejercicioListOld.contains(ejercicioListNewEjercicio)) {
                    Repuestas oldIdcomplementosRespuestaOfEjercicioListNewEjercicio = ejercicioListNewEjercicio.getIdcomplementosRespuesta();
                    ejercicioListNewEjercicio.setIdcomplementosRespuesta(repuestas);
                    ejercicioListNewEjercicio = em.merge(ejercicioListNewEjercicio);
                    if (oldIdcomplementosRespuestaOfEjercicioListNewEjercicio != null && !oldIdcomplementosRespuestaOfEjercicioListNewEjercicio.equals(repuestas)) {
                        oldIdcomplementosRespuestaOfEjercicioListNewEjercicio.getEjercicioList().remove(ejercicioListNewEjercicio);
                        oldIdcomplementosRespuestaOfEjercicioListNewEjercicio = em.merge(oldIdcomplementosRespuestaOfEjercicioListNewEjercicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = repuestas.getIdrespuestas();
                if (findRepuestas(id) == null) {
                    throw new NonexistentEntityException("The repuestas with id " + id + " no longer exists.");
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
            Repuestas repuestas;
            try {
                repuestas = em.getReference(Repuestas.class, id);
                repuestas.getIdrespuestas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The repuestas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ejercicio> ejercicioListOrphanCheck = repuestas.getEjercicioList();
            for (Ejercicio ejercicioListOrphanCheckEjercicio : ejercicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Repuestas (" + repuestas + ") cannot be destroyed since the Ejercicio " + ejercicioListOrphanCheckEjercicio + " in its ejercicioList field has a non-nullable idcomplementosRespuesta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(repuestas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Repuestas> findRepuestasEntities() {
        return findRepuestasEntities(true, -1, -1);
    }

    public List<Repuestas> findRepuestasEntities(int maxResults, int firstResult) {
        return findRepuestasEntities(false, maxResults, firstResult);
    }

    private List<Repuestas> findRepuestasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Repuestas.class));
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

    public Repuestas findRepuestas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Repuestas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRepuestasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Repuestas> rt = cq.from(Repuestas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
