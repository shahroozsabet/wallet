package com.leovegas.wallet.financialtransaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice(annotations = RestController.class)
public class FinancialTransactionControllerAdvice {

    @ExceptionHandler(FinancialTransactionNotFoundException.class)
    public void invalidRemoteCustomerData(FinancialTransactionNotFoundException exception, HttpServletResponse httpServletResponse)
            throws IOException {
        httpServletResponse.sendError(HttpStatus.NOT_FOUND.value(), String.format("Could not find financial transaction: %s", exception.getMessage()));
    }

    @ExceptionHandler(AccountBalanceNotEnoughException.class)
    public void remoteServiceException(AccountBalanceNotEnoughException exception, HttpServletResponse httpServletResponse)
            throws IOException {
        httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), String.format("Account current Balance is not enough: %s", exception.getMessage()));
    }

    @ExceptionHandler(FinancialTransactionIdException.class)
    public void remoteServiceException(FinancialTransactionIdException exception, HttpServletResponse httpServletResponse)
            throws IOException {
        httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

}