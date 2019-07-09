package com.leovegas.wallet.account;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class AccountResourceAssembler implements ResourceAssembler<AccountDTO, Resource<AccountDTO>> {

    @Override
    public Resource<AccountDTO> toResource(AccountDTO accountDTO) {
        return new Resource<>(accountDTO,
                linkTo(methodOn(AccountController.class).oneAccount(accountDTO.getId())).withSelfRel(),
                linkTo(methodOn(AccountController.class).allAccount()).withRel("accounts"));
    }
}
