package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(
        Integer step,
        TransactionType type,
        BigDecimal amount,
        TransactionalCostumer origin,
        TransactionalCostumer recipient,
        Boolean isFraud,
        Boolean isFlaggedFraud
) {

    public Transaction {
        Objects.requireNonNull(step);
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(recipient);
        Objects.requireNonNull(isFraud);
        Objects.requireNonNull(isFlaggedFraud);

        if(step <= 0) throw new IllegalArgumentException("step should be positive: " + step);
        if(amount.signum() < 0) throw new IllegalArgumentException("step should be positive: " + step);

    }
}
