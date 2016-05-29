package auction.domain;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.getAll", query = "select u from User as u"),
    @NamedQuery(name = "User.count", query = "select count(u) from User as u"),
})
public class User implements Serializable {

    @Id
    private String email;
    
    @OneToMany(mappedBy = "seller", cascade = CascadeType.PERSIST)
    Set<Item> offeredItems;

    public User() {
        
    }
    
    public User(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }
    
    public Iterator<Item> getOfferedItems(){
        return offeredItems.iterator();
    }
    
    public void addItem(Item item){
        try {
        
        offeredItems.add(item);
        item.setSeller(this);
       
        } catch (Exception e) {
        }

    }
    
    public int numberOfOfferedItems(){
        return offeredItems.size();
    }
    
    
}