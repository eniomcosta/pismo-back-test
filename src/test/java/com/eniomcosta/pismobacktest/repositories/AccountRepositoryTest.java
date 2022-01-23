package com.eniomcosta.pismobacktest.repositories;

import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.fixtures.entities.AccountFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    public void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    public void whenDataIsCorrect_shouldCreateAccountSuccessfully() {
        Account account = AccountFixture.buildDefault();
        account.setId(null);

        Account persistedAccount = accountRepository.save(account);

        assertNotNull(account);
        assertEquals(account.getId(), persistedAccount.getId());
        assertEquals(account.getDocumentNumber(), persistedAccount.getDocumentNumber());
    }

    @Sql(scripts = "/database/insert-account.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void whenProvideAValidAccountId_shouldFindAccountSuccessfully() {
        Account retrievedAccount = accountRepository.findById(1L).get();

        assertNotNull(retrievedAccount);
        assertEquals(1, retrievedAccount.getId());
        assertEquals("12345678900", retrievedAccount.getDocumentNumber());
    }

    @Test
    public void whenAccountIdIsInvalid_shouldNotFindAccount() {
        Optional<Account> optionalAccount = accountRepository.findById(1L);

        assertFalse(optionalAccount.isPresent());
    }
}
