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
    @JoinColumn(name = "accountIdFk")
    private int accountNum;

    @Column(unique = false, nullable = true)
    @OneToOne
    @JoinColumn(name = "accountIdFk")
    private int approvedBy;



    @Column(unique = false, nullable = false)
    private BigDecimal loanAmount;

    @Column(unique = false, nullable = false)
    private Timestamp loanDateTime;


    @Column(unique = false, nullable = false)
    private boolean approved = false;





    public Loan() {}

    public Loan(int accountNum, BigDecimal loanAmount, Timestamp loanDateTime) {
        this.accountNum = accountNum;
        this.loanAmount = loanAmount;
        this.loanDateTime = loanDateTime;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
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
}
