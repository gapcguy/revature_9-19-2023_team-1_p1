package com.revature.p1.banking.Service;

import com.revature.p1.banking.Controller.AuthController;
import com.revature.p1.banking.DAO.AccountDAO;
import com.revature.p1.banking.DTO.AccountDTO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service                        // Mark this class as a Spring service component.
public class AccountService {
    private AccountDAO aDAO;     // Declare a private variable to hold an instance of AccountDAO.

    /**
     * Default Constructor.
     */
    public AccountService() { }

    // Constructor with dependency injection of AccountDAO.
    @Autowired
    public AccountService(AccountDAO aDAO) { this.aDAO = aDAO; }

    /**
     * Retrieve a list of accounts.
     * @return a list of accounts associated with the user if the user is a customer, a list of all accounts
     * if the user is a manager or employee; otherwise, return nothing.
     */
    public List<Account> findAll() {
        // Create a new account instance.
        Account acc = new Account();
        // Get the currently-authenticated user.
        User currUser = AuthController.getUser();

        // Print user information (debugging purposes)
        System.out.println("Current user is: " + currUser.toString() + " with a user role of: " + currUser.getRole());

        // Check if the user has a role of "USER" and return accounts associated with the user.
        if (currUser.getRole() == User.USER) { return aDAO.findByUser(currUser); }

        // Check if the user has a role of "MANAGER" or "EMPLOYEE" and return a list of all accounts.
        else if (currUser.getRole() == User.MANAGER || currUser.getRole() == User.EMPLOYEE){ return aDAO.findAll(); }

        // if there is no user session attached, then we should be weary here and not return anything.
        else { return null; }
    }

    /**
     * Find an acocunt by account number.
     * @param acctNum is the account number expressed as an Integer object rather than a primitive.
     * @return the account.
     */
    public Account findByAcctNum(Integer acctNum) {
        // Use of Integer rather than int due to null check. If no account number is supplied, throw an exception.
        if (acctNum == null) { throw new IllegalArgumentException("Cannot find an account without an account number"); }

        // Attempt to find an account by its id.
        Optional<Account> a = aDAO.findByAccountId(acctNum);

        // Check if an account is found and return an account if found.
        if (a.isPresent())  { return a.get(); }
        // if no account is present, throw an exception.
        else                { throw new IllegalArgumentException("Account not found."); }
    }

    /**
     * Create a new account.
     * @return a saved instance of the account.
     */
    public Account createAccount() {
        // Create a new instance of the Account class.
        Account account = new Account();
        // Set the account parameters: date, balance, and associative user (must be signed in to do that).
        account.setDate(new Date());            // Set the Account Decoration date (may not be needed).
        account.setBalance(new BigDecimal(0));
        account.setUser(AuthController.getUser());

        // Save the account to the database.
        return aDAO.save(account);
    }

    /**
     * Withdraw money from an account.
     * @param amount             the amount to withdraw.
     * @param id                 the account ID.
     * @param transactionService an instance of the transaction service.
     * @return                   a withdrawal from an account.
     * @throws Exception         an exception to notify the user of insufficient funds.
     */
    public Account withdraw(BigDecimal amount, int id,TransactionService transactionService) throws Exception {
        // Find the account by its number and pass it to an instance of the Account class.
        Account acc = findByAcctNum(id);
        // Get the balance of the account.
        BigDecimal bal = acc.getBalance();

        // Compare the balance of the account to the requested withdrawal amount.
        if(bal.compareTo(amount) < 0){
            // Notify the user in the event of insufficient funds.
            throw new Exception("Insufficient funds");
        }

        Transaction t = new Transaction();
        t.setRecipientAccount(acc);
        t.setTransactionAmount(amount.negate());
        t.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
        if(AuthController.getUser().getUserId()!=acc.getUser().getUserId()){
            throw new Exception("Cannot Withdraw from Another's Account");
        }
        if(transactionService.getTransactionDAO().save(t) != null) {
            // If funds are sufficient, withdraw the funds from the account.
            return deposit(amount.negate(), id, transactionService);
        }
    }

    /**
     * Deposit money into an account.
     * @param amount             the amount to deposit.
     * @param id                 the account id.
     * @param transactionService an instance of the transaction service.
     * @return                   a deposit into an account.
     * @throws Exception         an exception to notify the user that the account cannot be updated with the deposit.
     */
    public Account deposit(BigDecimal amount, int id,TransactionService transactionService) throws Exception {
        // Find the account by its number and pass it to an instance of the Account class.
        Account acc = findByAcctNum(id);
        // Get the balance of the account.
        BigDecimal bal =  acc.getBalance();

        // Update the account balance by adding the deposited amount.
        acc.setBalance(bal.add(amount));

        // Create a new transaction record.
        Transaction t = new Transaction();
        t.setRecipientAccount(acc);
        t.setTransactionAmount(amount);
        t.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));

        // If the transaction body can be saved...
        if(transactionService.getTransactionDAO().save(t) != null){
            // ...then save the transaction and return the updated account information.
            return aDAO.save(acc);
        }else{
            // Otherwise, notify the customer that the transaction could not be completed.
            throw new Exception("Unable to Complete Transaction");
        }
    }

    /**
     * Transfer money between accounts
     * @param transactionService an instance of the transaction service.
     * @param fromAcctNum        the origination account number.
     * @param toAcctNum          the destination account number.
     * @param amountToTransfer   the amount to be transferred.
     * @throws Exception         an exception to notify the user of insufficient funds in the origination account.
     */
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
            // If there's not enough to cover the transaction, throw the exception.
            throw new Exception("Insufficient funds for transfer");
        }
    }
}
