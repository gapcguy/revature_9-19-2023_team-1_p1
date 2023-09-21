package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.TransactionDAO;
import com.revature.p1.banking.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private TransactionDAO transactionDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO) { this.transactionDAO = transactionDAO; }
    public List<Transaction> findAll() { return transactionDAO.findAll(); }
    public Transaction createTransaction(Transaction t) { return transactionDAO.save(t); }
    public Transaction getReferenceById(int id) {return transactionDAO.getReferenceById(id);}
    public Transaction saveAndFlush(Transaction t) { return transactionDAO.saveAndFlush(t); }

}
