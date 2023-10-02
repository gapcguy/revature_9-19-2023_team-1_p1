package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity                     // Indicates that this class is a JPA entity, representing a database table.
@Table(name="transaction")  // Specifies the name of the database table for this entity.
@Component                  // Marks this class as a Spring component, allowing it to be managed by Spring's IoC container.
public class Transaction {

    /* Variable annotation usages:
        @Id             - Indicates that the following field is the primary key for this entity.
        @GeneratedValue - Specifies how the primary key values are generated (in this case, auto-increment)
        @Column         - Specifies that a variable corresponds to a database column.
        @OneToOne       - Defines a one-to-one relationship with the entity to which it is annotating.
        @ManyToOne      - Defines a many-to-one relationship with the entity to which it is annotating.
        @JoinColumn     - Specifies a foreign key column in this table.
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @OneToOne
    @JoinColumn(name="loanId", referencedColumnName="loanId")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "toAccount",referencedColumnName = "accountId")
    private Account toAccount;

    @Column(unique = false, nullable = false)
    private BigDecimal transactionAmount;

    @Column(unique = false, nullable = false)
    private Timestamp transactionDateTime;

    // Constructors

    /**
     * Default Constructor.
     */
    public Transaction() {}

    /**
     * Parameterized constructor with the Transaction ID, Origination Account, Destination Account, transaction amount,
     * and Transaction datestamp/timestamp.
     * @param transactionId       is the Transaction ID.
     * @param fromAccount         is the Origination Account.
     * @param toAccount           is the Destination Account.
     * @param transactionAmount   is the Transaction Amount.
     * @param transactionDateTime is the Datestamp/Timestamp.
     */
    public Transaction(long transactionId,
                       Account fromAccount,
                       Account toAccount,
                       BigDecimal transactionAmount,
                       Timestamp transactionDateTime) {
        this.transactionId = transactionId;
        this.toAccount = toAccount;
        this.transactionAmount = transactionAmount;
        this.transactionDateTime = transactionDateTime;
    }

    // Getter and Setter methods.

    /**
     * The Getter method to retrieve the Transaction ID.
     * @return the Transaction ID.
     */
    public long getTransactionId() { return transactionId; }

    /**
     * The Getter method to retrieve the origination account.
     * @return the Origination account.
     */

    /**
     * The Getter method to retrieve the Loan.
     * @return the Loan.
     */
    public Loan getLoan() { return loan; }

    /**
     * The Getter method to retrieve the destination account.
     * @return the destination account.
     */
    public Account getToAccount() { return toAccount; }

    /**
     * The Getter method to retrieve the Transaction amount.
     * @return the transaction amount.
     */
    public BigDecimal getTransactionAmount() { return transactionAmount; }

    /**
     * The Getter method to retrieve the Datestamp/Timestamp of the transaction.
     * @return the Datestamp/Timestamp of the transaction.
     */
    public Timestamp getTransactionDateTime() { return transactionDateTime; }

    /**
     * The Setter method to set the Loan ID.
     * @param loanId is the Loan ID.
     */
    public void setLoanId(int loanId) {
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        this.loan = loan;
    }

    /**
     * The Setter method to set the recipient account.
     * @param toAccount is the recipient account.
     */
    public void setRecipientAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    /**
     * The Setter method to set the Transaction amount.
     * @param transactionAmount is the Transaction amount.
     */
    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * The Setter method to set the Timestamp/Datestamp of the transaction.
     * @param transactionDateTime is the Timestamp/Datestamp of the transaction.
     */
    public void setTransactionDateTime(Timestamp transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }



    /**
     * Override the toString() builtin to provide a human-readable representation of the Transaction Object.
     * @return a human-readable representation of the Transaction object.
     */
    @Override
    public String toString() {
        return "accountId:" + toAccount.getAccountId() +
                "     amount:" + transactionAmount +
                "     date:" + transactionDateTime +
                "\n";
    }
}
