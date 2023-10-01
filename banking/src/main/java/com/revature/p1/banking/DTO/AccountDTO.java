package com.revature.p1.banking.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AccountDTO {
    private Integer accountNum;
    private Date date;
    private BigDecimal balance;
    private int userId;

    /* I was unsure of the constructor types that we would end up needing, so once we establish all needed methods,
        we *could* get rid of the unused constructors. - MBW
     */

    // Constructors.
    /**
     * Default Constructor.
     */
    public AccountDTO() {}

    /**
     * Parameterized constructor with the account number.
     * @param accountNum is the Account number to set.
     */
    public AccountDTO(Integer accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * Parameterized constructor with the account number and user ID.
     * @param accountNum is the account number to set.
     * @param userId     is the user ID to set.
     */
    public AccountDTO(Integer accountNum, int userId) {
        this.accountNum = accountNum;
        this.userId = userId;
    }

    /**
     * Parameterized constructor with the account number and balance
     * @param accountNum is the account number to set.
     * @param balance    is the account balance to set.
     */
    public AccountDTO(Integer accountNum, BigDecimal balance) {
        this.accountNum = accountNum;
        this.balance = balance;
    }

    /**
     * Parameterized constructor with the account number, balance, and user ID.
     * @param accountNum is the account number to set.
     * @param balance    is the account balance to set.
     * @param userId     is the user ID to set.
     */
    public AccountDTO(Integer accountNum, BigDecimal balance, int userId) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.userId = userId;
    }

    /**
     * Parameterized constructor with the account number, date, balance, and user ID.
     * @param accountNum is the account number to set
     * @param date       is the date to set.
     * @param balance    is the balance to set.
     * @param userId     is the user ID to set.
     */
    public AccountDTO(Integer accountNum, Date date, BigDecimal balance, int userId) {
        this.accountNum = accountNum;
        this.date = date;
        this.balance = balance;
        this.userId = userId;
    }

    // Getter and setter methods.
    /**
     * Getter method for retrieving the account number
     * @return The account number.
     */
    public Integer     getAccountNum (                       ) { return accountNum;            }

    /**
     * Getter method for retrieving the date.
     * @return the date.
     */
    public Date        getDate       (                       ) { return date;                  }

    /**
     * Getter method for retrieving the balance.
     * @return the balance.
     */
    public BigDecimal  getBalance    (                       ) { return balance;               }

    /**
     * Getter method for retrieving the user ID.
     * @return the user id.
     */
    public int         getUserId     (                       ) { return userId;                }

    /**
     * Setter method for setting the Account Number.
     * @param accountNum is the account number to set.
     */
    public void        setAccountNum ( Integer    accountNum ) { this.accountNum = accountNum; }

    /**
     * Setter method for setting the date.
     * @param date is the date to set.
     */
    public void        setDate       ( Date       date       ) { this.date = date;             }

    /**
     * Setter method for setting the balance.
     * @param balance is the balance to set.
     */
    public void        setBalance    ( BigDecimal balance    ) { this.balance = balance;       }

    /**
     * Setter method for setting the user ID.
     * @param userId is the user ID to set.
     */
    public void        setUserId     ( int        userId     ) { this.userId = userId;         }
}
