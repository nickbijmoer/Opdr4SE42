package auction.dao;

import auction.domain.Category;
import auction.domain.Item;
import java.util.List;

public interface CategoryDAO {

    /**
     *
     * @return number of item instances
     */
    int count();

    /**
     * The item is persisted. If a item with the same id allready exists an EntityExistsException is thrown
     * @param category
     */
    void create(Category category);

    /**
     * Merge the state of the given item into persistant context. If the item did not exist an IllegalArgumentException is thrown
     * @param category
     */
    void edit(Category category);

    /**
     *
     * @param id
     * @return the found entity instance or null if the entity does not exist
     */
    Category find(String description);

    /**
     *
     * @return list of item instances
     */
    List<Category> findAll();
    /**
     *
     * @param description 
     * @return list of item instances having specified description
     */
    List<Category> findByDescription(String description);

    /**
     * Remove the entity instance
     * @param item - entity instance
     */
    void remove(Category category);
}
