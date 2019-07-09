package com.leovegas.wallet;

import com.leovegas.wallet.account.AccountController;
import com.leovegas.wallet.financialtransaction.FinancialTransactionController;
import com.leovegas.wallet.player.PlayerController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
class RootController {

    @GetMapping("/index")
    ResourceSupport index() {
        ResourceSupport rootResource = new ResourceSupport();
        rootResource.add(ControllerLinkBuilder.linkTo(methodOn(PlayerController.class).allPlayer()).withRel("players"));
        rootResource.add(linkTo(methodOn(AccountController.class).allAccount()).withRel("accounts"));
        rootResource.add(linkTo(methodOn(FinancialTransactionController.class).allFinancialTransaction()).withRel("financialtransactions"));
        return rootResource;
    }

}
