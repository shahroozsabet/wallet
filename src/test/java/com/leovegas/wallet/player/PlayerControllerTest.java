package com.leovegas.wallet.player;

import com.leovegas.wallet.entity.Status;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({PlayerController.class, PlayerResourceAssembler.class})
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PlayerControllerTest {

    private static List<PlayerDTO> playerDTOS = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private PlayerResourceAssembler playerResourceAssembler;
    @MockBean
    private PlayerService playerService;

    @BeforeClass
    public static void init() {
        PlayerDTO playerDTO1 = new PlayerDTO();
        playerDTO1.setStatus(Status.ENABLE);
        playerDTO1.setName("name1");
        PlayerDTO playerDTO2 = new PlayerDTO();
        playerDTO2.setStatus(Status.ENABLE);
        playerDTO2.setName("name2");
        playerDTOS.add(playerDTO1);
        playerDTOS.add(playerDTO2);
    }

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testPlayerResourceAssembler() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setStatus(Status.ENABLE);
        ResourceSupport resource = playerResourceAssembler.toResource(playerDTO);
        Assert.assertEquals(2, resource.getLinks().size());
        Assert.assertEquals("players", resource.getLinks().get(1).getRel());
        Assert.assertTrue(resource.getLinks().get(1).getHref().contains("players"));
    }

    @Test
    public void allPlayer() throws Exception {
        given(this.playerService.findAll()).willReturn(playerDTOS);
        this.mockMvc.perform(get("/players").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$._embedded.playerDTOList", Matchers.hasSize(2)))
                .andExpect(jsonPath("$._embedded.playerDTOList[0].name", is("name1")))
                .andExpect(jsonPath("$._embedded.playerDTOList[0].status", is(1)))
                .andExpect(jsonPath("$._embedded.playerDTOList[1].name", is("name2")))
                .andExpect(jsonPath("$._embedded.playerDTOList[1].status", is(1)))
                .andDo(document("players"));
    }

}