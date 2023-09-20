package com.revature.p1.banking.Controller;

import com.revature.p1.banking.DAO.UserDAO;
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

    private UserService uServ;

    @Autowired
    public UserController(UserService uServ) { this.uServ = uServ; }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(uServ.findAll());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User c) {
        User newUser = uServ.save(c);

        if( newUser == null) { return ResponseEntity.badRequest().build(); }

        return ResponseEntity.accepted().body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        if (id <= 0) { return ResponseEntity.badRequest().build(); }

        User u = uServ.getReferenceById(id);

        if( u == null ) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.ok().body(u);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User c) {
        User updatedUser = uServ.saveAndFlush(c);

        return ResponseEntity.accepted().body(updatedUser);
    }
}
