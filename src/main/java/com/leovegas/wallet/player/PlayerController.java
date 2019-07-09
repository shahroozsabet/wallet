package com.leovegas.wallet.player;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerResourceAssembler assembler;

    public PlayerController(PlayerService playerService, PlayerResourceAssembler assembler) {
        this.playerService = playerService;
        this.assembler = assembler;
    }

    @PostMapping("/players")
    ResponseEntity<?> newPlayer(@RequestBody PlayerDTO newPlayerDTO) throws URISyntaxException {
        Resource<PlayerDTO> resource = assembler.toResource(playerService.addPlayer(newPlayerDTO));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/players/{id}")
    Resource<PlayerDTO> onePlayer(@PathVariable Long id) {
        PlayerDTO playerDTO = playerService.findById(id);
        if (playerDTO == null)
            throw new PlayerNotFoundException(id);
        return assembler.toResource(playerDTO);
    }

    @GetMapping("/players")
    public Resources<Resource<PlayerDTO>> allPlayer() {

        List<Resource<PlayerDTO>> players = playerService.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(players,
                linkTo(methodOn(PlayerController.class).allPlayer()).withSelfRel());
    }

    @DeleteMapping("/players/{id}")
    ResponseEntity<?> deletePlayer(@PathVariable Long id) {

        playerService.removeById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/players/{id}")
    ResponseEntity<?> replacePlayer(@RequestBody PlayerDTO newPlayerDTO, @PathVariable Long id) throws URISyntaxException {

        PlayerDTO updatedPlayer = playerService.findById(id);
        if (updatedPlayer != null) {
            updatedPlayer.setName(newPlayerDTO.getName());
            updatedPlayer.setStatus(newPlayerDTO.getStatus());
            updatedPlayer = playerService.addPlayer(updatedPlayer);
        } else {
            newPlayerDTO.setId(id);
            updatedPlayer = playerService.addPlayer(newPlayerDTO);
        }
        Resource<PlayerDTO> resource = assembler.toResource(updatedPlayer);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

}
