package com.company.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

class YahooFinance {
    public static BigDecimal getPrice(final String ticker) {
        try {
            final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            final String data = reader.lines().skip(1).findFirst().get();
            final String[] dataItems = data.split(",");
            return new BigDecimal(dataItems[dataItems.length - 1]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class App {
    public static final List<String> symbols = Arrays.asList(
            "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
            "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU",
            "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

    public static void main(String[] args) {
        final BigDecimal HUNDRED = new BigDecimal("100");
        System.out.println("Stocks priced over $100 are " +
                symbols.stream()
                        .filter(s -> YahooFinance.getPrice(s).compareTo(HUNDRED) > 0)
                        .sorted()
                        .collect(joining(", ")));
    }
}
/*
output:
Stocks priced over $100 are AAPL, AMZN, GOOG, IBM, INTU
 */
