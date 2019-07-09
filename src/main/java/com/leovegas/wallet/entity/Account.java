package com.leovegas.wallet.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @SequenceGenerator(name = "WALLET_ACCOUNT_SEQUENCE", sequenceName = "WALLET_ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WALLET_ACCOUNT_SEQUENCE")
    private Long id;

    @Column(precision = 18, scale = 3)
    private Double currentBalance;

    @Column
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    @OneToOne
    @JoinColumn(name = "PLAYER_ID", unique = true)
    private Player player;

}
