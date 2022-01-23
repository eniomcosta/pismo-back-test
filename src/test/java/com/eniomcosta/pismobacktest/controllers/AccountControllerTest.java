package com.eniomcosta.pismobacktest.controllers;

import com.eniomcosta.pismobacktest.dtos.AccountDTO;
import com.eniomcosta.pismobacktest.entities.Account;
import com.eniomcosta.pismobacktest.fixtures.dtos.AccountDTOFixture;
import com.eniomcosta.pismobacktest.fixtures.entities.AccountFixture;
import com.eniomcosta.pismobacktest.services.interfaces.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    @Test
    void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
        mvc.perform(post("/accounts")
                        .contentType("application/json")
                        .content("invalid content"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenDocumentNumberSizeIsInvalid_thenReturnsStatus400() throws Exception {
        AccountDTO accountDTO = AccountDTOFixture.buildDefault();
        accountDTO.setDocumentNumber("123");

        mvc.perform(post("/accounts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accountDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInputValid_thenReturnsStatus200() throws Exception {
        AccountDTO accountDTO = AccountDTOFixture.buildDefault();
        Account account = AccountFixture.buildDefault();

        when(accountService.create(any())).thenReturn(account);

        mvc.perform(post("/accounts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accountDTO)))
                .andExpect(status().isCreated());

        ArgumentCaptor<AccountDTO> argument = ArgumentCaptor.forClass(AccountDTO.class);

        verify(accountService, times(1)).create(argument.capture());
        assertEquals(accountDTO.getDocumentNumber(), argument.getValue().getDocumentNumber());
    }

    @Test
    void shouldNotFindAccount_thenReturnsStatus404() throws Exception {
        when(accountService.findById(any())).thenThrow(EntityNotFoundException.class);

        Long testId = 99L;

        mvc.perform(get(String.format("/accounts/%d", testId)))
                .andExpect(status().isBadRequest());

        verify(accountService, times(1)).findById(testId);

    }

    @Test
    void shouldFindAccount_thenReturnsStatus404() throws Exception {
        Account account = AccountFixture.buildDefault();

        when(accountService.findById(eq(account.getId()))).thenReturn(account);

        mvc.perform(get(String.format("/accounts/%d", account.getId())))
                .andExpect(status().isOk());

        verify(accountService, times(1)).findById(account.getId());
    }
}
