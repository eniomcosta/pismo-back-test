package com.eniomcosta.pismobacktest.fixtures.dtos;

import com.eniomcosta.pismobacktest.dtos.AccountDTO;

public class AccountDTOFixture {
    public static AccountDTO buildDefault() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setDocumentNumber("12345678900");

        return accountDTO;
    }
}
