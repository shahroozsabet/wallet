package com.leovegas.wallet.account;

import java.util.List;

public interface AccountService {

    AccountDTO addAccount(AccountDTO accountDTO);

    AccountDTO findById(Long id);

    List<AccountDTO> findAll();

}
