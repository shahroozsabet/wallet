package com.leovegas.wallet.financialtransaction;

import com.leovegas.wallet.account.AccountBaseDTO;
import com.leovegas.wallet.account.AccountDTO;
import com.leovegas.wallet.account.AccountService;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialTransactionServiceITTest {

    @Autowired
    private FinancialTransactionService financialTransactionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PlayerService playerService;

    private PlayerBaseDTO playerBaseDTO;
    private AccountBaseDTO accountBaseDTO;

    @Before
    public void before() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        playerDTO = playerService.addPlayer(playerDTO);
        playerBaseDTO = new PlayerBaseDTO();
        playerBaseDTO.setId(playerDTO.getId());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setPlayer(playerBaseDTO);
        accountDTO.setCurrentBalance(0D);
        accountDTO.setStatus(Status.ENABLE);
        accountDTO = accountService.addAccount(accountDTO);
        accountBaseDTO = new AccountBaseDTO();
        accountBaseDTO.setId(accountDTO.getId());
    }

    @Test
    public void addFinancialTransaction() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
        financialTransactionDTO.setAccount(accountBaseDTO);
        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO savedFinancialTransactionDTO = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        Assert.assertNotNull(savedFinancialTransactionDTO);
        Assert.assertEquals(10, savedFinancialTransactionDTO.getAccount().getCurrentBalance().longValue());
    }

    @Test
    public void findById() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
        financialTransactionDTO.setAccount(accountBaseDTO);
        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO savedFinancialTransactionDTO = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        FinancialTransactionDTO foundedFinancialTransactionDTO = financialTransactionService.findById(savedFinancialTransactionDTO.getId());
        Assert.assertEquals(savedFinancialTransactionDTO.getId(), foundedFinancialTransactionDTO.getId());
    }

    @Test
    public void findAll() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
        financialTransactionDTO.setAccount(accountBaseDTO);
        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO savedfinancialTransactionDTO = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        List<FinancialTransactionDTO> foundedFinancialTransactionDTOs = financialTransactionService.findAll();
        Assert.assertTrue(foundedFinancialTransactionDTOs.size() > 0);
    }

    @Test
    public void findByPlayerId() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
        financialTransactionDTO.setAccount(accountBaseDTO);
        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO savedfinancialTransactionDTO = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        for (FinancialTransactionDTO transactionDTO : financialTransactionService.findByPlayerId(playerBaseDTO.getId())) {
            Assert.assertEquals(savedfinancialTransactionDTO.getPlayer().getId(), transactionDTO.getPlayer().getId());
        }
    }

    @Test
    public void findAllPaged() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
        financialTransactionDTO.setAccount(accountBaseDTO);
        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO savedfinancialTransactionDTO = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        Pageable pageable = PageRequest.of(0, 1);
        List<FinancialTransactionDTO> financialTransactionDTOS = financialTransactionService.findAllPaged(pageable).getContent();
        Assert.assertTrue(financialTransactionDTOS.size() > 0);
    }

    @Test
    public void removeById() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
        financialTransactionDTO.setAccount(accountBaseDTO);
        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO savedfinancialTransactionDTO = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        financialTransactionService.removeById(savedfinancialTransactionDTO.getId());
        FinancialTransactionDTO foundedFinancialTransactionDTO = financialTransactionService.findById(savedfinancialTransactionDTO.getId());
        Assert.assertNull(foundedFinancialTransactionDTO);
    }
}