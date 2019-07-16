package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by shsabet on 6/12/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerControllerITTest {

    @LocalServerPort
    private int port;

    private URL base;
    private PlayerDTO playerDTO;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/wallet-service/rest");
        playerDTO = new PlayerDTO();
        playerDTO.setName("name1");
        playerDTO.setStatus(Status.ENABLE);
    }

    @Test
    public void newPlayer() throws URISyntaxException {
        HttpEntity<PlayerDTO> request = new HttpEntity<>(playerDTO);
        ResponseEntity<PlayerDTO> response = template.postForEntity(base.toURI().resolve("/wallet-service/rest/players"), request, PlayerDTO.class);
        assertThat(response.getBody().getName(), equalTo("name1"));
    }

}