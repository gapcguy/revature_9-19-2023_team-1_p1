package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.AccountDAO;
import com.revature.p1.banking.DAO.UserDAO;
import com.revature.p1.banking.DTO.AccountDTO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Account> findAll() { return aDAO.findAll(); }

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

        // Adds user. If this is a joint account, multiple user IDs can be associated with it.
        // Let's break down the code.
        // 1. Ensure at least one user ID is present for this operation.
        if (aDTO.getUserIds() != null && !aDTO.getUserIds().isEmpty()) {
            // 2. Instantiate an ArrayList to hold all assigned users.
            List<User> users = new ArrayList<>();
            // 3. For each userId present in the Account DTO's target user IDs, perform the following:
            for (Integer userId : aDTO.getUserIds()) {
                // 3a. Establish a new instance of the user class.
                User user = new User();
                // 3b. Set the user ID of the user, taking in our specific user id at the current iteration.
                user.setUserId(userId);
                // 3c. add it to the list of users for this account.
                users.add(user);
            }
            // 4. Now set the associative users to the account.
            account.setUsers(users);
        }
        // 5. Now save the entry into the database
        return aDAO.save(account);
    }
}
