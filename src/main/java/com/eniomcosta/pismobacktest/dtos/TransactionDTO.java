package com.eniomcosta.pismobacktest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransactionDTO {

    @NotNull(message = "Operation Type is mandatory")
    @JsonProperty("operation_type")
    private Integer operationTypeId;

    @NotNull(message = "Amount is mandatory")
    @Min(value = 0L, message = "Amount must be bigger than 0")
    @JsonProperty("amount")
    private Double amount;

    @NotNull(message = "Account Id is mandatory")
    @JsonProperty("account_id")
    private Long accountId;
}
