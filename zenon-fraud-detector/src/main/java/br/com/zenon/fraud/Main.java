package br.com.zenon.fraud;

import java.io.IOException;
import java.util.List;

public class Main {

    static void main(String[] args) throws IOException {
        String archive = "../data/logs.csv";

        TransactionIngestor ingestor = new TransactionIngestor();

        List<Transaction> firstsResults = ingestor.findFirstsResultsWithFiles(archive);

        firstsResults.stream().limit(10).forEach(IO::println);

    }
}
