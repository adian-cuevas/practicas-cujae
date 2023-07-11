/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.Instrumento;
import Modelo.Medio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author adian
 */
public class MedioJpaController implements Serializable {

    public MedioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medio medio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(medio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medio medio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            medio = em.merge(medio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medio.getIdmedio();
                if (findMedio(id) == null) {
                    throw new NonexistentEntityException("The medio with id " + id + " no longer exists.");
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
            Medio medio;
            try {
                medio = em.getReference(Medio.class, id);
                medio.getIdmedio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medio with id " + id + " no longer exists.", enfe);
            }
            em.remove(medio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medio> findMedioEntities() {
        return findMedioEntities(true, -1, -1);
    }

    public List<Medio> findMedioEntities(int maxResults, int firstResult) {
        return findMedioEntities(false, maxResults, firstResult);
    }

    private List<Medio> findMedioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medio.class));
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

    public Medio findMedio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medio> rt = cq.from(Medio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Medio Mostrar(String nomb) {
        try {
            Medio experim;
            EntityManager em = getEntityManager();
            experim = (Medio) em.createNamedQuery("Medio.findByNombMedio").setParameter("nombMedio", nomb).getSingleResult();
            return experim;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;

    }

}
