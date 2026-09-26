package br.com.zenon.fraud;

import java.math.BigDecimal;

public class Main {

    static void main(String[] args) {
        Transaction transaction1 = new Transaction(
                1,
                TransactionalType.PAYMENT,
                new BigDecimal("9839.64"),
                new TransactionalCostumer(
                        "C1231006815",
                        new BigDecimal("170136.0"),
                        new BigDecimal("160296.36")),
                new TransactionalCostumer(
                        "M1979787155",
                        BigDecimal.ZERO,
                        BigDecimal.ZERO),
                false,
                false
        );

        Transaction transaction2 = new Transaction(
                743,
                TransactionalType.CASH_OUT,
                new BigDecimal("850002.52"),
                new TransactionalCostumer(
                        "C1280323807",
                        new BigDecimal("850002.52"),
                        BigDecimal.ZERO),
                new TransactionalCostumer(
                        "C873221189",
                        new BigDecimal("6510099.11"),
                        new BigDecimal("7360101.63")),
                true,
                false
        );

        IO.println("Transação 1: " + transaction1);
        IO.println("Transação 2: " + transaction2);
    }
}
