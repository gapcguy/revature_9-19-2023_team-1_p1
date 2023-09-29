package com.revature.p1.banking.Service;

import com.revature.p1.banking.Controller.AuthController;
import com.revature.p1.banking.DAO.AccountDAO;
import com.revature.p1.banking.DTO.AccountDTO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountDAO aDAO;

    public AccountService() {}
    @Autowired
    public AccountService(AccountDAO aDAO) {
        this.aDAO = aDAO;
    }

    public List<Account> findAll() {
        Account acc = new Account();
        User currUser = (User) AuthController.ses.getAttribute("currUser");
        if(currUser.getRole()==(User.USER)){
            acc.setUser(currUser);
            Example<Account> query = Example.of(acc);
            return aDAO.findAll(query);
        }else{
            return aDAO.findAll();
        }
        }

    public Account findByAcctNum(Integer acctNum) { // Use of Integer rather than int due to null check.
        if (acctNum == null) {
            throw new IllegalArgumentException("Cannot find an account without an account number");
        }

        Optional<Account> a = aDAO.findByAccountId(acctNum);

        if (a.isPresent()) {
            return a.get();
        } else {
            throw new IllegalArgumentException("Account not found.");
        }
    }

    // The createAccount method will require some explanation due to the consideration that the foreach implementation
    // *might* be as clear as mud at face value.
    public Account createAccount(AccountDTO aDTO) {
        Account account = new Account();
        account.setDate(new Date());            // Set the Account Decoration date (may not be needed).
        account.setBalance(aDTO.getBalance());
        account.setUser(AuthController.getUser());
        return aDAO.save(account);
    }

}
