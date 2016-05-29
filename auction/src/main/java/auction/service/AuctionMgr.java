package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;

public class AuctionMgr  {

    private ItemDAO itemDAO;
    public AuctionMgr() {
        itemDAO = new ItemDAOJPAImpl();
    }
    
   
    public Item getItem(Long id) {
        return itemDAO.find(id);
    }

  
   
    public List<Item> findItemByDescription(String description) {
        return itemDAO.findByDescription(description);
    }

    
    public Bid newBid(Item item, User buyer, Money amount) {
        return item.newBid(buyer, amount);
    }
}