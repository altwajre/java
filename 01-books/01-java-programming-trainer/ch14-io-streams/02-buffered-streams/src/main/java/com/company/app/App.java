package com.company.app;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("# bufferedInputStreamTest");
        try(FileInputStream myFile = new FileInputStream("input.dat");
            BufferedInputStream buff = new BufferedInputStream(myFile);){

            boolean eof = false;
            while (!eof) {
                int byteValue = buff.read();
                System.out.print(byteValue + " ");
                if(byteValue == -1){
                    eof = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
# bufferedInputStreamTest
84 104 105 115 32 105 115 32 97 32 116 101 115 116 32 102 105 108 101 -1
 */
