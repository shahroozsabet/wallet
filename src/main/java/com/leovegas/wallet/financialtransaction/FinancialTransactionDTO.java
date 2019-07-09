package com.leovegas.wallet.financialtransaction;

import com.leovegas.wallet.account.AccountBaseDTO;
import com.leovegas.wallet.player.PlayerBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class FinancialTransactionDTO extends FinancialTransactionBaseDTO {

    @NotNull
    private PlayerBaseDTO player;
    @NotNull
    private AccountBaseDTO account;

}
