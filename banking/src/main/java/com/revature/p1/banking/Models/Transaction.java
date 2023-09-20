package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="transactions")
@Component
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Column(unique = false, nullable = false)
    private int accountNum;

    @Column(unique = false, nullable = false)
    private BigDecimal transactionAmount;

    @Column(unique = false, nullable = false)
    private Timestamp transactionDateTime;

    @OneToOne
    @JoinColumn(name = "fromAccountIdFk")
    private Account fromAccount;


    // Foreign keys: TODO: EVALUATE FOR PROPER MULTIPLICITY
    @OneToOne
    @JoinColumn(name = "toAccountIdFk")
    private Account toAccount;

    public Transaction() {}

    public Transaction(int accountNum, BigDecimal transactionAmount, Timestamp transactionDateTime) {
        this.accountNum = accountNum;
        this.transactionAmount = transactionAmount;
        this.transactionDateTime = transactionDateTime;
    }
}
