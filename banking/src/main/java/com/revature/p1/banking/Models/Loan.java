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
}
