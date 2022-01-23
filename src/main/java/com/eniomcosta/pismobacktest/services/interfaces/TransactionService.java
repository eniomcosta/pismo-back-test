package com.eniomcosta.pismobacktest.services.interfaces;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.entities.Transaction;

public interface TransactionService {
    Transaction create(TransactionDTO transactionDTO);
}
