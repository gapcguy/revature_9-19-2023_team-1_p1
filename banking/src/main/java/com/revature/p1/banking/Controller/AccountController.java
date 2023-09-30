package com.revature.p1.banking.Controller;

import com.revature.p1.banking.DTO.AccountDTO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Service.AccountService;
import com.revature.p1.banking.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController                  // Indicates that this class is a REST controller.
@RequestMapping("/accounts")     // All requests to "/accounts" will be handled by methods in this class.
@CrossOrigin                     // Allows handling of HTTP requests from different origins.
public class AccountController {

    private final AccountService aServ;         // Service class for account-related operations.
    private final TransactionService tServ;     // Service class for transaction-related operations.

    // Constructor injection of AccountService and TransactionService.
    @Autowired
    public AccountController(AccountService aServ, TransactionService tServ) {
        this.aServ = aServ;
        this.tServ = tServ;}

    /*  Notes regarding the structure of the endpoints here:
        We should consider maximizing the use of the base /accounts endpoint by mapping multiple
        functions to it, based on specific need. In this instance, since we have two GetMappers listed here,
        we can be merely use the account number as a dynamic portion of the URI path. All else, such as
        getAllAccounts, createAccount, etc... can utilize various HTTP request types to act as a functionality
        modifier. With its current behavior, an HTTP GET request at the base URI for this controller has the
        intent of getting all accounts, an HTTP POST request will create a new account, and to edit an account,
        I believe that perhaps an HTTP PATCH request might be appropriate, but I'm unsure of what we would edit,
         so I'm open to a brainstorming session for this.

        -MBW
     */

    /**
     * Retrieves a list of all accounts
     *
     * @return ResponseEntity containing a list of all accounts, or an error message.
     */
    @GetMapping
    public ResponseEntity<List<Account>> viewAccounts() {
        // Respond with an HTTP 200 response containing the list of accounts.
        return ResponseEntity.ok().body(aServ.findAll()); }

    /**
     * Retrieves an account by its account number.
     *
     * @param acctNum The account number for which to search.
     * @return ResponseEntity containing the account, or an error message.
     */
    @GetMapping("/{acctNum}")
    public ResponseEntity<Object> getAccountByAcctNum(@PathVariable("acctNum") Integer acctNum) {
        // Try this.
        try {
            // Retrieve an account by its account number using the AccountService
            Account account = aServ.findByAcctNum(acctNum);
            // Respond with an HTTP 200 response containing the account.
            return ResponseEntity.ok().body(account);
        }
        // In the event of an exception to the above, do the following
        catch (IllegalArgumentException e) {
            // Call the printStackTrace exception method and respond with a bad request, outputting the
            // exception message in the response body.
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Create a new bank account.
     * @return A ResponseEntity containing the newly created account, or an error message.
     */
    @PostMapping
    public ResponseEntity<Object> createAccount() {
        try {
            // Create a new account using the AccountService.
            Account newAccount = aServ.createAccount();

            // Respond with a successful response (HTTP 201) with the newly-created account in the response body.
            return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
        }
        // If that doesn't work, perform the following:
        catch (IllegalArgumentException e) {
            // Print the exception stack trace.
            e.printStackTrace();
            // Return a bad request response (HTTP 400) with an error message in the response body.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Handle an account withdrawal request
     * @param amount   The amount to withdraw.
     * @param acctNum  The number of the account from which will be withdrawn
     * @return A ResponseEntity containing the updated account, or an error message
     */
    @PostMapping("/{acctNum}/withdraw")
    public ResponseEntity<Object> withdraw(
            @RequestParam("amount") BigDecimal amount,
            @PathVariable("acctNum") Integer acctNum) {
        // Attempt to perform a withdrawal operation using the Account Service
        try { return ResponseEntity.ok(aServ.withdraw(amount,acctNum,tServ)); }
        // If that doesn't work, perform the following:
        catch (Exception e) {
            // Print a stack trace.
            e.printStackTrace();
            // Return an HTTP Bad request with the message in the exception message in the response body.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{acctNum}/deposit")
    public ResponseEntity<Object> deposit(@RequestParam("amount") BigDecimal amount, @PathVariable("acctNum") Integer acctNum){
        // Attempt to perform a deposit.
        try { return ResponseEntity.ok(aServ.deposit(amount,acctNum,tServ)); }
        // If that doesn't work, perform the following:
        catch (Exception e) {
            // Print a stack trace.
            e.printStackTrace();
            // Return an HTTP Bad request with the message in the exception message in the response body.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
