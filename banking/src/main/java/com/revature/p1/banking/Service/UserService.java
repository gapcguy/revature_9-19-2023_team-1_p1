package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.RoleDAO;
import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.Models.Role;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;
    private RoleDAO roleDAO;

    public UserService() {}
    @Autowired
    public UserService(UserDAO userDAO){  this.userDAO = userDAO;  }

    public List<User> findAll(){ return userDAO.findAll();  }
    public User save(User u){ return userDAO.save(u); }
    public User findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Employees with an ID of 0 or less cannot exist");
        }
        Optional<User> user = userDAO.findById(id);

        if (user.isPresent()) { return user.get(); }
        else { throw new IllegalArgumentException("Employee id " + id + "does not exist"); }
    }
    public User findByUsername(String username) {
        if (username.equals(null) || username.equals("")) {
            throw new IllegalArgumentException("Cannot find a user without a username");
        }

        Optional<User> u = userDAO.findByUsername(username);

        // If the returned user is present in the optional,
        if(u.isPresent()) { return u.get(); }
        else              { throw new IllegalArgumentException(); }
    }

    public User updateEntireUser(User u) {
        if(u.getUsername().equals(null) || u.getUsername().equals("")) {
            throw new IllegalArgumentException("Username must not update to null!");
        }

        if(u.getPassword().equals(null) || u.getPassword().equals("")) {
            throw new IllegalArgumentException("Password must not update to null!");
        }

        // Updating in spring data uses the save() method. It's not just for inserts...
        // if save() is used on an existing record, it will update the record instead of creating a new one.
            Optional<User> userFromDatabase = userDAO.findByUsername(u.getUsername());
            if(userFromDatabase.isPresent()) { return userDAO.save(u); }
            else { throw new IllegalArgumentException("User not found."); }
    }

    // Patch request (update only the username of a user)
    public User updateEmployeeUsername(int userId, String username) {
        // Gather the employee by ID.
        Optional<User> originalUser = userDAO.findById(userId);

        if(originalUser.isPresent()) {
            User userToUpdate = originalUser.get();
            userToUpdate.setUsername(username);

            return userDAO.save(userToUpdate);
        } else {
            throw new IllegalArgumentException("User not found. aborting update");
        }
    }


    public User deleteById(int id) {
        /* Enjoy! - Michael Warner

            Let's implement this function without use of a try-catch block.

            Conceptually-speaking, Optional is a class used in Java to represent an object that may or may not contain
            a null value. When applied in this context, Optional<User> wraps the result of the 'findById' method from
            Spring Data JPA, whatever that might be.

            Understanding the above, what we are effectively implementing by invoking Optional<User> will enable the
            application with the ability to retrieve *either* a User object should the specified ID be found,
            or a null value should no entry be returned from its call.
         */
        Optional<User> userOptional = userDAO.findById(id);

        /*  Check if the user is present; Should .isPresent return false, we know a user with the id we are attempting
            to query does not exist in the database, and we return null. Otherwise, we shall continue. */
        if (!userOptional.isPresent()) { return null; }

        // Since we have confirmed that a user by the queried ID exists, .get is safe to call without throwing
        // NoSuchElementException;
            User user = userOptional.get();
            // Since the user is confirmed that it not only exists and is retrievable, account deletion can proceed.
            userDAO.deleteById(id);
            // we then respond with the newly-deleted user.
            return user;
    }

    public User insertUser(User u) { return userDAO.save(u); }
}
