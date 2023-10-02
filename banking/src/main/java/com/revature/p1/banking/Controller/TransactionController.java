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

@RestController                  // Class serves as a REST controller.
@RequestMapping("/transactions") // All requests to "/transactions" will be handled by methods in this class.
@CrossOrigin                    // Allows handling HTTP requests from different origins.
public class TransactionController {

    private TransactionService tServ;       // Service class for transaction-related operations
    private AccountService aServ;           // Service class for account-related operations.

    @Autowired                              // Constructor injection of TransactionService and AccountService
    public TransactionController(TransactionService tServ,AccountService aServ) {
        this.tServ = tServ;
        this.aServ = aServ;
    }

    /**
     * Retrieve a list of all transactions.
     * @return  A ResponseEntity containing all transactions or an error.
     */
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() { return ResponseEntity.ok().body(tServ.findAll()); }

    /**
     * Create a new transaction.
     * @param t - The transaction object representing a new transaction.
     * @return A ResponseEntity containing a newly-created transaction or an error message.
     */
    @PostMapping
    public ResponseEntity<Transaction> newTransaction(@RequestBody Transaction t) {
        // Create a new transaction using the TransactionService.
        Transaction newTransaction = tServ.createTransaction(t);

        // If the new transaction contains nothing, return an HTTP 400 code, bad request,
        if ( newTransaction == null ) { return ResponseEntity.badRequest().build(); }

        // Otherwise, return a ResponseEntity to invoke an HTTP 202 response with the new transaction in the Http body.
        return ResponseEntity.accepted().body(newTransaction);
    }

    /**
     * Retrieves a transaction by its id.
     * @param id is the ID of the transaction requested for retrieval.
     * @return   A ResponseEntity containing the retrieved transaction or an error message.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") int id) {
        // If the ID of the transaction is Less than or equal to 0 (which should not happen), then respond with a bad
        // request (http 400) status.
        if (id <= 0) { return ResponseEntity.badRequest().build(); }
        // Otherwise, continue.

        // Retrieve a transaction by its ID using the transaction service and assign it to the Transaction object "t".
        Transaction t = tServ.getReferenceById(id);

        // If "t" happens to be null for whatever reason, return a ResponseEntity with no content (HTTP 204).
        if (t == null ) { return ResponseEntity.noContent().build(); }

        // If the transaction ID is proper and the Transaction object t is not null, use the ResponseEntity
        // to send an HTTP OK (http 200) and include the transaction in the Response body.
        return ResponseEntity.ok().body(t);
    }

    /**
     * View a statement of transactions.
     *
     * @return A ResponseEntity containing a statement of transactions or an error message
     */
    @GetMapping("/statement")
    public ResponseEntity<Object> viewStatment(){
        try {
            // Retrieve a statement of transactions using the TransactionService and AccountService.
            return ResponseEntity.ok().body(tServ.viewStatement());
        } catch (Exception e) {
            // If that fails, print the stack trace and return a 400 status code (BAD REQUEST) and error message.
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Transfer funds between accounts.
     * @param tD is the TransferDTO containing transfer details.
     * @return A ResponseEntity indicating the success or failure of the funds transfer
     */
    @PostMapping("/transfer")
    public ResponseEntity<Object> transferFunds(@RequestBody TransferDTO tD) {

        /*  -- Example usage when calling (done from the request body):
            {
                "fromAcctNum"       : [origination account number]
                "toAcctNum"         : [destination account number]
                "amountToTransfer"  : [some amount]
            }
         */
        try {
            // Extract the transfer details from the TransferDTO.
            int fromAcctNum = tD.getFromAcctNum();
            int toAcctNum = tD.getToAcctNum();
            BigDecimal amountToTransfer = tD.getTransferAmount();

            // Perform a funds transfer between accounts using the AccountService and TransactionService.
            aServ.transfer(tServ, fromAcctNum, toAcctNum, amountToTransfer);

            // Respond with a successful (200 OK) response indicating that the transfer was a success.
            return ResponseEntity.ok("Transfer successful.");
        }
        // If that fails, fall to the exception handler.
        catch (Exception e) {
            // Print a stack trace
            e.printStackTrace();
            // Respond with a bad request and an error message on the Response Body.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}