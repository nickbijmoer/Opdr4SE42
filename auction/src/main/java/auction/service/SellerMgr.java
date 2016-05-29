package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SellerMgr {

    private ItemDAO itemDAO;
    private EntityManager em = Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU").createEntityManager();
    public SellerMgr() {
        itemDAO = new ItemDAOJPAImpl();
    }

   
    public Item offerItem(User seller, Category cat, String description) {
     
        Item item = new Item(seller, cat, description);
        
        em.getTransaction().begin();
        try {
           

           itemDAO.create(item); 
           seller.addItem(item);
        } catch (Exception e) {
        }
        
        em.getTransaction().commit();
        return item;    
       
        
    }
    
     
    public boolean revokeItem(Item item) {
        if (item.getHighestBid() == null) {
            itemDAO.remove(item);
            return true;
        }
        return false;
    }
}