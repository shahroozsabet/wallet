package com.leovegas.wallet.account;

import com.leovegas.wallet.entity.Account;
import com.leovegas.wallet.entity.Player;
import com.leovegas.wallet.entity.Status;
import com.leovegas.wallet.player.PlayerBaseDTO;
import com.leovegas.wallet.player.PlayerDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private ModelMapper modelMapper;

    private AccountService accountService;

    public static PlayerDTO generatePlayerDTO() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        return playerDTO;
    }

    public static PlayerBaseDTO generatePlayerBaseDTO() {
        PlayerBaseDTO playerBaseDTO = new PlayerBaseDTO();
        playerBaseDTO.setId(14L);
        return playerBaseDTO;
    }

    public static AccountDTO generateAccountDTO() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setPlayer(generatePlayerBaseDTO());
        accountDTO.setCurrentBalance(0D);
        accountDTO.setStatus(Status.ENABLE);
        return accountDTO;
    }

    private static Player generatePlayer() {
        Player player = new Player();
        player.setId(14L);
        return player;
    }

    public static Account generateAccount() {
        Account account = new Account();
        account.setPlayer(generatePlayer());
        account.setCurrentBalance(0D);
        account.setStatus(Status.ENABLE);
        return account;
    }

    @Before
    public void before() {
        accountService = new AccountServiceImpl(accountRepository, modelMapper);
    }

    @Test
    public void addAccount() {
        AccountDTO accountDTO = generateAccountDTO();
        Account account = generateAccount();
        given(modelMapper.map(accountDTO, Account.class)).willReturn(generateAccount());
        given(modelMapper.map(account, AccountDTO.class)).willReturn(generateAccountDTO());
        given(accountRepository.save(Mockito.any(Account.class))).will(invocation -> invocation.getArgument(0));
        AccountDTO savedAccountDTO = accountService.addAccount(accountDTO);
        Assert.assertNotNull(savedAccountDTO);
        Assert.assertEquals(accountDTO, savedAccountDTO);
        Assert.assertEquals(savedAccountDTO.getStatus(), Status.ENABLE);
    }

    @Test
    public void findById() {
        Long accountId = 14L;
        AccountDTO accountDTO = generateAccountDTO();
        Account account = generateAccount();
        given(modelMapper.map(accountDTO, Account.class)).willReturn(generateAccount());
        given(modelMapper.map(account, AccountDTO.class)).willReturn(generateAccountDTO());
        given(accountRepository.findById(Mockito.eq(accountId))).willReturn(Optional.of(account));
        AccountDTO foundedAccountDTO = accountService.findById(accountId);
        Assert.assertEquals(accountDTO, foundedAccountDTO);
        Assert.assertEquals(accountDTO.getStatus(), foundedAccountDTO.getStatus());
    }

    @Test
    public void findAll() {
        AccountDTO accountDTO = generateAccountDTO();
        Account account = generateAccount();
        given(modelMapper.map(accountDTO, Account.class)).willReturn(account);
        given(modelMapper.map(Arrays.asList(account), new TypeToken<List<AccountDTO>>() {
        }.getType())).willReturn(Arrays.asList(accountDTO));
        given(accountRepository.findAll()).willReturn(Arrays.asList(account));
        List<AccountDTO> foundedAccounts = accountService.findAll();
        Assert.assertTrue(foundedAccounts.size() > 0);
    }

}