package com.leovegas.wallet.financialtransaction;

import com.leovegas.wallet.entity.FinancialTransaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface FinancialTransactionRepository extends PagingAndSortingRepository<FinancialTransaction, Long> {

    List<FinancialTransaction> findByPlayerId(Long playerId);

}
