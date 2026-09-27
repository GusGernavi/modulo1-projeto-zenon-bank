package br.com.zenon.fraud;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    public Optional<Transaction> findByNameOrigem(String name);
}
