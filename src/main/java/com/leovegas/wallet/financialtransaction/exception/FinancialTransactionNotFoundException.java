package com.leovegas.wallet.financialtransaction.exception;

public class FinancialTransactionNotFoundException extends RuntimeException {

    public FinancialTransactionNotFoundException(Long id) {
        super(id.toString());
    }

}
