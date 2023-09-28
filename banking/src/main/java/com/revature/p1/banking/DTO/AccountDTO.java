package com.revature.p1.banking.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AccountDTO {
    private Integer accountNum;
    private Date date;
    private BigDecimal balance;
    private List<Integer> userIds;

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

    public AccountDTO(Integer accountNum, List<Integer> userIds) {
        this.accountNum = accountNum;
        this.userIds = userIds;
    }

    public AccountDTO(Integer accountNum, BigDecimal balance) {
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public AccountDTO(Integer accountNum, BigDecimal balance, List<Integer> userIds) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.userIds = userIds;
    }

    public AccountDTO(Integer accountNum, Date date, BigDecimal balance, List<Integer> userIds) {
        this.accountNum = accountNum;
        this.date = date;
        this.balance = balance;
        this.userIds = userIds;
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

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }


}
