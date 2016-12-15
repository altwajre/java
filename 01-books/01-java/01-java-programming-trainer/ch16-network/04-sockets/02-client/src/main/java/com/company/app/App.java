package com.company.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class App
{
    public static void main( String[] args )
    {
        String stockSymbol = "AAPL";
        Socket clientSocket = null;
        try{
            // open a client socket connection
            clientSocket = new Socket("localhost", 3000);
            System.out.println("Client: " + clientSocket);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try(OutputStream outbound = clientSocket.getOutputStream();
            BufferedReader inbound = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()))){
            // send stock symbol to the server
            outbound.write((stockSymbol).getBytes());
            String quote;
            while (true){
//                System.out.println("a");
                quote = inbound.readLine();
//                System.out.println("quote="+quote);
                if(quote.length() == 0) continue;;
                if(quote.equals("End")){
                    break;
                }
                System.out.println("Got the quote for " + stockSymbol + ":" + quote);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
/*
output:
Client: Socket[addr=localhost/127.0.0.1,port=3000,localport=54836]
Got the quote for AAPL
End: The price of AAPL is 51.31315859620034
 */
