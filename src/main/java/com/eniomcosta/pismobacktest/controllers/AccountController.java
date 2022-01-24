package com.eniomcosta.pismobacktest.controllers;

import com.eniomcosta.pismobacktest.converters.AccountConverter;
import com.eniomcosta.pismobacktest.dtos.AccountDTO;
import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.services.interfaces.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Create account")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Return created account"),
            @ApiResponse(code = 403, message = "Specified document number already exists"),
    })
    @PostMapping
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO newAccountDTO) {
        Account account = accountService.create(newAccountDTO);
        AccountDTO accountDTO = AccountConverter.toDto(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDTO);
    }

    @ApiOperation(value = "Find account by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account was found"),
            @ApiResponse(code = 403, message = "No account was found"),
    })
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long accountId) {
        AccountDTO accountDTO = AccountConverter.toDto(accountService.findById(accountId));
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }
}

