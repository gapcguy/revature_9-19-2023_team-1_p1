package com.revature.p1.banking.DTO;

import java.math.BigDecimal;

public class TransferDTO {
    private int fromAcctNum;
    private int toAcctNum;
    private BigDecimal amountToTransfer;

    public int getFromAcctNum() { return fromAcctNum; }
    public int getToAcctNum() { return toAcctNum; }
    public BigDecimal getTransferAmount() { return amountToTransfer; }
}