package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
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

    @Column(unique = false, nullable = false)
    @OneToOne
    @JoinColumn(name = "accountIdFk", referencedColumnName = "id")
    private long recipientAccount;

    @Column(unique = false, nullable = true)
    @OneToOne
    @JoinColumn(name = "approvalAccountIdFk", referencedColumnName = "id")
    private User approvedByAccount;


    @Column(unique = false, nullable = false)
    private BigDecimal loanAmount;

    @Column(unique = false, nullable = false)
    private Timestamp loanDateTime;


    @Column(unique = false, nullable = false)
    private boolean approved = false;

    public Loan() {}

    public Loan(Account recipientAccount, BigDecimal loanAmount, Timestamp loanDateTime) {
        this.recipientAccount = recipientAccount.getAccountId();
        this.loanAmount = loanAmount;
        this.loanDateTime = loanDateTime;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public long getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientUser(long recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public User getApprovedByAccount() {
        return approvedByAccount;
    }

    public void setApprovedByUser(User approvedByAccount) {
        this.approvedByAccount = approvedByAccount;
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
                ", approvedByAccount=" + approvedByAccount +
                ", loanAmount=" + loanAmount +
                ", loanDateTime=" + loanDateTime +
                ", approved=" + approved +
                '}';
    }
}
