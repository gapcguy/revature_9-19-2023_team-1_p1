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
    private User recipientUser;

    @Column(unique = false, nullable = true)
    @OneToOne
    @JoinColumn(name = "approvalAccountIdFk", referencedColumnName = "id")
    private User approvedByUser;


    @Column(unique = false, nullable = false)
    private BigDecimal loanAmount;

    @Column(unique = false, nullable = false)
    private Timestamp loanDateTime;


    @Column(unique = false, nullable = false)
    private boolean approved = false;

    public Loan() {}

    public Loan(Account recipientUser, BigDecimal loanAmount, Timestamp loanDateTime) {
        this.recipientUser = recipientUser.getUser();
        this.loanAmount = loanAmount;
        this.loanDateTime = loanDateTime;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public User getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(User recipientUser) {
        this.recipientUser = recipientUser;
    }

    public User getApprovedByUser() {
        return approvedByUser;
    }

    public void setApprovedByUser(User approvedByUser) {
        this.approvedByUser = approvedByUser;
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
                ", recipientUser=" + recipientUser +
                ", approvedByUser=" + approvedByUser +
                ", loanAmount=" + loanAmount +
                ", loanDateTime=" + loanDateTime +
                ", approved=" + approved +
                '}';
    }
}
