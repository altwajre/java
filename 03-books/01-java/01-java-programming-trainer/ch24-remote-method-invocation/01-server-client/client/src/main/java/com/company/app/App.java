package com.company.app;

import java.rmi.Naming;

public class App
{
    public static void main( String[] args )
    {
        String stockName = "AAPL";
        try {
            StockServer myServer = (StockServer)Naming.lookup("rmi://localhost:1099/QuoteService");
            String price = myServer.getQuote(stockName);
            if(price != null){
                System.out.println("The price of " + stockName + " is: $" + price);
            }
            else{
                System.out.println("Invalid Nasdaq symbol. " +
                        "Please use one of these:" +
                        myServer.getNasdaqSymbols().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println( "Hello World!" );
    }
}
