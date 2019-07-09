package com.leovegas.wallet.financialtransaction;

import com.leovegas.wallet.account.AccountNotFoundException;
import com.leovegas.wallet.entity.FinancialTransaction;
import com.leovegas.wallet.entity.Player;
import com.leovegas.wallet.financialtransaction.exception.AccountBalanceNotEnoughException;
import com.leovegas.wallet.financialtransaction.exception.FinancialTransactionIdException;
import com.leovegas.wallet.player.PlayerNotFoundException;
import com.leovegas.wallet.player.PlayerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class FinancialTransactionServiceImpl implements FinancialTransactionService {

    private final FinancialTransactionRepository financialTransactionRepository;
    private final PlayerService playerService;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    public FinancialTransactionServiceImpl(FinancialTransactionRepository financialTransactionRepository,
                                           PlayerService playerService, ModelMapper modelMapper,
                                           MessageSource messageSource) {
        this.financialTransactionRepository = financialTransactionRepository;
        this.playerService = playerService;
        this.modelMapper = modelMapper;
        this.messageSource = messageSource;
    }

    @Transactional
    @Override
    public FinancialTransactionDTO addFinancialTransaction(FinancialTransactionDTO financialTransactionDTO) {
        if (financialTransactionDTO.getTransactionId() == null)
            throw new FinancialTransactionIdException(messageSource.getMessage("FinancialTransaction.TransactionId.null", null, Locale.ENGLISH) + financialTransactionDTO.getPlayer().getId());
        Player player = playerService.findPlayerById(financialTransactionDTO.getPlayer().getId()).orElseThrow(() -> new PlayerNotFoundException(financialTransactionDTO.getPlayer().getId()));
        if (player.getAccount() == null)
            throw new AccountNotFoundException(financialTransactionDTO.getPlayer().getId());
        if (financialTransactionRepository.findByPlayerId(financialTransactionDTO.getPlayer().getId()).stream()
                .anyMatch(transaction -> transaction.getTransactionId().equals(financialTransactionDTO.getTransactionId())))
            throw new FinancialTransactionIdException(messageSource.getMessage("FinancialTransaction.TransactionId.notuniq", null, Locale.ENGLISH) + financialTransactionDTO.getTransactionId());
        Double currentBalance = player.getAccount().getCurrentBalance() + financialTransactionDTO.getTransactionAmount();
        if (currentBalance < 0)
            throw new AccountBalanceNotEnoughException(player.getAccount().getCurrentBalance());
        FinancialTransaction financialTransaction = new FinancialTransaction();
        financialTransaction.setId(financialTransactionDTO.getId());
        financialTransaction.setTransactionAmount(financialTransactionDTO.getTransactionAmount());
        financialTransaction.setTransactionId(financialTransactionDTO.getTransactionId());
        financialTransaction.setPlayer(player);
        financialTransaction.setAccount(player.getAccount());
        financialTransaction.getAccount().setCurrentBalance(currentBalance);
        return modelMapper.map(financialTransactionRepository.save(financialTransaction), FinancialTransactionDTO.class);
    }

    @Override
    public FinancialTransactionDTO findById(Long id) {
        FinancialTransaction financialTransaction = financialTransactionRepository.findById(id).orElse(null);
        if (financialTransaction == null)
            return null;
        return modelMapper.map(financialTransaction, FinancialTransactionDTO.class);
    }

    @Override
    public List<FinancialTransactionDTO> findAll() {
        return modelMapper.map(financialTransactionRepository.findAll(), new TypeToken<List<FinancialTransactionDTO>>() {
        }.getType());
    }

    @Transactional
    @Override
    public void removeById(Long id) {
        financialTransactionRepository.deleteById(id);
    }

    @Override
    public List<FinancialTransactionDTO> findByPlayerId(Long playerId) {
        return modelMapper.map(financialTransactionRepository.findByPlayerId(playerId), new TypeToken<List<FinancialTransactionDTO>>() {
        }.getType());
    }

    @Override
    public Page<FinancialTransactionDTO> findAllPaged(Pageable pageable) {
        return modelMapper.map(financialTransactionRepository.findAll(pageable), new TypeToken<Page<FinancialTransactionDTO>>() {
        }.getType());
    }

}
