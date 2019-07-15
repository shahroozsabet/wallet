package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Player;
import com.leovegas.wallet.entity.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Ignore
    @Test
    public void findById() {
        PlayerDTO playerDTO = generatePlayerDTO();
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        PlayerDTO byId = playerService.findById(playerDTO1.getId());
        Assert.assertEquals(byId.getName(), "name1");
    }

    @Ignore
    @Test
    public void findAll() {
        PlayerDTO playerDTO = generatePlayerDTO();
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        List<PlayerDTO> all = playerService.findAll();
        Assert.assertTrue(all.size() > 0);
    }

    @Ignore
    @Test
    public void removeById() {
        PlayerDTO playerDTO = generatePlayerDTO();
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        playerService.removeById(playerDTO1.getId());
        Assert.assertNull(playerService.findById(playerDTO1.getId()));
    }

    @Ignore
    @Test
    public void findPlayerById() {
        PlayerDTO playerDTO = generatePlayerDTO();
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        Optional<Player> playerById = playerService.findPlayerById(playerDTO1.getId());
        Assert.assertTrue(playerById.isPresent());
    }
}