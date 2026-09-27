package br.com.zenon.fraud;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionRepositoryMap implements TransactionRepository {

    private final Map<String, Transaction> mapTransactional;

    public TransactionRepositoryMap() {
        TransactionIngestor ingestor = new TransactionIngestor();
        this.mapTransactional = ingestor.read("../data/logs.csv").stream().collect(Collectors.toMap(t -> t.origin().name(), t -> t));
    }

    public Optional<Transaction> findByNameOrigem(String name){
        return Optional.ofNullable(mapTransactional.get(name));
    }
}
