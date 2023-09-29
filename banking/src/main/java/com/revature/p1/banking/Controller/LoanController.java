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

@RestController
@RequestMapping("/loans")
@CrossOrigin
public class LoanController {

    private LoanService loanService;
    private AccountService accountService;
    private TransactionService transactionService;

    @Autowired
    public LoanController(LoanService lServ, AccountService aServ) {
        this.accountService = aServ;
        this.loanService = lServ;
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok().body(loanService.findAll());
    }

    @PostMapping("{acctNum}/newLoan")
    public ResponseEntity<Object> createLoan(@RequestBody LoanDTO lDTO) {
        try {
            Loan newLoan = loanService.apply(lDTO, accountService.findByAcctNum(lDTO.getAccountId()));
            return ResponseEntity.ok(newLoan);
        } catch (Exception e) {
            e.printStackTrace();
            //return a 400 status code (BAD REQUEST) and error message
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{loanId}")
    public ResponseEntity<Object> acceptLoan(@PathVariable("loanId") int loanId) {
        //TODO Post method to accept a loan.
        //This loan patch will accept a loan. After accepting the loan, will need to transfer the money from the loan to the Account for the account_id_fk. Doing this will require a new transaction.
        //Essentially this request requires logic across multiple tables.
        //REQUIRE SESSIONS DATA FOR A USER WITH A ROLE THAT HAS ELEVATED PERMISSIONS

        // Get the user session and check if an account is logged in.
        try {
            Transaction transaction = loanService.acceptLoan(loanId, transactionService);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            //return a 400 status code (BAD REQUEST) and error message
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
