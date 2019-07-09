package com.leovegas.wallet.financialtransaction;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class FinancialTransactionBaseDTO {

    private Long id;
    private Date transactionDate;
    @NotNull
    private Long transactionAmount;
    @NotNull(message = "{FinancialTransaction.TransactionId.null}")
    private Long transactionId;

}
