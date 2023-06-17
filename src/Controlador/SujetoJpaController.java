/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.Sujeto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.SujetoPosiciones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Eduardo
 */
public class SujetoJpaController implements Serializable {

    public SujetoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sujeto sujeto) {
        if (sujeto.getSujetoPosicionesList() == null) {
            sujeto.setSujetoPosicionesList(new ArrayList<SujetoPosiciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SujetoPosiciones> attachedSujetoPosicionesList = new ArrayList<SujetoPosiciones>();
            for (SujetoPosiciones sujetoPosicionesListSujetoPosicionesToAttach : sujeto.getSujetoPosicionesList()) {
                sujetoPosicionesListSujetoPosicionesToAttach = em.getReference(sujetoPosicionesListSujetoPosicionesToAttach.getClass(), sujetoPosicionesListSujetoPosicionesToAttach.getIdsujetoPosiciones());
                attachedSujetoPosicionesList.add(sujetoPosicionesListSujetoPosicionesToAttach);
            }
            sujeto.setSujetoPosicionesList(attachedSujetoPosicionesList);
            em.persist(sujeto);
            for (SujetoPosiciones sujetoPosicionesListSujetoPosiciones : sujeto.getSujetoPosicionesList()) {
                Sujeto oldIdsujetoOfSujetoPosicionesListSujetoPosiciones = sujetoPosicionesListSujetoPosiciones.getIdsujeto();
                sujetoPosicionesListSujetoPosiciones.setIdsujeto(sujeto);
                sujetoPosicionesListSujetoPosiciones = em.merge(sujetoPosicionesListSujetoPosiciones);
                if (oldIdsujetoOfSujetoPosicionesListSujetoPosiciones != null) {
                    oldIdsujetoOfSujetoPosicionesListSujetoPosiciones.getSujetoPosicionesList().remove(sujetoPosicionesListSujetoPosiciones);
                    oldIdsujetoOfSujetoPosicionesListSujetoPosiciones = em.merge(oldIdsujetoOfSujetoPosicionesListSujetoPosiciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sujeto sujeto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sujeto persistentSujeto = em.find(Sujeto.class, sujeto.getIdsujeto());
            List<SujetoPosiciones> sujetoPosicionesListOld = persistentSujeto.getSujetoPosicionesList();
            List<SujetoPosiciones> sujetoPosicionesListNew = sujeto.getSujetoPosicionesList();
            List<String> illegalOrphanMessages = null;
            for (SujetoPosiciones sujetoPosicionesListOldSujetoPosiciones : sujetoPosicionesListOld) {
                if (!sujetoPosicionesListNew.contains(sujetoPosicionesListOldSujetoPosiciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SujetoPosiciones " + sujetoPosicionesListOldSujetoPosiciones + " since its idsujeto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SujetoPosiciones> attachedSujetoPosicionesListNew = new ArrayList<SujetoPosiciones>();
            for (SujetoPosiciones sujetoPosicionesListNewSujetoPosicionesToAttach : sujetoPosicionesListNew) {
                sujetoPosicionesListNewSujetoPosicionesToAttach = em.getReference(sujetoPosicionesListNewSujetoPosicionesToAttach.getClass(), sujetoPosicionesListNewSujetoPosicionesToAttach.getIdsujetoPosiciones());
                attachedSujetoPosicionesListNew.add(sujetoPosicionesListNewSujetoPosicionesToAttach);
            }
            sujetoPosicionesListNew = attachedSujetoPosicionesListNew;
            sujeto.setSujetoPosicionesList(sujetoPosicionesListNew);
            sujeto = em.merge(sujeto);
            for (SujetoPosiciones sujetoPosicionesListNewSujetoPosiciones : sujetoPosicionesListNew) {
                if (!sujetoPosicionesListOld.contains(sujetoPosicionesListNewSujetoPosiciones)) {
                    Sujeto oldIdsujetoOfSujetoPosicionesListNewSujetoPosiciones = sujetoPosicionesListNewSujetoPosiciones.getIdsujeto();
                    sujetoPosicionesListNewSujetoPosiciones.setIdsujeto(sujeto);
                    sujetoPosicionesListNewSujetoPosiciones = em.merge(sujetoPosicionesListNewSujetoPosiciones);
                    if (oldIdsujetoOfSujetoPosicionesListNewSujetoPosiciones != null && !oldIdsujetoOfSujetoPosicionesListNewSujetoPosiciones.equals(sujeto)) {
                        oldIdsujetoOfSujetoPosicionesListNewSujetoPosiciones.getSujetoPosicionesList().remove(sujetoPosicionesListNewSujetoPosiciones);
                        oldIdsujetoOfSujetoPosicionesListNewSujetoPosiciones = em.merge(oldIdsujetoOfSujetoPosicionesListNewSujetoPosiciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sujeto.getIdsujeto();
                if (findSujeto(id) == null) {
                    throw new NonexistentEntityException("The sujeto with id " + id + " no longer exists.");
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
            Sujeto sujeto;
            try {
                sujeto = em.getReference(Sujeto.class, id);
                sujeto.getIdsujeto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sujeto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SujetoPosiciones> sujetoPosicionesListOrphanCheck = sujeto.getSujetoPosicionesList();
            for (SujetoPosiciones sujetoPosicionesListOrphanCheckSujetoPosiciones : sujetoPosicionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sujeto (" + sujeto + ") cannot be destroyed since the SujetoPosiciones " + sujetoPosicionesListOrphanCheckSujetoPosiciones + " in its sujetoPosicionesList field has a non-nullable idsujeto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sujeto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sujeto> findSujetoEntities() {
        return findSujetoEntities(true, -1, -1);
    }

    public List<Sujeto> findSujetoEntities(int maxResults, int firstResult) {
        return findSujetoEntities(false, maxResults, firstResult);
    }

    private List<Sujeto> findSujetoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sujeto.class));
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

    public Sujeto findSujeto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sujeto.class, id);
        } finally {
            em.close();
        }
    }

    public int getSujetoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sujeto> rt = cq.from(Sujeto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public Sujeto Mostrar(int post){
        Sujeto sujeto;
        EntityManager em = getEntityManager();
        List<Sujeto> listaejercicio=findSujetoEntities();
        sujeto=listaejercicio.get(post);
        return sujeto;
    }
}
