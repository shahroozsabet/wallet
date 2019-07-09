package com.leovegas.wallet.account;

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
public class AccountController {

    private final AccountService accountService;
    private final AccountResourceAssembler assembler;

    public AccountController(AccountService accountService, AccountResourceAssembler assembler) {
        this.accountService = accountService;
        this.assembler = assembler;
    }

    @PostMapping("/accounts")
    ResponseEntity<?> newAccount(@RequestBody AccountDTO newAccountDTO) throws URISyntaxException {
        Resource<AccountDTO> resource = assembler.toResource(accountService.addAccount(newAccountDTO));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/accounts/{id}")
    Resource<AccountDTO> oneAccount(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.findById(id);
        if (accountDTO == null)
            throw new AccountNotFoundException(id);
        return assembler.toResource(accountDTO);
    }

    @GetMapping("/accounts")
    public Resources<Resource<AccountDTO>> allAccount() {
        List<Resource<AccountDTO>> accounts = accountService.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(accounts,
                linkTo(methodOn(AccountController.class).allAccount()).withSelfRel());
    }

    @PutMapping("/accounts/{id}")
    ResponseEntity<?> replaceAccount(@RequestBody AccountDTO newAccountDTO, @PathVariable Long id) throws URISyntaxException {

        AccountDTO updatedAccount = accountService.findById(id);
        if (updatedAccount != null) {
            updatedAccount.setCurrentBalance(newAccountDTO.getCurrentBalance());
            updatedAccount.setStatus(newAccountDTO.getStatus());
            updatedAccount = accountService.addAccount(updatedAccount);
        } else {
            newAccountDTO.setId(id);
            updatedAccount = accountService.addAccount(newAccountDTO);
        }
        Resource<AccountDTO> resource = assembler.toResource(updatedAccount);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

}
