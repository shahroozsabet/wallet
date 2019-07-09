package com.leovegas.wallet.account;

import com.leovegas.wallet.player.PlayerBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountDTO extends AccountBaseDTO {

    private PlayerBaseDTO player;

}
