package com.revature.p1.banking.Controller;

import com.revature.p1.banking.DAO.CustomerDAO;
import com.revature.p1.banking.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    private CustomerDAO cDAO;

    @Autowired
    public CustomerController(CustomerDAO cDAO) { this.cDAO = cDAO; }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok().body(cDAO.findAll());
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) {
        Customer newCust = cDAO.save(c);

        if( newCust == null) { return ResponseEntity.badRequest().build(); }

        return ResponseEntity.accepted().body(newCust);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        if (id <= 0) { return ResponseEntity.badRequest().build(); }

        Customer c = cDAO.getReferenceById(id);

        if( c == null ) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.ok().body(c);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) {
        Customer updatedCustomer = cDAO.saveAndFlush(c);

        return ResponseEntity.accepted().body(updatedCustomer);
    }
}
