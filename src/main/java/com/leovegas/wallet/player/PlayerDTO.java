package com.leovegas.wallet.player;

import com.leovegas.wallet.account.AccountBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerDTO extends PlayerBaseDTO {

    private AccountBaseDTO account;

}
