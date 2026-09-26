package br.com.zenon.fraud;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Main {

    static void main(String[] args) throws IOException {
        String archive = "../data/logs.csv";

        TransactionIngestor ingestor = new TransactionIngestor();

        List<Transaction> results = ingestor.read(archive);

        Locale locale = Locale.of("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(results);
        var totalDeFraudes = fraudAnalyzer.totalDeFraudes();
        var top3FraudesDeMaiorValor = fraudAnalyzer.maioresFraudes();
        var top5ClientesSuspeitos = fraudAnalyzer.maioresClientesSuspeitos();
        var prejuizoTotal = fraudAnalyzer.prejuizoTotal();
        var fraudesPorTipo = fraudAnalyzer.fraudesPorTipo();

        IO.println("1. Total de Fraudes: " + totalDeFraudes);

        IO.println("2. Top 3 Fraudes de Maior Valor: ");
        top3FraudesDeMaiorValor.forEach(v -> IO.println(formatter.format(v)));

        IO.println("3. Clientes Suspeitos: ");
        top5ClientesSuspeitos.forEach(IO::println);

        IO.println("4. Prejuizo Total: " + formatter.format(prejuizoTotal));

        IO.println("5. Fraudes por Tipo: ");
        fraudesPorTipo.forEach((k, v) -> IO.println(" - " + k + ": " + v.size()));

    }
}
