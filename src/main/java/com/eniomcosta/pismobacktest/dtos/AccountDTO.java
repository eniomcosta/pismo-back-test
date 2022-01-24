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
    @Size(min = 11, max = 11, message = "Document Number should have between 11 and characters")
    @JsonProperty("document_number")
    private String documentNumber;
}
