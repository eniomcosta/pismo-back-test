package com.eniomcosta.pismobacktest.services.interfaces;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;

public interface TransactionService {
    TransactionDTO create(TransactionDTO transactionDTO);
}
