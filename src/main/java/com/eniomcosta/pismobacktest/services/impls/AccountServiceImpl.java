package com.eniomcosta.pismobacktest.services.impls;

import com.eniomcosta.pismobacktest.converters.AccountConverter;
import com.eniomcosta.pismobacktest.dtos.AccountDTO;
import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.repositories.AccountRepository;
import com.eniomcosta.pismobacktest.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(AccountDTO accountDto) {
        try {
            return accountRepository.save(AccountConverter.toEntity(accountDto));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Document Number already exists");
        }
    }

    @Override
    public Account findById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account not found with id: %d", accountId)));
    }
}