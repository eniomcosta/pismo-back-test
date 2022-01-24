package com.eniomcosta.pismobacktest.controllers;

import com.eniomcosta.pismobacktest.dtos.TransactionDTO;
import com.eniomcosta.pismobacktest.entities.Transaction;
import com.eniomcosta.pismobacktest.exceptions.InvalidAmountException;
import com.eniomcosta.pismobacktest.exceptions.InvalidEnumOrdinalException;
import com.eniomcosta.pismobacktest.fixtures.dtos.TransactionDTOFixture;
import com.eniomcosta.pismobacktest.fixtures.entities.TransactionFixture;
import com.eniomcosta.pismobacktest.services.interfaces.TransactionService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @Test
    void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
        mvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content("invalid input"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenContentInInvalid_thenReturnsStatus400() throws Exception {
        mvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(TransactionDTO.builder().build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenAccountProvidedIsInvalid_thenReturnsStatus404() throws Exception {
        TransactionDTO transactionDTO = TransactionDTOFixture.buildDefaultDebt();

        when(transactionService.create(any())).thenThrow(EntityNotFoundException.class);

        mvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenOperationTypeIDProvidedIsInvalid_thenReturnsStatus400() throws Exception {
        TransactionDTO transactionDTO = TransactionDTOFixture.buildDefaultDebt();

        when(transactionService.create(any())).thenThrow(InvalidEnumOrdinalException.class);

        mvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenAmountEqualsZeroIsProvided_thenReturnsStatus400() throws Exception {
        TransactionDTO transactionDTO = TransactionDTOFixture.buildDefaultDebt();

        when(transactionService.create(any())).thenThrow(InvalidAmountException.class);

        mvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInputValidDebtOperation_thenReturnsStatus200() throws Exception {
        TransactionDTO transactionDTO = TransactionDTOFixture.buildDefaultDebt();
        Transaction transaction = TransactionFixture.buildDefaultDebt();

        when(transactionService.create(any())).thenReturn(transaction);

        mvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isCreated());

        ArgumentCaptor<TransactionDTO> argument = ArgumentCaptor.forClass(TransactionDTO.class);

        verify(transactionService, times(1)).create(argument.capture());
        assertEquals(transactionDTO.getAmount(), argument.getValue().getAmount());
        assertEquals(transactionDTO.getAccountId(), argument.getValue().getAccountId());
        assertEquals(transactionDTO.getOperationTypeId(), argument.getValue().getOperationTypeId());
    }
}
