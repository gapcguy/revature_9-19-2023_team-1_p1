package com.revature.p1.banking.DTO;

import java.math.BigDecimal;

public class LoanDTO {
    private Long accountId; // Account for the loan
    private BigDecimal loanAmount; // Amount requested

    public LoanDTO() {}

    public LoanDTO(Long accountId, BigDecimal loanAmount) {
        this.accountId = accountId;
        this.loanAmount = loanAmount;
    }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public BigDecimal getLoanAmount() { return loanAmount; }
    public void setLoanAmount(BigDecimal loanAmount) { this.loanAmount = loanAmount; }
}