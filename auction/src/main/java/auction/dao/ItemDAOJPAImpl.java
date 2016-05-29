/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.dao;

import auction.domain.Item;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Jelle
 */
public class ItemDAOJPAImpl implements ItemDAO {

    EntityManagerFactory ef = Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU");
    EntityManager em = ef.createEntityManager();

    public ItemDAOJPAImpl() {
       
    }

    @Override
    public int count() {
        Query q = em.createNamedQuery("Item.count", Item.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Item item) {
        if (find(item.getId()) != null) {
            throw new EntityExistsException();
        }

        em.getTransaction().begin();
        try {
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
           em.getTransaction().rollback();
        }
    }

    @Override
    public void edit(Item item) {
        if (find(item.getId()) == null) {
            throw new IllegalArgumentException();
        }

         em.getTransaction().begin();
        try {
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
             em.getTransaction().rollback();
        }
    }

    @Override
    public Item find(Long id) {
        Query q = em.createNamedQuery("Item.findByID", Item.class);
        q.setParameter("id", id);
        try {
            Item item = (Item) q.getSingleResult();
            return item;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Item> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Item.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Item> findByDescription(String description) {
        Query q = em.createNamedQuery("Item.findByDescription", Item.class);
        q.setParameter("description", description);
        try {
            return (List<Item>) q.getResultList();           
            
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void remove(Item item) {
         em.getTransaction().begin();
        try {
            em.remove(em.merge(item));
            em.getTransaction().commit();
        } catch (Exception e) {
             em.getTransaction().rollback();
        }
    }

}