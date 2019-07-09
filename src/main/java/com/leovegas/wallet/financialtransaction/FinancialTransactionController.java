package com.leovegas.wallet.financialtransaction;

import com.leovegas.wallet.financialtransaction.exception.FinancialTransactionNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class FinancialTransactionController {

    private final FinancialTransactionService financialTransactionService;
    private final FinancialTransactionResourceAssembler assembler;

    public FinancialTransactionController(FinancialTransactionService financialTransactionService,
                                          FinancialTransactionResourceAssembler assembler) {
        this.financialTransactionService = financialTransactionService;
        this.assembler = assembler;
    }

    @PostMapping("/financialtransactions")
    ResponseEntity<?> newFinancialTransaction(@Valid @RequestBody FinancialTransactionDTO financialTransactionDTO) throws URISyntaxException {
        Resource<FinancialTransactionDTO> resource = assembler.toResource(financialTransactionService.addFinancialTransaction(financialTransactionDTO));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/financialtransactions/{id}")
    Resource<FinancialTransactionDTO> oneFinancialTransaction(@PathVariable Long id) {
        FinancialTransactionDTO financialTransactionDTO = financialTransactionService.findById(id);
        if (financialTransactionDTO == null)
            throw new FinancialTransactionNotFoundException(id);
        return assembler.toResource(financialTransactionDTO);
    }

    @GetMapping("/financialtransactions")
    public Resources<Resource<FinancialTransactionDTO>> allFinancialTransaction() {

        List<Resource<FinancialTransactionDTO>> financialtransactions = financialTransactionService.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(financialtransactions,
                linkTo(methodOn(FinancialTransactionController.class).allFinancialTransaction()).withSelfRel());
    }

    @DeleteMapping("/financialtransactions/{id}")
    ResponseEntity<?> deleteFinancialTransaction(@PathVariable Long id) {

        financialTransactionService.removeById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/financialtransactions/{id}")
    ResponseEntity<?> replaceFinancialTransaction(@Valid @RequestBody FinancialTransactionDTO newFinancialTransactionDTO, @PathVariable Long id) throws URISyntaxException {

        FinancialTransactionDTO updatedFinancialTransactionDTO = financialTransactionService.findById(id);
        if (updatedFinancialTransactionDTO != null) {
            updatedFinancialTransactionDTO.setTransactionAmount(newFinancialTransactionDTO.getTransactionAmount());
            updatedFinancialTransactionDTO.setTransactionDate(newFinancialTransactionDTO.getTransactionDate());
            updatedFinancialTransactionDTO = financialTransactionService.addFinancialTransaction(updatedFinancialTransactionDTO);
        } else {
            newFinancialTransactionDTO.setId(id);
            updatedFinancialTransactionDTO = financialTransactionService.addFinancialTransaction(newFinancialTransactionDTO);
        }
        Resource<FinancialTransactionDTO> resource = assembler.toResource(updatedFinancialTransactionDTO);
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/financialtransactions/players/{id}")
    Resources<Resource<FinancialTransactionDTO>> allFinancialTransactionByPlayerId(@PathVariable Long id) {

        List<Resource<FinancialTransactionDTO>> financialtransactions = financialTransactionService.findByPlayerId(id).stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(financialtransactions,
                linkTo(methodOn(FinancialTransactionController.class).allFinancialTransaction()).withSelfRel());
    }

    @GetMapping("/pagedFinancialTransactions")
    public Page<FinancialTransactionDTO> findAllPagedFinancialTransaction(Pageable pageable) {
        return financialTransactionService.findAllPaged(pageable);
    }

}
