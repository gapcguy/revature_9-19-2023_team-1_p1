package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.TransactionDAO;
import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionDAO transactionDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO)  { this.transactionDAO = transactionDAO;       }
    public List<Transaction> findAll          ()              { return transactionDAO.findAll();            }
    public Transaction       createTransaction(Transaction t) { return transactionDAO.save(t);              }

    // This is a hand-jammed version of this controller that handles loan transactions instead of a full transaction
    // instance.
    public Transaction       createTransaction(Long loanId, int recipientAccountId, BigDecimal loanAmount) {
        Transaction transaction = new Transaction();

        transaction.setLoanId(loanId);
        transaction.setRecipientAccountId(recipientAccountId);
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
}
