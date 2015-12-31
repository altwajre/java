package com.company.app;

import java.io.FileInputStream;

/*
Checked exceptions are checked at compile-time.
It is named as checked exception because these exceptions are checked at Compile time.
 */
public class App
{
    public static void main( String[] args )
    {
        FileInputStream fileInputStream = null;
        /*
        This constructor FileInputStream(File filename) throws
        FileNotFoundException which is a checked exception
         */
        fileInputStream = new FileInputStream("myfile.txt");

        int k;
        /*
        read() method of FileInputStream class also throws
        a checked exception: IOException
         */
        while((k = fileInputStream.read()) != -1){
            System.out.println(((char) k));
        }

        /*
        close() method closes the file input stream throws IOException
         */
        fileInputStream.close();
    }
}
/*
Compile errors:
Error:(18, 27) java: unreported exception java.io.FileNotFoundException; must be caught or declared to be thrown
Error:(25, 40) java: unreported exception java.io.IOException; must be caught or declared to be thrown
Error:(32, 30) java: unreported exception java.io.IOException; must be caught or declared to be thrown
 */
