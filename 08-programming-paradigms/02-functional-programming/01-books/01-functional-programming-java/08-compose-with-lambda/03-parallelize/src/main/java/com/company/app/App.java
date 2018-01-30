package com.company.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
class StockInfo{
    public final String ticker;
    public final BigDecimal price;
    public StockInfo(final String symbol, final BigDecimal thePrice){
        ticker = symbol;
        price = thePrice;
    }
    public String toString(){
        return String.format("ticker: %s price: %g", ticker, price);
    }
}
class StockUtil{
    public static StockInfo getPrice(final String ticker){
        return new StockInfo(ticker, YahooFinance.getPrice(ticker));
    }
    public static Predicate<StockInfo> isPriceLessThan(final int price){
        return stockInfo -> stockInfo.price.compareTo(BigDecimal.valueOf(price)) < 0;
    }
    public static StockInfo pickHigh(final StockInfo stock1, final StockInfo stock2){
        return stock1.price.compareTo(stock2.price) > 0 ? stock1 : stock2;
    }
}
public class App
{
    static final List<String> symbols = Arrays.asList(
            "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
            "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU",
            "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");
    static void functionalStyle(final Stream<String> symbols){
        final StockInfo highPriced = symbols
                .map(StockUtil::getPrice)
                .filter(StockUtil.isPriceLessThan(500))
                .reduce(StockUtil::pickHigh)
                .get();
        System.out.println("High priced under $500 is " + highPriced);
    }
    private static void parallelStreamTest() {
        long startTime = System.nanoTime();
        functionalStyle(symbols.parallelStream());
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) + " milliseconds");
    }
    public static void main( String[] args )
    {
        /*
        The slowest involves a call to the web service, incurs a network delay, and has to perform the operation 20 times
        - once for each ticker symbol.
        However, we don’t have to wait for Yahoo to respond to the price for one ticker symbol before we send out the
        request for the next. Web services are quite capable of handling multiple requests concurrently.
         */
        System.out.println("#parallelStreamTest <- FAST");
        parallelStreamTest();
        System.out.println("#streamTest <- SLOW");
        streamTest();
    }
    private static void streamTest() {
        long startTime = System.nanoTime();
        functionalStyle(symbols.stream());
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) + " milliseconds");
    }
}
/*
output:
#parallelStreamTest <- FAST
High priced under $500 is ticker: IBM price: 149.330
844 milliseconds
#streamTest <- SLOW
High priced under $500 is ticker: IBM price: 149.330
4853 milliseconds
 */
