package com.leovegas.wallet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by shsabet on 6/12/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RootControllerTest {

    @Autowired
    private RootController rootController;

    @Test
    public void contextLoads() {
        assertThat(rootController).isNotNull();
    }

}