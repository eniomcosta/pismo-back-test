package com.eniomcosta.pismobacktest.fixtures.entities;

import com.eniomcosta.pismobacktest.entities.Account;

public class AccountFixture {
    public static Account buildDefault() {
        Account account = new Account();
        account.setId(1L);
        account.setDocumentNumber("12345678900");

        return account;
    }
}
