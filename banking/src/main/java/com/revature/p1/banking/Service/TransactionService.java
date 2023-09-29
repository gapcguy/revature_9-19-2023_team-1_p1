package com.revature.p1.banking.Service;

import com.revature.p1.banking.Controller.AuthController;
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

@Service
public class TransactionService {

    private final TransactionDAO transactionDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO)  { this.transactionDAO = transactionDAO;       }
    public List<Transaction> findAll          ()              { return transactionDAO.findAll();            }
    public List<BigDecimal> viewStatement(AccountService accountService) throws Exception {
        User currUser = AuthController.getUser();
        if (currUser.getRole() == (User.USER)) {

            return transactionDAO.findTransactionByAccountList(accountService.findAll());
        }else{
            throw new Exception("Only users can view their statement");
        }
    }
    public Transaction       createTransaction(Transaction t) { return transactionDAO.save(t);              }

    // This is a hand-jammed version of this controller that handles loan transactions instead of a full transaction
    // instance.
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
    public Transaction       getReferenceById (int id)        { return transactionDAO.getReferenceById(id); }
    public Transaction       saveAndFlush     (Transaction t) { return transactionDAO.saveAndFlush(t);      }
    public TransactionDAO getTransactionDAO(){ return transactionDAO;}
}
