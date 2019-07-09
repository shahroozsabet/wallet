package com.leovegas.wallet.player;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class PlayerResourceAssembler implements ResourceAssembler<PlayerDTO, Resource<PlayerDTO>> {

    @Override
    public Resource<PlayerDTO> toResource(PlayerDTO playerDTO) {
        return new Resource<>(playerDTO,
                linkTo(methodOn(PlayerController.class).onePlayer(playerDTO.getId())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).allPlayer()).withRel("players"));
    }
}
