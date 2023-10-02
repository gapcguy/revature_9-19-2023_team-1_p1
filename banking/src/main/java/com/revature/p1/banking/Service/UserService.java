package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service                        // Mark this class as a Spring service component.
public class UserService {

    private UserDAO userDAO; // Declare a private variable to hold an instance of UserDAO.

    /**
     * Default Constructor.
     */
    public UserService() {}

    // Constructor with dependency injection of UserDAO.
    @Autowired
    public UserService(UserDAO userDAO){  this.userDAO = userDAO;  }

    /**
     * Retrieve all users.
     * @return all users.
     */
    public List<User> findAll(){ return userDAO.findAll();  }

    /**
     * Save a new user account
     * @param u the user
     * @return a saved user.
     */
    public User save(User u){ return userDAO.save(u); }

    /**
     * Finds a user by their ID.
     * @param id the ID of the user.
     * @return the user.
     */
    public User findById(int id){ return userDAO.getReferenceById(id); }

    /**
     * Finds a user by their username.
     * @param username the username of the user.
     * @return the user.
     */
    public User findByUsername(String username) {
        // if there's no username in the search query, throw an exception.
        if (username.equals(null) || username.equals("")) {
            throw new IllegalArgumentException("Cannot find a user without a username");
        }

        // Otherwise, let's search for the username.
        Optional<User> u = userDAO.findByUsername(username);

        // If the returned user is present in the optional, get the user. Otherwise, throw an exception.
        if(u.isPresent()) { return u.get(); }
        else              { throw new IllegalArgumentException(); }
    }

    /**
     * Allows for user logins
     * @param user the user object to log in.
     * @return           the retrieved user.
     * @throws Exception indicating that a login attempt must have a username and matching password, and a generic for
     *                   other unspecified issues.
     */
    public User login(User user) throws Exception {
        // Retrieve the username and password from the request body.
        String username = user.getUsername();
        String password = user.getPassword();

        // If there's no username specified in the request body, throw an exception...
        if (username.equals(null) || username.equals("")) {
            throw new IllegalArgumentException("Cannot find a user without a username");
        }

        // ...otherwise, search for the username.
        Optional<User> u = userDAO.findByUsername(username);

        // If the user is present based on the username, retrieve it and check for a matching password.
        if(u.isPresent()) {
            User retrieved = u.get();
            // If the password matches what's held in the database, then retrieve the user...
            if(retrieved.getPassword().equals(password)){ return retrieved; }
            // ...otherwise, throw an exception stating that the password was incorrect.
            else { throw new Exception("incorrect password"); }
        }
        // If the user is not present, throw an exception.
        else { throw new Exception("User not found."); }
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
            if(userFromDatabase.isPresent()) { return userDAO.save(userFromDatabase.get()); }
            else { throw new IllegalArgumentException("User not found."); }
    }

    /**
     * Update the employee Username (update only the username of a user)
     * @param userId   the ID of the user.
     * @param username the new username.
     * @return         a saved user.
     */
    public User updateEmployeeUsername(int userId, String username) {
        // Gather the employee by ID.
        Optional<User> originalUser = userDAO.findById(userId);

        // If this user is present...
        if(originalUser.isPresent()) {
            // Get the user...
            User userToUpdate = originalUser.get();
            // then Update the username.
            userToUpdate.setUsername(username);
            // Return the user to the database after we're done.
            return userDAO.save(userToUpdate);
        } else {
            // If the user isn't present, full-stop cease the attempt.
            throw new IllegalArgumentException("User not found. aborting update");
        }
    }

    /**
     * Delete a user by the user's ID.
     * @param id The ID of the user.
     * @return the deleted user.
     */
    public User deleteById(int id) {
        /* Note from MBW/Gapcguy: Let's implement this function without use of a try-catch block.

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
}
