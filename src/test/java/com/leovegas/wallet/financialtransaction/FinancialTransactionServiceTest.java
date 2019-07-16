package com.leovegas.wallet.financialtransaction;

import com.leovegas.wallet.account.AccountBaseDTO;
import com.leovegas.wallet.account.AccountNotFoundException;
import com.leovegas.wallet.entity.Account;
import com.leovegas.wallet.entity.FinancialTransaction;
import com.leovegas.wallet.entity.Player;
import com.leovegas.wallet.entity.Status;
import com.leovegas.wallet.player.PlayerBaseDTO;
import com.leovegas.wallet.player.PlayerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class FinancialTransactionServiceTest {

    private FinancialTransactionService financialTransactionService;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private FinancialTransactionRepository financialTransactionRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private MessageSource messageSource;

    public static PlayerBaseDTO generatePlayerBaseDTO() {
        PlayerBaseDTO playerBaseDTO = new PlayerBaseDTO();
        playerBaseDTO.setId(14L);
        return playerBaseDTO;
    }

    public static AccountBaseDTO generateAccountBaseDTO() {
        AccountBaseDTO accountBaseDTO = new AccountBaseDTO();
        accountBaseDTO.setId(14L);
        return accountBaseDTO;
    }

    public static Account generateAccount() {
        Account account = new Account();
        account.setId(14L);
        account.setCurrentBalance(0D);
        return account;
    }

    private static FinancialTransaction generateFinancialTransaction() {
        FinancialTransaction financialTransaction = new FinancialTransaction();
        financialTransaction.setTransactionAmount(10L);
        financialTransaction.setTransactionId(1L);
//        financialTransaction.setAccount(accountBaseDTO);
//        financialTransaction.setPlayer(playerBaseDTO);
        return financialTransaction;
    }

    private static FinancialTransactionDTO generateFinancialTransactionDTO() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
        financialTransactionDTO.setAccount(generateAccountBaseDTO());
        financialTransactionDTO.setPlayer(generatePlayerBaseDTO());
        return financialTransactionDTO;
    }

    private static Player generatePlayer() {
        Player player = new Player();
        player.setName("name1");
        player.setStatus(Status.ENABLE);
        return player;
    }

    @Before
    public void before() {
        financialTransactionService = new FinancialTransactionServiceImpl(financialTransactionRepository, playerService, modelMapper, messageSource);
    }

    @Test(expected = AccountNotFoundException.class)
    public void addFinancialTransactionNotFoundAccount() {
        long playerId = 14L;
        FinancialTransactionDTO financialTransactionDTO = generateFinancialTransactionDTO();
        FinancialTransaction financialTransaction = generateFinancialTransaction();
        Player player = generatePlayer();
        given(modelMapper.map(financialTransactionDTO, FinancialTransaction.class)).willReturn(financialTransaction);
        given(modelMapper.map(financialTransaction, FinancialTransactionDTO.class)).willReturn(financialTransactionDTO);
        given(financialTransactionRepository.save(Mockito.any(FinancialTransaction.class))).will(invocation -> invocation.getArgument(0));
        given(playerService.findPlayerById(Mockito.eq(playerId))).willReturn(Optional.of(player));
        FinancialTransactionDTO savedFinancialTransactionDTO = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
    }

    @Test
    public void addFinancialTransaction() {
        long playerId = 14L;
        FinancialTransactionDTO financialTransactionDTO = generateFinancialTransactionDTO();
        FinancialTransaction financialTransaction = generateFinancialTransaction();
        Player player = generatePlayer();
        player.setAccount(generateAccount());
        given(modelMapper.map(financialTransactionDTO, FinancialTransaction.class)).willReturn(financialTransaction);
        given(modelMapper.map(financialTransaction, FinancialTransactionDTO.class)).willReturn(financialTransactionDTO);
        given(financialTransactionRepository.save(Mockito.any(FinancialTransaction.class))).willReturn(financialTransaction);
        given(playerService.findPlayerById(Mockito.eq(playerId))).willReturn(Optional.of(player));
        FinancialTransactionDTO savedFinancialTransactionDTO = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        Assert.assertNotNull(savedFinancialTransactionDTO);
        Assert.assertEquals(financialTransactionDTO, savedFinancialTransactionDTO);
    }

    @Ignore
    @Test
    public void findById() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
//        financialTransactionDTO.setAccount(accountBaseDTO);
//        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO financialTransactionDTO1 = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        FinancialTransactionDTO byId = financialTransactionService.findById(financialTransactionDTO1.getId());
        Assert.assertEquals(byId.getId(), financialTransactionDTO1.getId());
    }

    @Ignore
    @Test
    public void findAll() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
//        financialTransactionDTO.setAccount(accountBaseDTO);
//        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO financialTransactionDTO1 = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        List<FinancialTransactionDTO> all = financialTransactionService.findAll();
        Assert.assertTrue(all.size() > 0);
    }

    @Ignore
    @Test
    public void findByPlayerId() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
//        financialTransactionDTO.setAccount(accountBaseDTO);
//        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO financialTransactionDTO1 = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
//        for (FinancialTransactionDTO transactionDTO : financialTransactionService.findByPlayerId(playerBaseDTO.getId())) {
//            Assert.assertEquals(transactionDTO.getPlayer().getId(), financialTransactionDTO1.getPlayer().getId());
//        }

    }

    @Ignore
    @Test
    public void findAllPaged() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
//        financialTransactionDTO.setAccount(accountBaseDTO);
//        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO financialTransactionDTO1 = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        Pageable pageable = PageRequest.of(0, 1);
        List<FinancialTransactionDTO> content = financialTransactionService.findAllPaged(pageable).getContent();
        Assert.assertTrue(content.size() > 0);
    }

    @Ignore
    @Test
    public void removeById() {
        FinancialTransactionDTO financialTransactionDTO = new FinancialTransactionDTO();
        financialTransactionDTO.setTransactionAmount(10L);
        financialTransactionDTO.setTransactionId(1L);
//        financialTransactionDTO.setAccount(accountBaseDTO);
//        financialTransactionDTO.setPlayer(playerBaseDTO);
        FinancialTransactionDTO financialTransactionDTO1 = financialTransactionService.addFinancialTransaction(financialTransactionDTO);
        financialTransactionService.removeById(financialTransactionDTO1.getId());
        FinancialTransactionDTO byId = financialTransactionService.findById(financialTransactionDTO1.getId());
        Assert.assertNull(byId);
    }
}