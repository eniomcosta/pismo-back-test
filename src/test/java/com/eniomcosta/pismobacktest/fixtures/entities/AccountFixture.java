package com.eniomcosta.pismobacktest.fixtures.entities;

import com.eniomcosta.pismobacktest.entities.Account;

import java.util.HashSet;

public class AccountFixture {
    public static Account buildDefault() {
        return Account.builder()
                .id(1L)
                .documentNumber("12345678900")
                .transactions(new HashSet<>())
                .build();
    }
}
