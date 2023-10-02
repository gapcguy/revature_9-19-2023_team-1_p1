package com.revature.p1.banking.Controller;

import com.revature.p1.banking.DTO.LoanDTO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Service.AccountService;
import com.revature.p1.banking.Service.LoanService;
import com.revature.p1.banking.Service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController                 // Class serves as a REST controller.
@RequestMapping("/loans")       // All requests to "/loans" will be handled by methods in this class.
@CrossOrigin                    // Allows handling HTTP requests from different origins.
public class LoanController {

    private LoanService loanService;                // Service class for loan-related operations
    private AccountService accountService;          // Service class for account-related operations.
    private TransactionService transactionService;  // Service class for transaction-related operations.

    @Autowired              // Constructor injection of LoanService, AccountService, and TransactionService.
    public LoanController(LoanService lServ, AccountService aServ, TransactionService tServ) {
        this.accountService = aServ;
        this.loanService = lServ;
        this.transactionService = tServ;
    }

    /**
     * Create a new Loan.
     * @param lDTO  Contains loan details.
     * @return      A ResponseEntity containing the newly-created loan, or an error.
     */
    @PostMapping("/newLoan")
    public ResponseEntity<Object> createLoan(@RequestBody LoanDTO lDTO) {
        try {
            // Apply for a new loan using LoanService and associate it with an account.
            Loan newLoan = loanService.apply(lDTO, accountService.findByAcctNum(lDTO.getAccountId()));

            // Return with an HTTP 200 containing the newly-created loan application.
            return ResponseEntity.ok(newLoan);
        }
        // If the above doesn't work:
        catch (Exception e) {
            // Print the exception's stack trace.
            e.printStackTrace();
            //return a 400 status code (BAD REQUEST) and error message
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Accept a loan.
     *
     * @param loanId The id of the loan to be
     * @return A ResponseEntity containing a singular loan application
     */
    @PostMapping("/{loanId}")
    public ResponseEntity<Object> acceptLoan(@PathVariable("loanId") int loanId) {
        try {
            // Accept a loan using the LoanService and create a transaction.
            Transaction transaction = loanService.acceptLoan(loanId, transactionService);

            // Respond with a successful response containing the transaction.
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            // Print a stack trace.
            e.printStackTrace();
            // return a 400 status code (BAD REQUEST) and error message
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * View all loans.
     *
     * @return a ResponseEntity containing a list of loans, or an error message.
     */
    @GetMapping
    public ResponseEntity<Object> viewLoans() {
        try { return ResponseEntity.ok().body(loanService.findAll(accountService)); }  // Retrieve a list of all loans using the loanService.
        // If the loans cannot be accessed, print the stack trance and return an HTTP 400 status code.
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
