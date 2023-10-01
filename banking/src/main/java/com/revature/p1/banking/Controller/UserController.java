package com.revature.p1.banking.Controller;

import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController             // This class serves as a REST Controller.
@RequestMapping("/users")   // All requests to "/users" will be handled by methods in this class
@CrossOrigin                // Allows handling of HTTP requests from different origins.
public class UserController {

    private final UserService uServ;    // Service class for user-related operations.

    @Autowired // Constructor injection of UserService.
    public UserController(UserService uServ) { this.uServ = uServ; }

    /**
     * Retrieve a list of all users.
     * @return ResponseEntity containing a list of all users or an error message.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() { return ResponseEntity.ok().body(uServ.findAll()); } // Retrieve a list of all users.

    /**
     * Add a new user
     * @param u The user object representing the new user.
     * @return A ResponseEntity containing the newly-created user, or an error.
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User u) {
        // Save a new user using the UserService.
        User newUser = uServ.save(u);
        // Respond with either a bad request or an accepted response based on the result.
        return ( newUser == null) ? ResponseEntity.badRequest().build() : ResponseEntity.accepted().body(newUser);
    }

    /**
     * Retrieve a user by their ID
     * @param id is The ID of the user for retrieval.
     * @return A ResponseEntity containing the User or an error message.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id) {
        // Retrieve a user by their ID using the User Service.
        User u = uServ.findById(id);

        try {
            // Respond with a bad request response if the provided ID is less than or equal to 0,
            // a no content response if the retrieved user value is null, or an ok with the user in the
            // response body if a user is found at the ID.
            return (id <= 0) ? ResponseEntity.badRequest().build() :
                    (u == null) ? ResponseEntity.noContent().build() :
                            ResponseEntity.ok().body(u);
        } catch (IllegalArgumentException e) {
            // If the above throws an exception, respond with a "bad request" response, with the exception message in
            // the body of the response.
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * Retrieve a user by their username.
     *
     * @param username is the username of the user intended for retrieval.
     * @return A ResponseEntity containing the user or an error message.
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<Object> findByUsername(@PathVariable("username") String username) {
        try {
            // Retrieve a user by their username using the UserService.
            User u = uServ.findByUsername(username);
            // Respond with a successful HTTP response containing the retrieved user in the response body.
            return ResponseEntity.ok().body(u);
        } catch (IllegalArgumentException e) {
            // If that doesn't work, print the stack trace and respond with a "bad request" response, with the
            // exception message in the response body.
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Updates a user.
     * @param u is the user object representing the updated user information.
     * @return A ResponseEntity containing the updated user or an error message.
     */
    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User u) {
        try {
            // Update the User's information using the UserService.
            return ResponseEntity.accepted().body(uServ.updateEntireUser(u));
        } catch (IllegalArgumentException e) {
            // If the above doesn't work, print the stack trace and respond with an HTTP response with the exception
            // message in the response body.
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Update a user's username.
     *
     * @param id       is the ID of the user who we are attempting to update.
     * @param username is the new username
     * @return         A ResponseEntity containing the updated user or an error message.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateUserName(@PathVariable("id") int id, @RequestBody String username) {
        try {
            // Update the user's username using the user service.
            return ResponseEntity.accepted().body(uServ.updateEmployeeUsername(id, username));
        } catch (IllegalArgumentException e) {
            // If an exception is thrown, print the stack trace and respond with an HTTP response with the exception
            // message in the response body.
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Delete a user by their ID.
     * @param id is the ID of the user to delete.
     * @return   A ResponseEntity containing the deleted user or an error message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        // Respond with a Bad Request if the provided ID is less than or equal to 0.
        if (id <= 0 ) { return ResponseEntity.badRequest().build(); }

        // Delete a user by their ID using the UserService.
        User deletedUser = uServ.deleteById(id);

        // Return with either a successful response containing the deleted user or Http 404.
        return (deletedUser != null) ? ResponseEntity.ok().body(deletedUser) : ResponseEntity.notFound().build();

    }
}
