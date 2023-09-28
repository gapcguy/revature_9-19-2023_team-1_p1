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
}
