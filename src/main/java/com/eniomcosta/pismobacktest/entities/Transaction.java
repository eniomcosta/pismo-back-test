package com.eniomcosta.pismobacktest.entities;

import com.eniomcosta.pismobacktest.enums.OperationType;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Account is mandatory")
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @NotNull(message = "Operation Type is mandatory")
    @Enumerated
    @Column(name = "operation_type", nullable = false)
    private OperationType operationType;

    @NotNull(message = "Amount is mandatory")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @CreatedDate
    private Instant createdAt;
}
