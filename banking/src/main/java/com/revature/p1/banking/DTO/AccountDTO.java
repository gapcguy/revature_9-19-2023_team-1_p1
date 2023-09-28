package com.revature.p1.banking.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AccountDTO {
    private Integer accountNum;
    private Date date;
    private BigDecimal balance;
    private int userId;

    public Integer getAccountNum() {
        return accountNum;
    }

    /* I was unsure of the constructor types that we would end up needing, so once we establish all needed methods,
        we *could* get rid of the unused constructors. - MBW
     */

    public AccountDTO() {}

    public AccountDTO(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public AccountDTO(Integer accountNum, int userId) {
        this.accountNum = accountNum;
        this.userId = userId;
    }

    public AccountDTO(Integer accountNum, BigDecimal balance) {
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public AccountDTO(Integer accountNum, BigDecimal balance, int userId) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.userId = userId;
    }

    public AccountDTO(Integer accountNum, Date date, BigDecimal balance, int userId) {
        this.accountNum = accountNum;
        this.date = date;
        this.balance = balance;
        this.userId = userId;
    }

    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {this.userId = userId;
    }


}
