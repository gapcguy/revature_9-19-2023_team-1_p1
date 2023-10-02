package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity                 // Indicates that this class is a JPA entity, representing a database table.
@Table(name="loan")     // Specifies the name of the database table for this entity.
@Component              // Marks this class as a Spring component, allowing it to be managed by Spring's IoC container.
public class Loan {

    /* Variable annotation usages:
        @Id             - Indicates that the following field is the primary key for this entity.
        @GeneratedValue - Specifies how the primary key values are generated (in this case, auto-increment)
        @Column         - Specifies that a variable corresponds to a database column.
        @OneToOne       - Defines a one-to-one relationship with the entity to which it is annotating.
        @JoinColumn     - Specifies a foreign key column in this table.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account recipientAccount;

    @ManyToOne
    @JoinColumn(name =  "userId")
    private User approvedBy;

    @Column(unique = false, nullable = false)
    private BigDecimal loanAmount;

    @Column(unique = false, nullable = false)
    private Timestamp loanDateTime;

    @Column(unique = false, nullable = false)
    private boolean approved = false;

    // Constructors
    /**
     * Default Constructor.
     */
    public Loan() {}

    /**
     * Parameterized constructor with the recipient account, the loan amount, and the Loan Datestamp/Timestamp
     * @param recipientAccount
     * @param loanAmount
     * @param loanDateTime
     */
    public Loan(Account recipientAccount, BigDecimal loanAmount, Timestamp loanDateTime) {
        this.recipientAccount = recipientAccount;
        this.loanAmount = loanAmount;
        this.loanDateTime = loanDateTime;
    }

    // Getter and setter methods.
    /**
     * Getter method for retrieving the loan id.
     * @return The loan ID.
     */
    public int getLoanId() { return loanId; }

    /**
     * Getter method for retrieving the Recipient account.
     * @return the recipient account
     */
    public Account getRecipientAccount() { return recipientAccount; }

    /**
     * Getter method for retrieving the account of the individual that approved the loan.
     * @return the account of the individual that approved the loan.
     */
    public User getApprovedByAccount() { return approvedBy; }

    /**
     * Getter method for retrieving the amount of the loan.
     * @return the amount of the loan.
     */
    public BigDecimal getLoanAmount() { return loanAmount; }

    /**
     * Getter method for retrieving the datestamp/timestamp of the loan application date.
     * @return the datestamp/timestamp of the loan application date.
     */
    public Timestamp getLoanDateTime() { return loanDateTime; }

    /**
     * The getter method for returning the approval flag.
     * @return the approval flag.
     */
    public boolean isApproved() { return approved; }

     /**
     * The setter method for setting the loan ID.
     * @param loanId is the Loan ID
     */
    public void setLoanId(int loanId) { this.loanId = loanId; }

    /**
     * The setter method for setting the recipient account.
     * @param recipientAccount is the recipient account.
     */
    public void setRecipientAccount(Account recipientAccount) { this.recipientAccount = recipientAccount; }

    /**
     * The setter method for setting the account entity that approved the loan.
     * @param approvedByAccount the account entity that approved the loan.
     */
    public void setApprovedByUser(User approvedByAccount) { this.approvedBy = approvedByAccount; }

    /**
     * The setter method for setting the amount of the loan.
     * @param loanAmount the amount of the loan.
     */
    public void setLoanAmount(BigDecimal loanAmount) { this.loanAmount = loanAmount; }

    /**
     * The setter method for setting the datestamp/timestamp.
     * @param loanDateTime the datestamp/timestamp.
     */
    public void setLoanDateTime(Timestamp loanDateTime) { this.loanDateTime = loanDateTime; }

    /**
     * The setter method for setting the approval flag on the loan.
     * @param approved the approval flag on the loan.
     */
    public void setApproved(boolean approved) { this.approved = approved; }


    /**
     * Override the toString() builtin to provide a human-readable representation of the Loan Object.
     * @return a human-readable representation of the Loan object.
     */
    @Override
    public String toString() {
        String app;
        if(approved){
            app = " approved";
        }else{
            app = " not approved";
        }
        return "loanID:"+ loanId+
                "accountId:" + recipientAccount.getAccountId() +
                "     amount:" + loanAmount +
                "     status:" + app +
                "     date of application:" + loanDateTime +
                "\n";
    }
}
