package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions){
        this.transactions = transactions;
    }

    public int totalDeFraudes(){
        return transactions.stream().filter(Transaction::isFraud).toList().size();
    }

    public List<BigDecimal> maioresFraudes() {
        var onlyFrauds = transactions.stream().filter(Transaction::isFraud).toList();
        return onlyFrauds.stream().sorted(Comparator.comparing(Transaction::amount, Comparator.reverseOrder())).limit(3).map(Transaction::amount).toList();
    }

    public List<String> maioresClientesSuspeitos(){
        var onlyFrauds = transactions.stream().filter(Transaction::isFraud).toList();
        return onlyFrauds.stream().sorted(Comparator.comparing(Transaction::amount, Comparator.reverseOrder())).map(t -> t.origin().name()).distinct().limit(5).toList();
    }

    public BigDecimal prejuizoTotal() {
        var onlyFrauds = transactions.stream().filter(Transaction::isFraud).toList();
        return onlyFrauds.stream().map(Transaction::amount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public Map<TransactionalType, List<Transaction>> fraudesPorTipo(){
        var onlyFrauds = transactions.stream().filter(Transaction::isFraud).toList();
        return onlyFrauds.stream().sorted(Comparator.comparing(Transaction::type)).collect(Collectors.groupingBy(Transaction::type));
    }
 }
