package com.eniomcosta.pismobacktest.fixtures.entities;

import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.enums.OperationType;

import java.time.Instant;

public class TransactionFixture {
    public static Transaction buildDefaultDebt() {
        Transaction transaction = new Transaction();

        transaction.setAccount(AccountFixture.buildDefault());
        transaction.setId(1L);
        transaction.setAmount(Double.parseDouble("-1.00"));
        transaction.setOperationType(OperationType.COMPRA_A_VISTA);
        transaction.setEventDate(Instant.now());

        return transaction;
    }
}
