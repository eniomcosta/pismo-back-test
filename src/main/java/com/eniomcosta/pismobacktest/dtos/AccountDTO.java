package com.eniomcosta.pismobacktest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class AccountDTO {

    @JsonProperty("account_id")
    private Long id;

    @NotEmpty(message = "Document Number should not be empty")
    @Size(min = 11, max = 11, message = "Document Number should have between 11 and characters")
    @JsonProperty("document_number")
    private String documentNumber;
}
