/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.FormulaDr;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Eduardo
 */
public class FormulaDrJpaController implements Serializable {

    public FormulaDrJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormulaDr formulaDr) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formulaDr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormulaDr formulaDr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formulaDr = em.merge(formulaDr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formulaDr.getIdformulaDr();
                if (findFormulaDr(id) == null) {
                    throw new NonexistentEntityException("The formulaDr with id " + id + " no longer exists.");
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
            FormulaDr formulaDr;
            try {
                formulaDr = em.getReference(FormulaDr.class, id);
                formulaDr.getIdformulaDr();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formulaDr with id " + id + " no longer exists.", enfe);
            }
            em.remove(formulaDr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormulaDr> findFormulaDrEntities() {
        return findFormulaDrEntities(true, -1, -1);
    }

    public List<FormulaDr> findFormulaDrEntities(int maxResults, int firstResult) {
        return findFormulaDrEntities(false, maxResults, firstResult);
    }

    private List<FormulaDr> findFormulaDrEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormulaDr.class));
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

    public FormulaDr findFormulaDr(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormulaDr.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormulaDrCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormulaDr> rt = cq.from(FormulaDr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public FormulaDr MostrarFR(int id_dr){
        FormulaDr aux=null;
        EntityManager em = getEntityManager();
        Query a = em.createQuery("SELECT a FROM FormulaDr a WHERE a.iddimensionRelevante =:id");
        a.setParameter("id", id_dr);   
        aux = (FormulaDr) a.getSingleResult();
        return aux;
    }
    
}
