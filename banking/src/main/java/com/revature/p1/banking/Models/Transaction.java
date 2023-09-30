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

    @ManyToOne
    @JoinColumn(name = "fromAccount",referencedColumnName = "accountId")
    private Account fromAccount;

    @OneToOne
    @JoinColumn(name="loanId", referencedColumnName="loanId")
    private Loan loan;

    // Foreign keys: TODO: EVALUATE FOR PROPER MULTIPLICITY
    @ManyToOne
    @JoinColumn(name = "toAccount",referencedColumnName = "accountId")
    private Account toAccount;

    @Column(unique = false, nullable = false)
    private BigDecimal transactionAmount;

    @Column(unique = false, nullable = false)
    private Timestamp transactionDateTime;

    public void setLoanId(int loanId) {
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        this.loan = loan;
    }

    public void setRecipientAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionDateTime(Timestamp transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Transaction() {}

    public Transaction(long transactionId, Account fromAccount, Account toAccount, BigDecimal transactionAmount, Timestamp transactionDateTime) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.transactionAmount = transactionAmount;
        this.transactionDateTime = transactionDateTime;
    }

    @Override
    public String toString() {
        return "accountId:" + toAccount.getAccountId() +
                "     transactionAmount:" + transactionAmount +
                "     transactionDateTime:" + transactionDateTime +
                "\n";
    }
}
