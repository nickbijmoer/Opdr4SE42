package auction.domain;

import java.io.Serializable;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Bid implements Serializable{

   
    @Id
    private Long id;

    @Column
    private FontysTime time;

    @ManyToOne(cascade = CascadeType.MERGE)
    @CascadeOnDelete
    private User buyer;

    @Embedded
    private Money amount;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable=false)
    @CascadeOnDelete
    private Item item;
    
    public Bid() {
    }

    public Bid(User buyer, Money amount) {
        this.buyer = buyer;
        this.amount = amount;
    }

    public FontysTime getTime() {
        return time;
    }
    
    public Item getItem(){
        return item;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }
}