package com.eniomcosta.pismobacktest.converters;

import com.eniomcosta.pismobacktest.dtos.AccountDTO;
import com.eniomcosta.pismobacktest.entities.Account;

public class AccountConverter {
    public static AccountDTO toDto(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setDocumentNumber(account.getDocumentNumber());
        return accountDTO;
    }

    public static Account toEntity(AccountDTO accountDto){
        Account account = new Account();
        account.setDocumentNumber(accountDto.getDocumentNumber());
        return account;
    }
}
