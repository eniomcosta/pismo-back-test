package com.eniomcosta.pismobacktest.enums;

import java.util.Set;

public enum OperationType {
    COMPRA_A_VISTA(1, "Compra à vista"),
    COMPRA_PARCELADA(2, "Compra parcelada"),
    SAQUE(3, "Saque"),
    PAGAMENTO(4,"Pagamento");

    OperationType(int i, String s) {}

    private static OperationType[] getList() {
        return OperationType.values();
    }

    public static OperationType getOperationById(int id) {
        return getList()[id];
    }

    private Set<OperationType> getDebtOperations() {
        return Set.of(COMPRA_A_VISTA, COMPRA_PARCELADA, SAQUE);
    }

    private Set<OperationType> getCreditOperations() {
        return Set.of(PAGAMENTO);
    }

    public Boolean isDebtOperation(OperationType operationType) {
        return getDebtOperations().contains(operationType);
    }

    public Boolean isCreditOperation(OperationType operationType) {
        return getCreditOperations().contains(operationType);
    }
}
