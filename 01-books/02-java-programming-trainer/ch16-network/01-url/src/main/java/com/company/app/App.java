package com.company.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class App
{
    public static void main( String[] args )
    {
        uRLConnectionTest();

        urlOpenStreamTest();
    }

    private static void urlOpenStreamTest() {
        System.out.println("# urlOpenStreamTest");
        try {
            URL url = new URL("http://www.google.com");
            InputStream in = url.openStream();
            BufferedReader buff = new BufferedReader(new InputStreamReader(in));
            while(true){
                String nextLine = buff.readLine();
                if(nextLine != null){
                    System.out.println(nextLine);
                }
                else{
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void uRLConnectionTest() {
        System.out.println("# uRLConnectionTest");
        String nextLine;
        URL url = null;
        URLConnection urlConnection = null;
        try{
            // Assume index.html is a default home page name
            url = new URL("http://www.google.com");
            urlConnection = url.openConnection();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try(InputStreamReader inStream = new InputStreamReader(urlConnection.getInputStream(), "UTF8");
            BufferedReader buff = new BufferedReader(inStream)) {
            // Read and print the content of the Google's home page
            while(true){
                nextLine = buff.readLine();
                if(nextLine != null){
                    System.out.println(nextLine);
                }
                else{
                    break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
/*
output:
the output is big, so run to see it
 */