package com.revature.p1.banking.Controller;


import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    private TransactionService tServ;

    @Autowired
    public TransactionController(TransactionService tServ) { this.tServ = tServ;}

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok().body(tServ.findAll());
    }

    @PostMapping
    public ResponseEntity<Transaction> newTransaction(@RequestBody Transaction t) {
        Transaction newTransaction = tServ.createTransaction(t);

        if ( newTransaction == null ) { return ResponseEntity.badRequest().build(); }

        return ResponseEntity.accepted().body(newTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") int id) {
        if (id <= 0) { return ResponseEntity.badRequest().build(); }

        Transaction t = tServ.getReferenceById(id);

        if (t == null ) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.ok().body(t);
    }

}
