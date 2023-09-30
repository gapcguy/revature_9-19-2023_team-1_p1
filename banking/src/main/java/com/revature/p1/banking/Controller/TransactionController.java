package com.revature.p1.banking.Controller;


import com.revature.p1.banking.DTO.TransferDTO;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Service.AccountService;
import com.revature.p1.banking.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    private TransactionService tServ;
    private AccountService aServ;

    @Autowired
    public TransactionController(TransactionService tServ,AccountService aServ) {
        this.tServ = tServ;
        this.aServ = aServ;
    }

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

    @GetMapping("/statement")
    public ResponseEntity<Object> viewStatment(){
        try {
            return ResponseEntity.ok().body(tServ.viewStatement(aServ));
        } catch (Exception e) {
            e.printStackTrace();
            //return a 400 status code (BAD REQUEST) and error message
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> transferFunds(@RequestBody TransferDTO tD) {

        // Example JSON:
        try {

            int fromAcctNum = tD.getFromAcctNum();
            int toAcctNum = tD.getToAcctNum();
            BigDecimal amountToTransfer = tD.getTransferAmount();
            aServ.transfer(tServ, fromAcctNum, toAcctNum, amountToTransfer);
            return ResponseEntity.ok("Transfer successful.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
