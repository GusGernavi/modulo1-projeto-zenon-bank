package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class TransactionIngestor {

    private static final Logger LOGGER = Logger.getLogger(TransactionIngestor.class.getName());
    private static final Integer TOTAL_LINES_READER = 100000;

    public List<Transaction> read(String name){
        Path path = Path.of(name);
        try(Stream<String> lines = Files.lines(path)){
            return lines.skip(1).limit(TOTAL_LINES_READER).map(this::transformObject).filter(Optional::isPresent).map(Optional::get).toList();
        } catch (IOException ex){
            LOGGER.warning("Erro ao Processar Arquivo");
            throw new RuntimeException("Falha ao processar arquivo", ex);
        }
    }
    private Optional<Transaction> transformObject(String line){
        try{

        var chunks = line.split(",");

        var step = Integer.valueOf(chunks[0]);
        var type = TransactionType.valueOf(chunks[1]);
        var amount = new BigDecimal(chunks[2]);
        var nameOrigin = chunks[3];
        var oldBalanceOrig = new BigDecimal(chunks[4]);
        var newBalanceOrig = new BigDecimal(chunks[5]);
        var nameDest = chunks[6];
        var oldBalanceDest = new BigDecimal(chunks[7]);
        var newBalanceDest = new BigDecimal(chunks[8]);
        var isFraud = chunks[9].equals("1");
        var isFlaggedFraud = chunks[10].equals("1");


        return Optional.of(new Transaction(step, type, amount, new TransactionalCostumer(nameOrigin, oldBalanceOrig, newBalanceOrig), new TransactionalCostumer(nameDest, oldBalanceDest, newBalanceDest), isFraud, isFlaggedFraud));
        } catch (Exception ex){
            System.err.println("Erro: " + line + " | " + ex.getMessage());
        }

        return Optional.empty();
    }
}
