package com.leovegas.wallet.financialtransaction;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class FinancialTransactionResourceAssembler implements ResourceAssembler<FinancialTransactionDTO, Resource<FinancialTransactionDTO>> {

    @Override
    public Resource<FinancialTransactionDTO> toResource(FinancialTransactionDTO FinancialTransactionDTO) {
        return new Resource<>(FinancialTransactionDTO,
                linkTo(methodOn(FinancialTransactionController.class).oneFinancialTransaction(FinancialTransactionDTO.getId())).withSelfRel(),
                linkTo(methodOn(FinancialTransactionController.class).allFinancialTransaction()).withRel("accounts"));
    }
}
