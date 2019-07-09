package com.leovegas.wallet.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class FinancialTransaction {

    @Id
    @SequenceGenerator(name = "WALLET_FINANCIAL_TRANSACTION_SEQUENCE", sequenceName = "WALLET_FINANCIAL_TRANSACTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WALLET_FINANCIAL_TRANSACTION_SEQUENCE")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    private Long transactionAmount;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
    private Account account;

    @Column(name = "TRANSACTION_ID", precision = 12, nullable = false)
    private Long transactionId;

    @PrePersist
    public void onPrePersist() {
        transactionDate = new Date();
    }

}
