package com.eniomcosta.pismobacktest.services.impls;

import com.eniomcosta.pismobacktest.converters.TransactionConverter;
import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.exceptions.InvalidAmountException;
import com.eniomcosta.pismobacktest.repositories.TransactionRepository;
import com.eniomcosta.pismobacktest.services.interfaces.AccountService;
import com.eniomcosta.pismobacktest.services.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public Transaction create(TransactionDTO transactionDTO) {
        Account account = accountService.findById(transactionDTO.getAccountId());

        Transaction transaction = TransactionConverter.toEntity(transactionDTO, account);

        validate(transaction);

        return transactionRepository.save(transaction);
    }

    private void validate(Transaction transaction) {
        if (transaction.getAccount() == null || transaction.getAccount().getId() == null) {
            throw new EntityNotFoundException("A valid account must be provided");
        }

        if (transaction.getAmount() == 0) {
            throw new InvalidAmountException("Amount can't be equal to 0");
        }
    }
}
