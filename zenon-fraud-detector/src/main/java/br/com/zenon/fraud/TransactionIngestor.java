package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class TransactionIngestor {

    private static final Logger LOGGER = Logger.getLogger(TransactionIngestor.class.getName());
    private static final Integer TOTAL_LINES_READER = 1000;

    public List<Transaction> findFirstsResults(String name){

        List<Transaction> transactions = new ArrayList<>();

        Path path = Path.of("../data/logs.csv");
        try(BufferedReader br = new BufferedReader(new FileReader(path.toFile()))){
            for (int i = 0; (i < TOTAL_LINES_READER && br.readLine() != null); i++) {
                var line = br.readLine();

                Transaction transaction = transformObject(line);
                transactions.add(transaction);
            }
        } catch (IOException ex){
            LOGGER.warning("Erro ao Processar Arquivo");
            throw new RuntimeException("Falha ao processar arquivo", ex);
        }
        return transactions;
    }
    public List<Transaction> findFirstsResultsWithFiles(String name){
        Path path = Path.of("../data/logs.csv");
        try(Stream<String> lines = Files.lines(path)){
            return lines.skip(1).limit(1001).map(this::transformObject).toList();
        } catch (IOException ex){
            LOGGER.warning("Erro ao Processar Arquivo");
            throw new RuntimeException("Falha ao processar arquivo", ex);
        }
    }

    private Transaction transformObject(String line){

        var chunks = line.split(",");

        var step = Integer.valueOf(chunks[0]);
        var type = TransactionalType.valueOf(chunks[1]);
        var amount = new BigDecimal(chunks[2]);
        var nameOrigin = chunks[3];
        var oldBalanceOrig = new BigDecimal(chunks[4]);
        var newBalanceOrig = new BigDecimal(chunks[5]);
        var nameDest = chunks[6];
        var oldBalanceDest = new BigDecimal(chunks[7]);
        var newBalanceDest = new BigDecimal(chunks[8]);
        var isFraud = chunks[9].equals("1");
        var isFlaggedFraud = chunks[10].equals("1");


        return new Transaction(step, type, amount, new TransactionalCostumer(nameOrigin, oldBalanceOrig, newBalanceOrig), new TransactionalCostumer(nameDest, oldBalanceDest, newBalanceDest), isFraud, isFlaggedFraud);
    }
}
