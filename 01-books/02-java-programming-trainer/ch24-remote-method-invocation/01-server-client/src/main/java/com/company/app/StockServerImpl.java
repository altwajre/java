package com.company.app;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class StockServerImpl extends UnicastRemoteObject implements StockServer {

    private String price = null;
    private ArrayList<String> nasdaqSymbols = new ArrayList<>();
    public StockServerImpl() throws RemoteException {
        super();
        // Define some hard-coded NASDAQ symbols
        nasdaqSymbols.add("AAPL");
        nasdaqSymbols.add("MSFT");
        nasdaqSymbols.add("YHOO");
        nasdaqSymbols.add("AMZN");
        nasdaqSymbols.add("MOT");
    }
    public String getQuote(String symbol) throws RemoteException {
        if(nasdaqSymbols.indexOf(symbol.toUpperCase()) != -1){
            // Generate a randeom price for valid symbols
            price = (new Double(Math.random() * 100)).toString();
        }
        return price;
    }

    public List<String> getNasdaqSymbols() throws RemoteException {
        return nasdaqSymbols;
    }
}
