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

@Entity
public class Bid implements Serializable{

   
    @Id
    private Long id;

    @Column
    private FontysTime time;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User buyer;

    @Column
    private Money amount;
    
    public Bid() {
    }

    public Bid(User buyer, Money amount) {
        this.buyer = buyer;
        this.amount = amount;
    }

    public FontysTime getTime() {
        return time;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }
}