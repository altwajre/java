package com.company.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public static final List<String> symbols = Arrays.asList(
            "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
            "AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU",
            "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

    /*
    Benefits:
    1, ability to parallelize for free
    2, use function composition and higher-order functions
    3, avoid mutability
     */
    static void functionalStyle(final Stream<String> symbols){
        final StockInfo highPriced = symbols
                .map(StockUtil::getPrice)
                .filter(StockUtil.isPriceLessThan(500))
                .reduce(StockUtil::pickHigh)
                .get();
        System.out.println("High priced undrt $500 is " + highPriced);
    }

    public static void main( String[] args )
    {
        System.out.println("#imperativeStyle");
        imperativeStyle();
        System.out.println("#functionalStyle");
        functionalStyle(symbols.stream());
    }

    private static void imperativeStyle() {
        final List<StockInfo> stocks = new ArrayList<>();
        for(String symbol : symbols){
            stocks.add(StockUtil.getPrice(symbol));
        }
        final List<StockInfo> stocksPriceUnder500 = new ArrayList<>();
        final Predicate<StockInfo> isPriceLessThan500 = StockUtil.isPriceLessThan(500);
        for (StockInfo stock : stocks){
            if(isPriceLessThan500.test(stock)){
                stocksPriceUnder500.add(stock);
            }
        }
        StockInfo highPriced = new StockInfo("", BigDecimal.ZERO);
        for(StockInfo stock : stocksPriceUnder500){
            highPriced = StockUtil.pickHigh(highPriced, stock);
        }
        System.out.println("High priced under $500 is " + highPriced);
    }
}
/*
output:
#imperativeStyle
High priced under $500 is ticker: IBM price: 149.330
#functionalStyle
High priced undrt $500 is ticker: IBM price: 149.330
 */
