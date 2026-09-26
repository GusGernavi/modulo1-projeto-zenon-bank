package br.com.zenon.fraud;

import java.math.BigDecimal;

public record TransactionalCostumer(
        String name,
        BigDecimal oldBalance,
        BigDecimal newBalance
) {
}
