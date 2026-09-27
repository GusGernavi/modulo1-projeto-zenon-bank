package br.com.zenon.fraud;

import java.util.List;
import java.util.Optional;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final List<Transaction> listTransactional;

    public TransactionRepositoryImpl() {
        TransactionIngestor ingestor = new TransactionIngestor();
        this.listTransactional = ingestor.read("../data/logs.csv");
    }

    public Optional<Transaction> findByNameOrigem(String name){
        return listTransactional.stream().filter(t -> t.origin().name().equals(name)).findFirst();
    }
}
