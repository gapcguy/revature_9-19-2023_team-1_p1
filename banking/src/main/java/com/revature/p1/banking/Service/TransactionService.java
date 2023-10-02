package com.revature.p1.banking.Service;

import com.revature.p1.banking.Controller.AuthController;
import com.revature.p1.banking.DAO.AccountDAO;
import com.revature.p1.banking.DAO.TransactionDAO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// Mark this class as a Spring service component
@Service
public class TransactionService {

    // Declare a private variable to hold an instance of TransactionDAO
    private final TransactionDAO transactionDAO;
    private final AccountDAO accountDAO;

    /**
     * Constructor with dependency injection of TransactionDAO.
     * @param transactionDAO the injected dependency.
     */
    @Autowired
    public TransactionService(TransactionDAO transactionDAO, AccountDAO accountDAO)  {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    /**
     * Finds all transactions.
     * @return all transactions.
     */
    public List<Transaction> findAll          ()              { return transactionDAO.findAll();            }

    /**
     * Enables a statement to be created.
     * Enables a statement to be created.
     * @return               a formatted statement.
     * @throws Exception     notes that an active user session must be present.
     */
    public String viewStatement() throws Exception {
        // determine the current authorized user session based on the authentication
        User currUser = AuthController.getUser();

        // If the currently signed-in user is a customer...
        if (currUser.getRole() == (User.USER)) {

            // Gather all transactions.
            List<Transaction> result = transactionDAO.findTransactionByAccountList(accountDAO.findByUser(currUser));

            // Create a header
            String statement = "=================    " + currUser.getFirstName().toUpperCase()
                    + " "
                    +currUser.getLastName().toUpperCase()+ "'S BANK STATEMENT     ===============\n";

            // Iterate through the list of transactions on the account and build the transaction table.
            for(int i = 0; i < result.size(); i++) { statement += result.get(i).toString(); }
            // return the completed statement.
            return statement;

        } else {
            // Throw the exception if the user session isn't set to the user role.
            throw new Exception("Only users can view their statement");
        }
    }

    /**
     * Create a new transaction
     * @param t an instance of the transaction class.
     * @return a saved transaction.
     */
    public Transaction createTransaction(Transaction t) { return transactionDAO.save(t);              }

    /**
     * Create a new transaction.
     * This is a hand-jammed version of this controller that handles loan transactions instead of a full transaction
     * instance.
     *
     * @param loanId      the ID of the loan.
     * @param account     the ID of the target account
     * @param loanAmount  the amount of the loan.
     * @return            the transaction.
     */
    public Transaction       createTransaction(int loanId, Account account, BigDecimal loanAmount) {
        Transaction transaction = new Transaction();

        transaction.setLoanId(loanId);
        transaction.setRecipientAccount(account);
        transaction.setTransactionAmount(loanAmount);
        transaction.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));

        try {
            // Save the new transaction to the database.
            transaction = transactionDAO.save(transaction);

            // return the transaction object.
            return transaction;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to get a reference to a transaction by its ID.
     * @param id the ID of the target transaction.
     * @return   the referenced transaction.
     */
    public Transaction       getReferenceById (int id)        { return transactionDAO.getReferenceById(id); }

    /**
     * A method to save and flush a transaction
     * @param t an instance of the transaction class.
     * @return  the saved/flushed transaction.
     */
    public Transaction       saveAndFlush     (Transaction t) { return transactionDAO.saveAndFlush(t);      }

    /**
     * A method to get a TransactionDAO instance.
     * @return a transactionDAO instance.
     */
    public TransactionDAO getTransactionDAO(){ return transactionDAO;}
}
