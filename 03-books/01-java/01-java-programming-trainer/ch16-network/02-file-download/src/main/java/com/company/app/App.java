package com.company.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class App
{
    public static void main( String[] args )
    {
        String url = "http://myflex.org/yf/nyc.jpg";
        String fileName = "nyc.jpg";

        URLConnection fileStream = null;
        try{
            URL remoteFile = new URL(url);
            fileStream = remoteFile.openConnection();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try(FileOutputStream fOut = new FileOutputStream(fileName);
            InputStream in = fileStream.getInputStream()){
            // Read a remote file and save it in the local one
            int data;
            System.out.println("Starting the download from " + url);
            while((data = in.read()) != -1){
                fOut.write(data);
            }
            System.out.println("Finished downloading the file " + fileName);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
