package com.revature.p1.banking.DTO;

import java.math.BigDecimal;

public class TransferDTO {
    private int fromAcctNum;
    private int toAcctNum;
    private BigDecimal amountToTransfer;

    // Constructors.
    /**
     * Default Constructor.
     */
    public TransferDTO() {}

    /**
     * Parameterized constructor with the origination account number, the destination account number, and the amount
     * to be transferred.
     * @param fromAcctNum       is the origination account number.
     * @param toAcctNum         is the destination account number.
     * @param amountToTransfer  is the amount to be transferred.
     */
    public TransferDTO(int fromAcctNum, int toAcctNum, BigDecimal amountToTransfer) {
        this.fromAcctNum = fromAcctNum;
        this.toAcctNum = toAcctNum;
        this.amountToTransfer = amountToTransfer;
    }

    // Getter and setter methods.
    /**
     * Getter method for retrieving the origination account number.
     * @return the origination account number.
     */
    public int getFromAcctNum() { return fromAcctNum; }

    /**
     * Getter method for retrieving the destination account number.
     * @return the destination account number.
     */
    public int getToAcctNum() { return toAcctNum; }

    /**
     * Getter method for retrieving the transfer amount.
     * @return the transfer amount.
     */
    public BigDecimal getTransferAmount() { return amountToTransfer; }

    /**
     * Setter method for setting the origination account number.
     * @param fromAcctNum is the origination account number.
     */
    public void setFromAcctNum(int fromAcctNum) { this.fromAcctNum = fromAcctNum; }

    /**
     * Setter method for setting the destination account number.
     * @param toAcctNum is the destination account number.
     */
    public void setToAcctNum(int toAcctNum) { this.toAcctNum = toAcctNum; }

    /**
     * Setter method for setting the transfer amount.
     * @param amountToTransfer is the loan amount to set.
     */
    public void setAmountToTransfer(BigDecimal amountToTransfer) { this.amountToTransfer = amountToTransfer; }
}