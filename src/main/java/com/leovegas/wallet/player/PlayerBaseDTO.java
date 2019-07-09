package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Status;
import lombok.Data;

@Data
public class PlayerBaseDTO {

    private Long id;
    private String name;
    private Status status;

}
