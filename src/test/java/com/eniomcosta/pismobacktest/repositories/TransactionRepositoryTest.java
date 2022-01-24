package com.eniomcosta.pismobacktest.repositories;

import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.enums.OperationType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    public void tearDown() {
        transactionRepository.deleteAll();
    }

    @Sql(scripts = "/database/insert-account.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void shouldCreateTransactionSuccessfully() {
        Account account = accountRepository.findById(1L).get();

        Transaction transaction = Transaction.builder()
                .account(account)
                .operationTypeId(OperationType.COMPRA_A_VISTA.getCode())
                .amount(Double.parseDouble("-1.0"))
                .eventDate(LocalDateTime.now())
                .build();

        Transaction persistedTransaction = transactionRepository.save(transaction);

        assertNotNull(persistedTransaction);
        assertNotNull(persistedTransaction.getId());
        assertEquals(persistedTransaction.getAccount(), transaction.getAccount());
        assertEquals(persistedTransaction.getOperationTypeId(), transaction.getOperationTypeId());
        assertEquals(persistedTransaction.getAmount(), transaction.getAmount());
        assertNotNull(persistedTransaction.getEventDate());
    }
}
