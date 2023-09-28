package com.revature.p1.banking.Controller;

import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService uServ;

    @Autowired
    public UserController(UserService uServ) { this.uServ = uServ; }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() { return ResponseEntity.ok().body(uServ.findAll()); }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User c) {
        User newUser = uServ.save(c);
        return ( newUser == null) ? ResponseEntity.badRequest().build() : ResponseEntity.accepted().body(newUser);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id) {

        User u = uServ.findById(id);

        try {
            return (id <= 0) ? ResponseEntity.badRequest().build() :
                    (u == null) ? ResponseEntity.noContent().build() :
                            ResponseEntity.ok().body(u);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    // todo: select method for userName
    @GetMapping("/username/{username}")
    public ResponseEntity<Object> findByUsername(@PathVariable("username") String username) {
        try {
            User u = uServ.findByUsername(username);
            return ResponseEntity.ok().body(u);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User u) {
        try {

            return ResponseEntity.accepted().body(uServ.updateEntireUser(u));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateUserName(@PathVariable("id") int id, @RequestBody String username) {
        try {
            return ResponseEntity.accepted().body(uServ.updateEmployeeUsername(id, username));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        if (id <= 0 ) { return ResponseEntity.badRequest().build(); }

        User deletedUser = uServ.deleteById(id);

        return (deletedUser != null) ? ResponseEntity.ok().body(deletedUser) : ResponseEntity.notFound().build();

    }
}
