package com.eniomcosta.pismobacktest.converters;

import com.eniomcosta.pismobacktest.dtos.AccountDTO;
import com.eniomcosta.pismobacktest.entities.Account;

public class AccountConverter {
    public static AccountDTO toDto(Account account){
        return AccountDTO.builder()
                .id(account.getId())
                .documentNumber(account.getDocumentNumber()).build();
    }

    public static Account toEntity(AccountDTO accountDto){
        return Account.builder()
                .documentNumber(accountDto.getDocumentNumber())
                .build();
    }
}
