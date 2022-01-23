package com.eniomcosta.pismobacktest.controllers;

import com.eniomcosta.pismobacktest.converters.AccountConverter;
import com.eniomcosta.pismobacktest.dtos.AccountDTO;
import com.eniomcosta.pismobacktest.services.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO newAccountDTO) {
        AccountDTO accountDTO = AccountConverter.toDto(accountService.create(newAccountDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDTO);
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long accountId) {
        AccountDTO accountDTO = AccountConverter.toDto(accountService.findById(accountId));
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }
}

