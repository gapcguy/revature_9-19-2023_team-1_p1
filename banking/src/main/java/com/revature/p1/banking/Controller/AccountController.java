package com.revature.p1.banking.Controller;

import com.revature.p1.banking.DTO.AccountDTO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {

    private final AccountService aServ;

    @Autowired
    public AccountController(AccountService aServ) { this.aServ = aServ; }

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

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() { return ResponseEntity.ok().body(aServ.findAll()); }

    @GetMapping("/{acctNum}")
    public ResponseEntity<Object> getAccountByAcctNum(@PathVariable("acctNum") Integer acctNum) {
        try {
            Account account = aServ.findByAcctNum(acctNum);
            return ResponseEntity.ok().body(account);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createAccount(@RequestBody AccountDTO aDto) {
        try {
            Account newAccount = aServ.createAccount(aDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
