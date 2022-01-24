package com.eniomcosta.pismobacktest.converters;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.enums.OperationType;
import com.eniomcosta.pismobacktest.exceptions.InvalidEnumOrdinalException;

import java.time.LocalDateTime;

public class TransactionConverter {
    public static TransactionDTO toDto(Transaction transaction) {
        return TransactionDTO.builder()
                .operationTypeId(transaction.getOperationTypeId())
                .amount(transaction.getAmount())
                .accountId(transaction.getAccount().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDTO transactionDTO, Account account) {
        OperationType operationType = OperationType.getOperationById(transactionDTO.getOperationTypeId())
                .orElseThrow(() -> new InvalidEnumOrdinalException("Operation Type Id provided is invalid"));

        Double amount = operationType.isDebtOperation() ?
                (transactionDTO.getAmount() * -1) : transactionDTO.getAmount();

        return Transaction.builder()
                .account(account)
                .operationTypeId(operationType.getCode())
                .amount(amount)
                .eventDate(LocalDateTime.now())
                .build();
    }
}
