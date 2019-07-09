package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Player;
import com.leovegas.wallet.entity.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * Created by shsabet on 7/3/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    public void addPlayer() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        Assert.assertEquals(playerDTO1.getStatus(), Status.ENABLE);
    }

    @Test
    public void findById() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        PlayerDTO byId = playerService.findById(playerDTO1.getId());
        Assert.assertEquals(byId.getName(), "name1");
    }

    @Test
    public void findAll() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        List<PlayerDTO> all = playerService.findAll();
        Assert.assertTrue(all.size() > 0);
    }

    @Test
    public void removeById() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        playerService.removeById(playerDTO1.getId());
        Assert.assertNull(playerService.findById(playerDTO1.getId()));
    }

    @Test
    public void findPlayerById() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO playerDTO1 = playerService.addPlayer(playerDTO);
        Optional<Player> playerById = playerService.findPlayerById(playerDTO1.getId());
        Assert.assertTrue(playerById.isPresent());
    }
}