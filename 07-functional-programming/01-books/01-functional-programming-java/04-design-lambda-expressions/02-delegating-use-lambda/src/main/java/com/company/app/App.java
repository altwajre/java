package com.company.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

class CalculateNAV{
    final private Function<String, BigDecimal> priceFinder;
    public CalculateNAV(Function<String, BigDecimal> priceFinder) {
        this.priceFinder = priceFinder;
    }

    public BigDecimal computeStockWorth(final String ticker, final int shares){
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }
}
class YahooFinance{
    /*
    Lambda expressions and method references can throw checked exceptions only if those exceptions are declared using
    the throws clause in the abstract method of the functional interface they stand in for. Since the Function
    interface’s apply method doesn’t specify any expected exceptions, we can’t directly throw the checked exception
    in this example. As a workaround, we wrapped the exception into the unchecked RuntimeException. The lambda
    expression now simple passes the exception through, and upstream in the code will have to handle it.
     */
    public static BigDecimal getPrice(final String ticker){
        try {
            final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            final String data = reader.lines().skip(1).findFirst().get();
            final String[] dataItems = data.split(",");
            return new BigDecimal(dataItems[dataItems.length - 1]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
public class App
{
    static void computeStockWorthStubTest(){
        final CalculateNAV calculateNAV = new CalculateNAV(ticker -> new BigDecimal("6.01"));
        System.out.println("Test - 1000 GOOG stock worth: "+calculateNAV.computeStockWorth("GOOG", 1000));
    }
    public static void main( String[] args )
    {
        System.out.println("#computeStockWorthStubTest");
        computeStockWorthStubTest();

        System.out.println("#computeStockWorth");
        computeStockWorth();
    }

    private static void computeStockWorth() {
        final CalculateNAV calculateNAV = new CalculateNAV(YahooFinance::getPrice);
        System.out.println("yahoo - 100 GOOG stock worth: "+calculateNAV.computeStockWorth("GOOG", 100));
    }
}
/*
output:
#computeStockWorthStubTest
Test - 1000 GOOG stock worth: 6010.00
#computeStockWorth
yahoo - 100 GOOG stock worth: 73529.998800
 */
