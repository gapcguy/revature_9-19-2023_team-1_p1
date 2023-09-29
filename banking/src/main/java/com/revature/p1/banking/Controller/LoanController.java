package com.revature.p1.banking.Controller;

import com.revature.p1.banking.DTO.LoanDTO;
import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Models.User;
import com.revature.p1.banking.Service.LoanService;
import com.revature.p1.banking.Service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/loans")
@CrossOrigin
public class LoanController {

    private LoanService loanService;
    private TransactionService transactionService;

    @Autowired
    public LoanController(LoanService lServ) { this.loanService = lServ; }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok().body(loanService.findAll());
    }

    @PostMapping("/newLoan")
    public ResponseEntity<Object> createLoan(@RequestBody LoanDTO lDTO, HttpSession session) {

        // First check - Let's ensure that a user session is present in the client.
        User currentUser = (User) session.getAttribute("currentUser");
        // If there is no user session set, deny access.
        if (currentUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("You must be logged in to do that.");
        }

        // Second check - See Use case diagram.
        // It is expected that the user is the only role that can request a loan.
        if (currentUser.getRole() != 'U') {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Only users can request for a loan.");
        }
        Integer accountId = Math.toIntExact(lDTO.getAccountId());
        BigDecimal loanAmount = lDTO.getLoanAmount();

        Loan newLoan = loanService.createLoanRequest(currentUser, accountId, loanAmount);

        if (newLoan != null) {
            return ResponseEntity.ok().body("Your Loan request has been successfully processed.");
        } else {
            return ResponseEntity.badRequest().body("Failed to process the loan request");
        }
    }

    @PostMapping("/acceptLoan/{loanId}")
    public ResponseEntity<Object> acceptLoan(@PathVariable("loanId") Long loanId, HttpSession session) {
        //TODO Post method to accept a loan.
        //This loan patch will accept a loan. After accepting the loan, will need to transfer the money from the loan to the Account for the account_id_fk. Doing this will require a new transaction.
        //Essentially this request requires logic across multiple tables.
        //REQUIRE SESSIONS DATA FOR A USER WITH A ROLE THAT HAS ELEVATED PERMISSIONS

        // Get the user session and check if an account is logged in.
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("You must be logged in to do that.");
        }

        // See Use case diagram for role permissions. The User *must* be a manager or employee to proceed
        if (currentUser.getRole() != 'M' || currentUser.getRole() != 'E') {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You must be a Manager to perform this action");
        }

        if (loanService.acceptLoan(currentUser, loanId)) {
            Transaction transaction = transactionService.createTransaction(
                    loanId,
                    loanService.findById(loanId).getRecipientAccount().getAccountId(),
                    loanService.findById(loanId).getLoanAmount()
                    );

            if (transaction != null) { return ResponseEntity.ok().body("Loan accepted and transaction complete."); }
            else { return ResponseEntity.badRequest().body("Failed to create transaction"); }
        } else { return ResponseEntity.badRequest().body("Failed to accept loan"); }
    }

}
