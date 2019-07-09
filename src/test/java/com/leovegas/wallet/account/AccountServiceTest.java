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
public class AccountServiceTest {

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
        AccountDTO accountDTO1 = accountService.addAccount(accountDTO);
        Assert.assertNotNull(accountDTO1);
    }

    @Test
    public void findById() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setPlayer(playerBaseDTO);
        accountDTO.setCurrentBalance(0D);
        accountDTO.setStatus(Status.ENABLE);
        AccountDTO accountDTO1 = accountService.addAccount(accountDTO);
        AccountDTO byId = accountService.findById(accountDTO1.getId());
        Assert.assertEquals(byId.getId(), accountDTO1.getId());
    }

    @Test
    public void findAll() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setPlayer(playerBaseDTO);
        accountDTO.setCurrentBalance(0D);
        accountDTO.setStatus(Status.ENABLE);
        AccountDTO accountDTO1 = accountService.addAccount(accountDTO);
        List<AccountDTO> all = accountService.findAll();
        Assert.assertTrue(all.size() > 0);
    }

}