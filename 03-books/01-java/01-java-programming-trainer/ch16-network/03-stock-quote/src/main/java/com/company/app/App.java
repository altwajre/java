package com.company.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

public class App
{
    public static void main( String[] args )
    {
        String symbol = "AAPL";
        String csvString;
        URL url = null;
        URLConnection urlConnection = null;

        try{
            url = new URL("http://quote.yahoo.com/d/quotes.csv?s="
            + symbol + "&f=sl1d1t1c1ohgv&e=.csv");
            urlConnection = url.openConnection();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try(InputStreamReader inStream = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader buff = new BufferedReader(inStream)){
            // get the quote as a csv string
            csvString = buff.readLine();
            // parse the csv string
            StringTokenizer tokenizer = new StringTokenizer(csvString, ",");
            String ticker = tokenizer.nextToken();
            String price = tokenizer.nextToken();
            String tradeDate = tokenizer.nextToken();
            String tradeTime = tokenizer.nextToken();
            System.out.println("Symbol: " + ticker + " Price: " + price + " Date: " + tradeDate + " Time: " + tradeTime);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
/*
output:
Symbol: "AAPL" Price: 93.59 Date: "6/28/2016" Time: "4:00pm"
 */
