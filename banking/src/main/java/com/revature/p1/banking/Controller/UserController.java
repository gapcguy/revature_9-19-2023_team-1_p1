package com.revature.p1.banking.Controller;

import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class UserController {

    private UserDAO cDAO;

    @Autowired
    public UserController(UserDAO cDAO) { this.cDAO = cDAO; }

    @GetMapping
    public ResponseEntity<List<User>> getAllCustomers() {
        return ResponseEntity.ok().body(cDAO.findAll());
    }

    @PostMapping
    public ResponseEntity<User> addCustomer(@RequestBody User c) {
        User newCust = cDAO.save(c);

        if( newCust == null) { return ResponseEntity.badRequest().build(); }

        return ResponseEntity.accepted().body(newCust);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getCustomerById(@PathVariable("id") int id) {
        if (id <= 0) { return ResponseEntity.badRequest().build(); }

        User c = cDAO.getReferenceById(id);

        if( c == null ) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.ok().body(c);
    }

    @PutMapping
    public ResponseEntity<User> updateCustomer(@RequestBody User c) {
        User updatedUser = cDAO.saveAndFlush(c);

        return ResponseEntity.accepted().body(updatedUser);
    }
}
