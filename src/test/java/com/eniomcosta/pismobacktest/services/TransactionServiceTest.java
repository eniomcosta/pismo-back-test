package com.eniomcosta.pismobacktest.services;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.exceptions.InvalidAmountException;
import com.eniomcosta.pismobacktest.exceptions.InvalidEnumOrdinalException;
import com.eniomcosta.pismobacktest.fixtures.dtos.TransactionDTOFixture;
import com.eniomcosta.pismobacktest.fixtures.entities.AccountFixture;
import com.eniomcosta.pismobacktest.fixtures.entities.TransactionFixture;
import com.eniomcosta.pismobacktest.repositories.TransactionRepository;
import com.eniomcosta.pismobacktest.services.impls.TransactionServiceImpl;
import com.eniomcosta.pismobacktest.services.interfaces.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Test
    void whenAccountDoesNotExists_shouldThrowException() {
        when(accountService.findById(any())).thenThrow(new EntityNotFoundException("Account not found with id: 1"));

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
            transactionService.create(TransactionDTOFixture.buildDefaultDebt())
        );

        String expectedMessage = "Account not found with id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenOperationTypeProvidedIsInvalid_shouldThrowException() {
        when(accountService.findById(any())).thenReturn(AccountFixture.buildDefault());

        TransactionDTO transactionDTO =  TransactionDTOFixture.buildDefaultDebt();
        transactionDTO.setOperationTypeId(0);

        Exception exception = assertThrows(InvalidEnumOrdinalException.class, () ->
                transactionService.create(transactionDTO)
        );

        String expectedMessage = "Operation Type Id provided is invalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenAmountProvidedIsEqualsToZero_shouldThrowException() {
        when(accountService.findById(any())).thenReturn(AccountFixture.buildDefault());

        TransactionDTO transactionDTO =  TransactionDTOFixture.buildDefaultDebt();
        transactionDTO.setAmount(Double.parseDouble("0.0"));

        Exception exception = assertThrows(InvalidAmountException.class, () ->
                transactionService.create(transactionDTO)
        );

        String expectedMessage = "Amount can't be equal to 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void whenDataIsProvidedCorrectly_shouldCreateDebtTransactionSuccessfully() {
        when(accountService.findById(any())).thenReturn(AccountFixture.buildDefault());
        when(transactionRepository.save(any())).thenReturn(TransactionFixture.buildDefaultDebt());

        TransactionDTO transactionDTO =  TransactionDTOFixture.buildDefaultDebt();

        Transaction transaction = transactionService.create(transactionDTO);

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);

        verify(transactionRepository, times(1)).save(argument.capture());
        assertEquals(transactionDTO.getOperationTypeId(), argument.getValue().getOperationType().getCode());
        assertEquals(transactionDTO.getAmount(), argument.getValue().getAmount() * -1);
        assertEquals(transactionDTO.getAccountId(), argument.getValue().getAccount().getId());

        assertEquals(transactionDTO.getAccountId(), transaction.getAccount().getId());
        assertEquals(transactionDTO.getAmount(), transaction.getAmount() * -1);
        assertEquals(transactionDTO.getOperationTypeId(), transaction.getOperationType().getCode());
    }

}
