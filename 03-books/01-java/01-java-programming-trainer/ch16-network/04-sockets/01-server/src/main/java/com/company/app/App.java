package com.company.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App
{
    public static void main( String[] args )
    {
        ServerSocket serverSocket = null;
        Socket client = null;

        BufferedReader inbound = null;
        OutputStream outbound = null;

        try{
            // Create a server socket
            serverSocket = new ServerSocket(3000);
            System.out.println("Waiting for a quote request...");
            while(true){
                // wait for a request
                client = serverSocket.accept();
                // Get the streams
                inbound = new BufferedReader(new InputStreamReader(client.getInputStream()));
                outbound = client.getOutputStream();

                String symbol = inbound.readLine();

                System.out.println(symbol);

                // Generate a random stock price
                String price = (new Double(Math.random() * 100)).toString();
                outbound.write(("\n The price of " + symbol + " is " + price + "\n").getBytes());

                System.out.println("Request for " + symbol + " has been processed - the price of "
                        + symbol + " is " + price + "\n");
                outbound.write("End\n".getBytes());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                inbound.close();
                outbound.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
/*
output:
Waiting for a quote request...
Request for AAPL has been processed - the price of AAPL is 51.31315859620034
 */