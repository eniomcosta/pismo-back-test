package com.eniomcosta.pismobacktest.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashSet;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(name = "uc_account_document", columnNames = {"document_number"})
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Document Number is mandatory")
    @Column(name = "document_number", unique = true, length = 11, nullable = false)
    private String documentNumber;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Transaction> transactions = new LinkedHashSet<>();
}

