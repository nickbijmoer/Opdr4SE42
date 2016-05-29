package auction.domain;

import java.util.List;
import nl.fontys.util.Money;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.eclipse.persistence.annotations.CascadeOnDelete;


@Entity
@NamedQueries({
    @NamedQuery(name = "Item.findByID", query = "select i from Item as i where i.id = :id"),
    @NamedQuery(name = "Item.count", query = "select count(i) from Item as i"),
    @NamedQuery(name = "Item.find", query = "select i from Item as i where i.id = :id"),
    @NamedQuery(name = "Item.findByDescription", query = "select i from Item as i where i.description = :description")
})
public class Item implements Comparable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private User seller;
    
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private Category category;
    
    
    @Column 
    private String description;
    
    
    @OneToOne
    private Bid highest;
    
    @OneToMany(mappedBy=" item", cascade = CascadeType.PERSIST)
    @CascadeOnDelete
    private List<Bid> bids;

    public Item() {
    }

    
    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
        
    }

   
    public Long getId() {
        return id;
    }

    
    public User getSeller() {
        return seller;
    }
    
    public List<Bid> getBids(){
        return bids;
    }

    
    public Category getCategory() {
        return category;
    }

    
    public String getDescription() {
        return description;
    }

    
    public Bid getHighestBid() {
        return highest;
    }

    
    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount);
        return highest;
    }

    
    public int compareTo(Object arg0) {
        //TODO
        return -1;
    }

    public boolean equals(Object o) {
        if(o == this)
        {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (long) id *
                category.hashCode() *
                seller.hashCode() *
                description.hashCode() *
                highest.hashCode();
    }
    
}