package auction.service;

import auction.dao.CategoryDAOJPAImpl;
<<<<<<< HEAD
=======
import auction.domain.Bid;
>>>>>>> origin/master
import org.junit.Ignore;
import javax.persistence.*;
import util.DatabaseCleaner;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import java.util.Iterator;
import nl.fontys.util.Money;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemsFromSellerTest {

   
    private AuctionMgr auctionMgr;
    private RegistrationMgr registrationMgr;
    private SellerMgr sellerMgr;

    public ItemsFromSellerTest() {
    }

    @Before
    public void setUp() throws Exception {
        registrationMgr = new RegistrationMgr();
        auctionMgr = new AuctionMgr();
        sellerMgr = new SellerMgr();
        DatabaseCleaner dc = new DatabaseCleaner(Persistence.createEntityManagerFactory("nl.fhict.se42_auction_jar_1.0-SNAPSHOTPU").createEntityManager());
        dc.clean();
    }

    @Test
 //   @Ignore
    public void numberOfOfferdItems() {

        String email = "ifu1@nl";
        String omsch1 = "omsch_ifu1";
        String omsch2 = "omsch_ifu2";

        User user1 = registrationMgr.registerUser(email);
        assertEquals(0, user1.numberOfOfferedItems());
        
        
        Category cat = new Category("cat2");
        
        CategoryDAOJPAImpl categories = new CategoryDAOJPAImpl();
        categories.create(cat);
        Item item1 = sellerMgr.offerItem(user1, cat, omsch1);

       
        // test number of items belonging to user1
        //assertEquals(0, user1.numberOfOfferedItems());
        assertEquals(1, user1.numberOfOfferedItems());
        
        /*
         *  expected: which one of te above two assertions do you expect to be true?
         *  QUESTION:
         *    Explain the result in terms of entity manager and persistance context.
         */
         
         
        assertEquals(1, item1.getSeller().numberOfOfferedItems());


        User user2 = registrationMgr.getUser(email);
        assertEquals(1, user2.numberOfOfferedItems());
        Item item2 = sellerMgr.offerItem(user2, cat, omsch2);
        assertEquals(2, user2.numberOfOfferedItems());

        User user3 = registrationMgr.getUser(email);
        assertEquals(2, user3.numberOfOfferedItems());

        User userWithItem = item2.getSeller();
        assertEquals(2, userWithItem.numberOfOfferedItems());
        //assertEquals(3, userWithItem.numberOfOfferedItems());
        /*
         *  expected: which one of te above two assertions do you expect to be true?
         *  QUESTION:
         *    Explain the result in terms of entity manager and persistance context.
         */
        
        
        //assertNotSame(user3, userWithItem);
        assertEquals(user3, userWithItem);

    }

    @Test
//    @Ignore
    public void getItemsFromSeller() {
        String email = "ifu1@nl";
        String omsch1 = "omsch_ifu1";
        String omsch2 = "omsch_ifu2";

        Category cat = new Category("cat2");
        CategoryDAOJPAImpl categories = new CategoryDAOJPAImpl();
        categories.create(cat);

        User user10 = registrationMgr.registerUser(email);
        Item item10 = sellerMgr.offerItem(user10, cat, omsch1);
        Iterator<Item> it = user10.getOfferedItems();
        // testing number of items of java object
        assertTrue(it.hasNext());
        
        // now testing number of items for same user fetched from db.
        User user11 = registrationMgr.getUser(email);
        Iterator<Item> it11 = user11.getOfferedItems();
        assertTrue(it11.hasNext());
        it11.next();
        assertFalse(it11.hasNext());

        // Explain difference in above two tests for te iterator of 'same' user

        
        
        User user20 = registrationMgr.getUser(email);
        Item item20 = sellerMgr.offerItem(user20, cat, omsch2);
        Iterator<Item> it20 = user20.getOfferedItems();
        assertTrue(it20.hasNext());
        it20.next();
        assertTrue(it20.hasNext());


        User user30 = item20.getSeller();
        Iterator<Item> it30 = user30.getOfferedItems();
        assertTrue(it30.hasNext());
        it30.next();
        assertTrue(it30.hasNext());

    }
    
     @Test
    public void biDirectionalTest() {
        User user1 = registrationMgr.registerUser("nick@bijmoer.nl");
        User user2 = registrationMgr.registerUser("bart@memelink.nl");
        User user3 = registrationMgr.registerUser("thijs@memelink.nl");
        Money money = new Money(50, "euro");
        Money money2 = new Money(666, "euro");
        
        CategoryDAOJPAImpl cda = new CategoryDAOJPAImpl();
        Category cat = new Category("katten");
        cda.create(cat);
        
        Item kat = sellerMgr.offerItem(user1, cat, "EenKat");       
        
        kat.newBid(user2, money);
        assertEquals(1, kat.getBids().size());
        
        Bid bid1 = kat.getHighestBid();
        
        kat.newBid(user3, money2);
        assertEquals(2, kat.getBids().size());
        
        Bid bid2 = kat.getHighestBid();
        
        assertEquals(bid1.getItem(), kat);
    }
}
