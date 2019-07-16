package com.leovegas.wallet.account;

import com.leovegas.wallet.entity.Status;
import com.leovegas.wallet.player.PlayerBaseDTO;
import com.leovegas.wallet.player.PlayerDTO;
import com.leovegas.wallet.player.PlayerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceITTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PlayerService playerService;

    private PlayerBaseDTO playerBaseDTO;

    @Before
    public void before() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        playerDTO = playerService.addPlayer(playerDTO);
        playerBaseDTO = new PlayerBaseDTO();
        playerBaseDTO.setId(playerDTO.getId());
    }

    @Test
    public void addAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setPlayer(playerBaseDTO);
        accountDTO.setCurrentBalance(0D);
        accountDTO.setStatus(Status.ENABLE);
        AccountDTO savedDTO = accountService.addAccount(accountDTO);
        Assert.assertNotNull(savedDTO);
    }

    @Test
    public void findById() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setPlayer(playerBaseDTO);
        accountDTO.setCurrentBalance(0D);
        accountDTO.setStatus(Status.ENABLE);
        AccountDTO savedAccountDTO = accountService.addAccount(accountDTO);
        AccountDTO foundedAccountDTO = accountService.findById(savedAccountDTO.getId());
        Assert.assertEquals(savedAccountDTO.getId(), foundedAccountDTO.getId());
    }

    @Test
    public void findAll() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setPlayer(playerBaseDTO);
        accountDTO.setCurrentBalance(0D);
        accountDTO.setStatus(Status.ENABLE);
        AccountDTO savedAccountDTO = accountService.addAccount(accountDTO);
        List<AccountDTO> foundedAccountDTOs = accountService.findAll();
        Assert.assertTrue(foundedAccountDTOs.size() > 0);
    }

}