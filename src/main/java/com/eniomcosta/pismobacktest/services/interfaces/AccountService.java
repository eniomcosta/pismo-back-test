package com.eniomcosta.pismobacktest.services.interfaces;

import com.eniomcosta.pismobacktest.dtos.AccountDTO;
import com.eniomcosta.pismobacktest.entities.Account;

public interface AccountService {
    Account create(AccountDTO accountDto);
    Account findById(Long accountId);
}
