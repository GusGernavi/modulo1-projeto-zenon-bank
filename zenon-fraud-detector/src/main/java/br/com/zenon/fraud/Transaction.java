package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(
        Integer step,
        TransactionalType type,
        BigDecimal amount,
        TransactionalCostumer origin,
        TransactionalCostumer recipient,
        Boolean isFraud,
        Boolean isFlaggedFraud
) {
}
