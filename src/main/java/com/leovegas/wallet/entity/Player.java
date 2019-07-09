package com.leovegas.wallet.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @SequenceGenerator(name = "WALLET_PLAYER_SEQUENCE", sequenceName = "WALLET_PLAYER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WALLET_PLAYER_SEQUENCE")
    private Long id;

    private String name;

    @Column
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    @OneToOne(mappedBy = "player", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Account account;

}
