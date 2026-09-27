package br.com.zenon.fraud;

import java.io.IOException;
import java.util.Optional;

public class Main {

    static void main(String[] args) throws IOException {
        String archive = "../data/logs.csv";

        TransactionRepository repository = new TransactionRepositoryMap();

        String name = "C1868032458";

        long ini, fim;

        ini = System.nanoTime();

        Optional<Transaction> transactionOptional = repository.findByNameOrigem(name);

        fim = System.nanoTime();

        IO.println("Busca levou " + (fim - ini)  + "ms");

        if(transactionOptional.isPresent()){
            IO.println(transactionOptional.get());
        } else {
            IO.println("Transação não encontrada para o cliente " + name);
        }


    }
}
