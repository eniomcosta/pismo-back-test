package com.eniomcosta.pismobacktest.fixtures.entities;

import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.enums.OperationType;

import java.time.Instant;
import java.time.LocalDateTime;

public class TransactionFixture {
    public static Transaction buildDefaultDebt() {
        return Transaction.builder()
                .account(AccountFixture.buildDefault())
                .id(1L)
                .amount(Double.parseDouble("-1.00"))
                .operationTypeId(OperationType.COMPRA_A_VISTA.getCode())
                .eventDate(LocalDateTime.now())
                .build();

    }

    public static Transaction buildDefaultCredit() {
        return Transaction.builder()
                .account(AccountFixture.buildDefault())
                .id(1L)
                .amount(Double.parseDouble("1.00"))
                .operationTypeId(OperationType.PAGAMENTO.getCode())
                .eventDate(LocalDateTime.now())
                .build();

    }
}
