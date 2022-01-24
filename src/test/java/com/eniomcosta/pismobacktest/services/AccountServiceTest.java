package com.eniomcosta.pismobacktest.services;

import com.eniomcosta.pismobacktest.dtos.AccountDTO;
import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.fixtures.dtos.AccountDTOFixture;
import com.eniomcosta.pismobacktest.fixtures.entities.AccountFixture;
import com.eniomcosta.pismobacktest.repositories.AccountRepository;
import com.eniomcosta.pismobacktest.services.impls.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void whenDocumentNumberAlreadyExists_shouldThrowException() {
        when(accountRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        Exception exception = assertThrows(DataIntegrityViolationException.class, () ->
            accountService.create(AccountDTOFixture.buildDefault())
        );

        String expectedMessage = "Document Number already exists";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenDocumentNumberIsValid_shouldCreateAccountSuccessfully() {
        when(accountRepository.save(any(Account.class))).thenReturn(AccountFixture.buildDefault());

        AccountDTO accountDTO = AccountDTOFixture.buildDefault();

        Account account = accountService.create(accountDTO);

        ArgumentCaptor<Account> argument = ArgumentCaptor.forClass(Account.class);

        verify(accountRepository, times(1)).save(argument.capture());
        assertEquals(accountDTO.getDocumentNumber(), argument.getValue().getDocumentNumber());
        assertNull(argument.getValue().getId());
        assertNotNull(account.getId());
    }

    @Test
    void whenAccountIdIsInvalid_shouldThrownError() {
        when(accountRepository.findById(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                accountService.findById(1L)
        );

        String expectedMessage = "Account not found with id:";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenAccountIdIsValid_shouldReturnSuccessfully() {
        Account account = AccountFixture.buildDefault();

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));

        Account result = accountService.findById(account.getId());

        assertEquals(account.getId(), result.getId());
        assertEquals(account.getDocumentNumber(), result.getDocumentNumber());
        assertNotNull(account.getTransactions());
    }
}
