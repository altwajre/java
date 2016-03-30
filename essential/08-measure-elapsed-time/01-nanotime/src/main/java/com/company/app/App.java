package com.company.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
// http://stackoverflow.com/questions/1770010/how-do-i-measure-time-elapsed-in-java
public class App
{
    static final List<String> symbols = Arrays.asList(
            "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
            "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU",
            "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");
    public static void main( String[] args )
    {
        long startTime = System.nanoTime();
        symbols.forEach(s -> System.out.print(YahooFinance.getPrice(s) + " "));
        System.out.println("");
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) + " milliseconds");
    }
}
/*
output:
2.86 12.28 149.330002 57.41 51.369999 10.83 107.68 93.220001 593.859985 41.869999 28.10 26.16 744.77002 32.380001 102.57 54.709999 40.700001 24.03 89.110001 36.32
4696 milliseconds
 */