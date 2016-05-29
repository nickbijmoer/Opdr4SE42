package auction.service;

import java.util.*;
import auction.domain.User;
import auction.dao.UserDAOCollectionImpl;
import auction.dao.UserDAO;
import auction.dao.UserDAOJPAImpl;

public class RegistrationMgr {

    private UserDAO userDAO;

    public RegistrationMgr() {
        userDAO = new UserDAOJPAImpl();
    }

    
    public User registerUser(String email) {
        if (!email.contains("@")) {
            return null;
        }
        User user = userDAO.findByEmail(email);
        if (user != null) {
            return user;
        }
        user = new User(email);
        userDAO.create(user);
        return user;
    }

    
    public User getUser(String email) {
        return userDAO.findByEmail(email);
    }

    
    public List<User> getUsers() {
        return userDAO.findAll();
    }
    
    
    
    
}