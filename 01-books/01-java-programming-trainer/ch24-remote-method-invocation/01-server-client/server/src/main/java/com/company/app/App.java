package com.company.app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class App
{
    public static void main( String[] args )
    {
        try {
            // Create the registry on port 1099
            LocateRegistry.createRegistry(1099);
            // Instantiate the StockServerImpl and bind it
            // to the registry under the name QuoteService
            StockServerImpl ssi = new StockServerImpl();
            Naming.rebind("rmi://localhost:1099/QuoteService",ssi);
            System.out.println("<QuoteService> server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
