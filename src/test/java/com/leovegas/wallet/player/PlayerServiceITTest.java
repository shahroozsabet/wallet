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
public class PlayerServiceITTest {

    @Autowired
    private PlayerService playerService;

    @Test
    public void addPlayer() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO savedPlayerDTO = playerService.addPlayer(playerDTO);
        Assert.assertEquals(Status.ENABLE, savedPlayerDTO.getStatus());
    }

    @Test
    public void findById() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO savedPlayerDTO = playerService.addPlayer(playerDTO);
        PlayerDTO foundedPlayerDTO = playerService.findById(savedPlayerDTO.getId());
        Assert.assertEquals("name1", foundedPlayerDTO.getName());
    }

    @Test
    public void findAll() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO savedPlayerDTO = playerService.addPlayer(playerDTO);
        List<PlayerDTO> foundedPlayerDTOs = playerService.findAll();
        Assert.assertTrue(foundedPlayerDTOs.size() > 0);
    }

    @Test
    public void removeById() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO savedPlayerDTO = playerService.addPlayer(playerDTO);
        playerService.removeById(savedPlayerDTO.getId());
        Assert.assertNull(playerService.findById(savedPlayerDTO.getId()));
    }

    @Test
    public void findPlayerById() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
        PlayerDTO savedPlayerDTO = playerService.addPlayer(playerDTO);
        Optional<Player> foundedPlayerById = playerService.findPlayerById(savedPlayerDTO.getId());
        Assert.assertTrue(foundedPlayerById.isPresent());
    }
}