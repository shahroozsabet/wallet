package com.leovegas.wallet.account;

import com.leovegas.wallet.entity.Status;
import lombok.Data;

@Data
public class AccountBaseDTO {

    private Long id;
    private Double currentBalance;
    private Status status;

}
