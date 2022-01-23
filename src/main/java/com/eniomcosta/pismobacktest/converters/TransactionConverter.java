package com.eniomcosta.pismobacktest.converters;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.enums.OperationType;
import com.eniomcosta.pismobacktest.exceptions.InvalidEnumOrdinalException;

public class TransactionConverter {
    public static TransactionDTO toDto(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setOperationTypeId(transaction.getOperationType().getCode());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setAccountId(transaction.getAccount().getId());
        return transactionDTO;
    }

    public static Transaction toEntity(TransactionDTO transactionDTO, Account account) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);

        transaction.setOperationType(OperationType.getOperationById(transactionDTO.getOperationTypeId())
                .orElseThrow(() -> new InvalidEnumOrdinalException("Operation Type Id provided is invalid")));

        Double amount = transaction.getOperationType().isDebtOperation() ?
                (transactionDTO.getAmount() * -1) : transactionDTO.getAmount();

        transaction.setAmount(amount);

        return transaction;
    }
}
