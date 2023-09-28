package com.revature.p1.banking.Controller;

import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Service.LoanService;
import com.revature.p1.banking.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
@CrossOrigin
public class LoanController {

    private LoanService loanService;

    @Autowired
    public LoanController(LoanService lServ) { this.loanService = lServ; }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok().body(loanService.findAll());
    }


    //TODO Post method to create a loan request
    //This loan request will create a new loan with the boolean Accepted equal to false
    //REQUIRES SESSION DATA FOR A USER
    //REQUIRES A PATHPARAM FOR THE ACCOUNT OF THE LOAN, and the BODY will contain the AMOUNT requested

    //TODO Post method to accept a loan.
    //This loan patch will accept a loan. After accepting the loan, will need to transfer the money from the loan to the Account for the account_id_fk. Doing this will require a new transaction.
    //Essentially this request requires logic across multiple tables.
    //REQUIRE SESSIONS DATA FOR A USER WITH A ROLE THAT HAS ELEVATED PERMISSIONS
    

}
