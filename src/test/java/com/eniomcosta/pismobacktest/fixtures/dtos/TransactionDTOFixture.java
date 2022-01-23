package com.eniomcosta.pismobacktest.fixtures.dtos;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.enums.OperationType;

public class TransactionDTOFixture {
    public static TransactionDTO buildDefaultDebt() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(Double.parseDouble("1.00"));
        transactionDTO.setOperationTypeId(OperationType.COMPRA_A_VISTA.getCode());
        transactionDTO.setAccountId(1L);

        return transactionDTO;
    }
}
