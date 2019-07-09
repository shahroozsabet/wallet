package com.leovegas.wallet.financialtransaction.exception;

public class AccountBalanceNotEnoughException extends RuntimeException {

    public AccountBalanceNotEnoughException(Double currentBalance) {
        super(currentBalance.toString());
    }
}
