package com.eniomcosta.pismobacktest.repositories;

import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.enums.OperationType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

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

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(OperationType.COMPRA_A_VISTA);
        transaction.setAmount(Double.parseDouble("-1.0"));

        Transaction persistedTransaction = transactionRepository.save(transaction);

        assertNotNull(persistedTransaction);
        assertNotNull(persistedTransaction.getId());
        assertEquals(persistedTransaction.getAccount(), transaction.getAccount());
        assertEquals(persistedTransaction.getOperationType(), transaction.getOperationType());
        assertEquals(persistedTransaction.getAmount(), transaction.getAmount());
    }
}
