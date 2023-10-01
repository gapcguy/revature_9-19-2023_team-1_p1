package com.revature.p1.banking.DTO;

import java.math.BigDecimal;

public class LoanDTO {
    private int accountId; // Account for the loan
    private BigDecimal loanAmount; // Amount requested

    // Constructors.
    /**
     * Default Constructor.
     */
    public LoanDTO() {}

    /**
     * Parameterized constructor with the account ID and Loan amount.
     * @param accountId  is the ID of the account.
     * @param loanAmount is the amount of the loan.
     */
    public LoanDTO(int accountId, BigDecimal loanAmount) {
        this.accountId = accountId;
        this.loanAmount = loanAmount;
    }

    // Getter and setter methods.

    /**
     * Getter method for retrieving the account ID.
     * @return the account ID.
     */
    public int getAccountId() { return accountId; }

    /**
     * Getter method for retrieving the amount of the loan.
     * @return the loan amount.
     */
    public BigDecimal getLoanAmount() { return loanAmount; }

    /**
     * Setter method for setting the account ID.
     * @param accountId is the account ID to set.
     */
    public void setAccountId(int accountId) { this.accountId = accountId; }

    /**
     * Setter method for setting the amount of the loan.
     * @param loanAmount is the amount of the loan to set.
     */
    public void setLoanAmount(BigDecimal loanAmount) { this.loanAmount = loanAmount; }
}