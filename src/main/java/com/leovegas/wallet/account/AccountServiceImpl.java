package com.leovegas.wallet.account;

import com.leovegas.wallet.entity.Account;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) {
        return modelMapper.map(accountRepository.save(modelMapper.map(accountDTO, Account.class)), AccountDTO.class);
    }

    @Override
    public AccountDTO findById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null)
            return null;
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public List<AccountDTO> findAll() {
        return modelMapper.map(accountRepository.findAll(), new TypeToken<List<AccountDTO>>() {
        }.getType());
    }

}
