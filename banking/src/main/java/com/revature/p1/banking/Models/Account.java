package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity                 // Indicates that this class is a JPA entity, representing a database table.
@Table(name="account")  // Specifies the name of the database table for this entity.
@Component              // Marks this class as a Spring component, allowing it to be managed by Spring's IoC container.
public class Account {

    /* Variable annotation usages:
       @Id             - Indicates that the following field is the primary key for this entity.
       @GeneratedValue - Specifies how the primary key values are generated (in this case, auto-increment)
       @Column         - Specifies that a variable corresponds to a database column.
       @ManyToOne      - Defines a many-to-one relationship with the entity to which it is annotating.
       @JoinColumn     - Specifies a foreign key column in this table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(nullable=false, unique = true)
    private Date date;

    @Column(nullable = false, unique = false)
    private BigDecimal balance;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private User user;


    // Getter and setter methods.

    /**
     * Getter method for retrieving the Account ID.
     * @return an account ID.
     */
    public int getAccountId() { return accountId; }

    /**
     * Getter method for retrieving the date.
     * @return the date.
     */
    public Date getDate()     { return date; }

    /**
     * Getter method for retrieving the balance.
     * @return the balance.
     */
    public BigDecimal getBalance() { return balance; }

    /**
     * Getter method for retrieving the user.
     * @return
     */
    public User getUser() { return user; }

    /**
     * Setter method for setting the Account id
      * @param accountId is the Account ID.
     */
    public void setAccountId(int accountId) { this.accountId = accountId; }

    /**
     * Setter method for setting the date.
     * @param date is the Date.
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * Setter method for setting the balance.
     * @param balance is the balance.
     */
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    /**
     * Setter method for setting the user.
     * @param user is the user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Override the toString() builtin to provide a human-readable representation of the Account Object.
     * @return a human-readable representation of the account object.
     */
    @Override
    public String toString() {
        return
                "accountId=" + accountId +
                ", date=" + date +
                ", balance=" + balance +
                ", userId=" + user.getUserId() +
                '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId;
    }

}
