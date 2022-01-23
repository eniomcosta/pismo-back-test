package com.eniomcosta.pismobacktest.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum OperationType {
    COMPRA_A_VISTA(1, "Compra à vista"),
    COMPRA_PARCELADA(2, "Compra parcelada"),
    SAQUE(3, "Saque"),
    PAGAMENTO(4,"Pagamento");

    private final Integer code;
    private final String description;

    private static List<OperationType> getList() {
        return Arrays.asList(OperationType.values());
    }

    public static Optional<OperationType> getOperationById(int id) {
        return getList().stream().filter(operationType -> operationType.code == id).findFirst();
    }

    private static Set<OperationType> getDebtOperations() {
        return Set.of(COMPRA_A_VISTA, COMPRA_PARCELADA, SAQUE);
    }

    private static Set<OperationType> getCreditOperations() {
        return Set.of(PAGAMENTO);
    }

    public Boolean isDebtOperation() {
        return getDebtOperations().contains(this);
    }

    public Boolean isCreditOperation(OperationType operationType) {
        return getCreditOperations().contains(this);
    }
}
