/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.ComplementosPregunta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Ejercicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eduardo
 */
public class ComplementosPreguntaJpaController implements Serializable {

    public ComplementosPreguntaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ComplementosPregunta complementosPregunta) {
        if (complementosPregunta.getEjercicioList() == null) {
            complementosPregunta.setEjercicioList(new ArrayList<Ejercicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejercicio> attachedEjercicioList = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListEjercicioToAttach : complementosPregunta.getEjercicioList()) {
                ejercicioListEjercicioToAttach = em.getReference(ejercicioListEjercicioToAttach.getClass(), ejercicioListEjercicioToAttach.getIdejercicio());
                attachedEjercicioList.add(ejercicioListEjercicioToAttach);
            }
            complementosPregunta.setEjercicioList(attachedEjercicioList);
            em.persist(complementosPregunta);
            for (Ejercicio ejercicioListEjercicio : complementosPregunta.getEjercicioList()) {
                ComplementosPregunta oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListEjercicio = ejercicioListEjercicio.getComplementosPreguntaIdcomplementosPregunta();
                ejercicioListEjercicio.setComplementosPreguntaIdcomplementosPregunta(complementosPregunta);
                ejercicioListEjercicio = em.merge(ejercicioListEjercicio);
                if (oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListEjercicio != null) {
                    oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListEjercicio.getEjercicioList().remove(ejercicioListEjercicio);
                    oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListEjercicio = em.merge(oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListEjercicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ComplementosPregunta complementosPregunta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ComplementosPregunta persistentComplementosPregunta = em.find(ComplementosPregunta.class, complementosPregunta.getIdcomplementosPregunta());
            List<Ejercicio> ejercicioListOld = persistentComplementosPregunta.getEjercicioList();
            List<Ejercicio> ejercicioListNew = complementosPregunta.getEjercicioList();
            List<String> illegalOrphanMessages = null;
            for (Ejercicio ejercicioListOldEjercicio : ejercicioListOld) {
                if (!ejercicioListNew.contains(ejercicioListOldEjercicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ejercicio " + ejercicioListOldEjercicio + " since its complementosPreguntaIdcomplementosPregunta field is not nullable.");
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
            complementosPregunta.setEjercicioList(ejercicioListNew);
            complementosPregunta = em.merge(complementosPregunta);
            for (Ejercicio ejercicioListNewEjercicio : ejercicioListNew) {
                if (!ejercicioListOld.contains(ejercicioListNewEjercicio)) {
                    ComplementosPregunta oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListNewEjercicio = ejercicioListNewEjercicio.getComplementosPreguntaIdcomplementosPregunta();
                    ejercicioListNewEjercicio.setComplementosPreguntaIdcomplementosPregunta(complementosPregunta);
                    ejercicioListNewEjercicio = em.merge(ejercicioListNewEjercicio);
                    if (oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListNewEjercicio != null && !oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListNewEjercicio.equals(complementosPregunta)) {
                        oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListNewEjercicio.getEjercicioList().remove(ejercicioListNewEjercicio);
                        oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListNewEjercicio = em.merge(oldComplementosPreguntaIdcomplementosPreguntaOfEjercicioListNewEjercicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = complementosPregunta.getIdcomplementosPregunta();
                if (findComplementosPregunta(id) == null) {
                    throw new NonexistentEntityException("The complementosPregunta with id " + id + " no longer exists.");
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
            ComplementosPregunta complementosPregunta;
            try {
                complementosPregunta = em.getReference(ComplementosPregunta.class, id);
                complementosPregunta.getIdcomplementosPregunta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The complementosPregunta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ejercicio> ejercicioListOrphanCheck = complementosPregunta.getEjercicioList();
            for (Ejercicio ejercicioListOrphanCheckEjercicio : ejercicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ComplementosPregunta (" + complementosPregunta + ") cannot be destroyed since the Ejercicio " + ejercicioListOrphanCheckEjercicio + " in its ejercicioList field has a non-nullable complementosPreguntaIdcomplementosPregunta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(complementosPregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ComplementosPregunta> findComplementosPreguntaEntities() {
        return findComplementosPreguntaEntities(true, -1, -1);
    }

    public List<ComplementosPregunta> findComplementosPreguntaEntities(int maxResults, int firstResult) {
        return findComplementosPreguntaEntities(false, maxResults, firstResult);
    }

    private List<ComplementosPregunta> findComplementosPreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ComplementosPregunta.class));
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

    public ComplementosPregunta findComplementosPregunta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ComplementosPregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getComplementosPreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ComplementosPregunta> rt = cq.from(ComplementosPregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
