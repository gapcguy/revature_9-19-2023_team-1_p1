package com.revature.p1.banking.Models;

import jakarta.persistence.*;
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

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Timestamp getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Timestamp transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountNum=" + accountNum +
                ", transactionAmount=" + transactionAmount +
                ", transactionDateTime=" + transactionDateTime +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                '}';
    }
}
