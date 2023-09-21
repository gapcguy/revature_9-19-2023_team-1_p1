package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="loans")
@Component
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;

    @OneToOne
    @JoinColumn(name = "accountId")
    private Account recipientAccount;


    @OneToOne
    @JoinColumn(name =  "userId")
    private User approvedBy;


    @Column(unique = false, nullable = false)
    private BigDecimal loanAmount;

    @Column(unique = false, nullable = false)
    private Timestamp loanDateTime;


    @Column(unique = false, nullable = false)
    private boolean approved = false;

    public Loan() {}

    public Loan(Account recipientAccount, BigDecimal loanAmount, Timestamp loanDateTime) {
        this.recipientAccount = recipientAccount;
        this.loanAmount = loanAmount;
        this.loanDateTime = loanDateTime;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientUser(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public User getApprovedByAccount() {
        return approvedBy;
    }

    public void setApprovedByUser(User approvedByAccount) {
        this.approvedBy = approvedByAccount;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Timestamp getLoanDateTime() {
        return loanDateTime;
    }

    public void setLoanDateTime(Timestamp loanDateTime) {
        this.loanDateTime = loanDateTime;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", recipientAccount=" + recipientAccount +
                ", approvedByAccount=" + approvedBy +
                ", loanAmount=" + loanAmount +
                ", loanDateTime=" + loanDateTime +
                ", approved=" + approved +
                '}';
    }
}
