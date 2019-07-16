package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Player;
import com.leovegas.wallet.entity.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testSavePlayer() {
        Player player = new Player();
        player.setName("name1");
        player.setStatus(Status.ENABLE);
        Player savedPlayer = playerRepository.save(player);
        Assert.assertNotNull(savedPlayer);
        Assert.assertNotNull(savedPlayer.getId());
    }
}