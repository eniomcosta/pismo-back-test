package com.eniomcosta.pismobacktest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

    @JsonProperty(value = "account_id")
    private Long id;

    @NotEmpty(message = "Document Number should not be empty")
    @JsonProperty("document_number")
    private String documentNumber;
}
