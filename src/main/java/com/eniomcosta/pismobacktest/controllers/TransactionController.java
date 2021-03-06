package com.eniomcosta.pismobacktest.controllers;

import com.eniomcosta.pismobacktest.converters.TransactionConverter;
import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.services.interfaces.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation(value = "Create a transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Transaction created"),
            @ApiResponse(code = 403, message = "Validation error"),
    })
    @PostMapping
    public ResponseEntity<TransactionDTO> create(@Valid @RequestBody TransactionDTO newTransactionDTO) {
        Transaction transaction = transactionService.create(newTransactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.toDto(transaction));
    }

}
