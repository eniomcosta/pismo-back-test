package com.eniomcosta.pismobacktest.fixtures.dtos;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.enums.OperationType;

public class TransactionDTOFixture {
    public static TransactionDTO buildDefaultDebt() {
        return TransactionDTO.builder()
                .amount(Double.parseDouble("1.00"))
                .operationTypeId(OperationType.COMPRA_A_VISTA.getCode())
                .accountId(1L)
                .build();
    }

    public static TransactionDTO buildDefaultCredit() {
        return TransactionDTO.builder()
                .amount(Double.parseDouble("1.00"))
                .operationTypeId(OperationType.PAGAMENTO.getCode())
                .accountId(1L)
                .build();
    }
}
