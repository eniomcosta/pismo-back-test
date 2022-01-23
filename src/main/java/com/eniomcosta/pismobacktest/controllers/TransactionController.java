package com.eniomcosta.pismobacktest.controllers;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.services.interfaces.TransactionService;
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

    @PostMapping
    public ResponseEntity<TransactionDTO> create(@Valid @RequestBody TransactionDTO newTransactionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.create(newTransactionDTO));
    }

}
