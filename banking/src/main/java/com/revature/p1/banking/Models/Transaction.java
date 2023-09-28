package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="transaction")
@Component
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @OneToOne
    @JoinColumn(name = "fromAccount",referencedColumnName = "accountId")
    private Account fromAccount;


    // Foreign keys: TODO: EVALUATE FOR PROPER MULTIPLICITY
    @OneToOne
    @JoinColumn(name = "toAccount",referencedColumnName = "accountId")
    private Account toAccount;

    @Column(unique = false, nullable = false)
    private BigDecimal transactionAmount;

    @Column(unique = false, nullable = false)
    private Timestamp transactionDateTime;



    public Transaction() {}

    public Transaction(long transactionId, Account fromAccount, Account toAccount, BigDecimal transactionAmount, Timestamp transactionDateTime) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transactionAmount = transactionAmount;
        this.transactionDateTime = transactionDateTime;
    }
}
