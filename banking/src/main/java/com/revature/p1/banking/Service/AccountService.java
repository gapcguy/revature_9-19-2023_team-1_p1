package com.revature.p1.banking.Service;

import com.revature.p1.banking.Controller.AuthController;
import com.revature.p1.banking.DAO.AccountDAO;
import com.revature.p1.banking.DTO.AccountDTO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountDAO aDAO;

    public AccountService() {
    }

    @Autowired
    public AccountService(AccountDAO aDAO) {
        this.aDAO = aDAO;
    }

    public List<Account> findAll() {
        Account acc = new Account();
        User currUser = AuthController.getUser();
        System.out.println(currUser.toString());
        if (currUser.getRole() == User.USER){
            return aDAO.findByUser(currUser);
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
    public Account createAccount() {
        System.out.println("hit create account");
        Account account = new Account();
        account.setDate(new Date());            // Set the Account Decoration date (may not be needed).
        account.setBalance(new BigDecimal(0));
        account.setUser(AuthController.getUser());
        return aDAO.save(account);
    }

    public Account withdraw(BigDecimal amount, int id,TransactionService transactionService) throws Exception {
        Account acc = findByAcctNum(id);
        BigDecimal bal = acc.getBalance();
        if(bal.compareTo(amount) < 0){
            throw new Exception("insufficient funds");
        }
        return deposit(amount.negate(),id,transactionService);
    }

    public Account deposit(BigDecimal amount, int id,TransactionService transactionService) throws Exception {
        Account acc = findByAcctNum(id);
        BigDecimal bal =  acc.getBalance();
        acc.setBalance(bal.add(amount));
        Transaction t = new Transaction();
        t.setRecipientAccount(acc);
        t.setTransactionAmount(amount);
        t.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
        if(transactionService.getTransactionDAO().save(t) != null){
            return aDAO.save(acc);
        }else{
            throw new Exception("Unable to Complete Transaction");
        }
    }

    public void transfer(TransactionService transactionService,
                            int fromAcctNum, int toAcctNum,
                            BigDecimal amountToTransfer)
            throws Exception {

        // Get the initial account balance.
        Account fromAccount = findByAcctNum(fromAcctNum);
        BigDecimal fromAccountBalance = fromAccount.getBalance();

        // CompareTo is used to compare two BigDecimal objects. Essentially it will check the values of both and return
        // a 1 for a greater-than result, a 0 for an equal-to (no difference) result, or a -1 for a less-than result.
        // In this case, we will use this merely to check if the value held in fromAccountBalance, when compared to
        // amountToTransfer, evaluates to true when evaluating for the expression ">= 0"
        int fundsAvailable = fromAccountBalance.compareTo(amountToTransfer);

        // Check if the source account balance (fromAccountBalance) contains the requested amount, and perform the
        // transaction on the condition that there's enough money in the account to cover the transfer.
        if (fundsAvailable >= 0) {
            Account toAccount = findByAcctNum(toAcctNum);
            // withdraw the amount from the origination account.
            withdraw(amountToTransfer, fromAccount.getAccountId(), transactionService);

            // Deposit the withdrawn funds to the destination account.
            deposit(amountToTransfer, toAccount.getAccountId(), transactionService);
        } else {
            throw new Exception("Insufficient funds for transfer");
        }
    }

}
