package br.com.zenon.fraud;

import java.io.IOException;
import java.util.List;

public class Main {

    static void main(String[] args) throws IOException {
        String archive = "../data/paysim_with_bad_data.csv";

        TransactionIngestor ingestor = new TransactionIngestor();

        List<Transaction> firstsResults = ingestor.findFirstsResultsWithFiles(archive);
        IO.println(firstsResults.size());

        firstsResults.forEach(IO::println);

    }
}
