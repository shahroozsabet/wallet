package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Player;
import com.leovegas.wallet.entity.Status;
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

/**
 * Created by shsabet on 7/3/2019.
 */
@RunWith(SpringRunner.class)
public class PlayerServiceTest {

    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private ModelMapper modelMapper;

    private static PlayerDTO generatePlayerDTO() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        return playerDTO;
    }

    private static Player generatePlayer() {
        Player player = new Player();
        player.setName("name1");
        player.setStatus(Status.ENABLE);
        return player;
    }

    @Before
    public void before() {
        playerService = new PlayerServiceImpl(playerRepository, modelMapper);
    }

    @Test
    public void addPlayer() {
        PlayerDTO playerDTO = generatePlayerDTO();
        Player player = generatePlayer();
        given(modelMapper.map(playerDTO, Player.class)).willReturn(player);
        given(modelMapper.map(player, PlayerDTO.class)).willReturn(playerDTO);
        given(playerRepository.save(Mockito.any(Player.class))).will(invocation -> invocation.getArgument(0));
        PlayerDTO savedPlayer = playerService.addPlayer(playerDTO);
        Assert.assertEquals(playerDTO, savedPlayer);
        Assert.assertEquals(savedPlayer.getStatus(), Status.ENABLE);
    }

    @Test
    public void findById() {
        long playerId = 14L;
        PlayerDTO playerDTO = generatePlayerDTO();
        Player player = generatePlayer();
        given(modelMapper.map(playerDTO, Player.class)).willReturn(player);
        given(modelMapper.map(player, PlayerDTO.class)).willReturn(playerDTO);
        given(playerRepository.findById(Mockito.eq(playerId))).willReturn(Optional.of(player));
        PlayerDTO foundedPlayerDTO = playerService.findById(playerId);
        Assert.assertEquals(playerDTO, foundedPlayerDTO);
        Assert.assertEquals("name1", foundedPlayerDTO.getName());
    }

    @Test
    public void findAll() {
        PlayerDTO playerDTO = generatePlayerDTO();
        Player player = generatePlayer();
        given(modelMapper.map(playerDTO, Player.class)).willReturn(player);
        given(modelMapper.map(Arrays.asList(player), new TypeToken<List<PlayerDTO>>() {
        }.getType())).willReturn(Arrays.asList(playerDTO));
        given(playerRepository.findAll()).willReturn(Arrays.asList(player));
        List<PlayerDTO> foundedPlayers = playerService.findAll();
        Assert.assertTrue(foundedPlayers.size() > 0);
    }

    @Test
    public void findPlayerById() {
        long playerId = 14L;
        PlayerDTO playerDTO = generatePlayerDTO();
        Player player = generatePlayer();
        given(modelMapper.map(playerDTO, Player.class)).willReturn(player);
        given(modelMapper.map(player, PlayerDTO.class)).willReturn(playerDTO);
        given(playerRepository.findById(Mockito.eq(playerId))).willReturn(Optional.of(player));
        Optional<Player> foundedPlayerDTO = playerService.findPlayerById(playerId);
        Assert.assertEquals(player, foundedPlayerDTO.get());
    }
}