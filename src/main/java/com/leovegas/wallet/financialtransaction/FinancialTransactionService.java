package com.leovegas.wallet.financialtransaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinancialTransactionService {

    FinancialTransactionDTO addFinancialTransaction(FinancialTransactionDTO financialTransactionDTO);

    FinancialTransactionDTO findById(Long id);

    List<FinancialTransactionDTO> findAll();

    void removeById(Long id);

    List<FinancialTransactionDTO> findByPlayerId(Long playId);

    Page<FinancialTransactionDTO> findAllPaged(Pageable pageable);
}
