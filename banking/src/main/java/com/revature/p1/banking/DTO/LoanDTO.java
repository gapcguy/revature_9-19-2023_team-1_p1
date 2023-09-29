package com.revature.p1.banking.DTO;

import java.math.BigDecimal;

public class LoanDTO {
    private int accountId; // Account for the loan
    private BigDecimal loanAmount; // Amount requested

    public LoanDTO() {}

    public LoanDTO(int accountId, BigDecimal loanAmount) {
        this.accountId = accountId;
        this.loanAmount = loanAmount;
    }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public BigDecimal getLoanAmount() { return loanAmount; }
    public void setLoanAmount(BigDecimal loanAmount) { this.loanAmount = loanAmount; }
}