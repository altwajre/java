package com.company.app;

import java.io.*;

public class App
{
    public static void main( String[] args )
    {
        fileInputStreamTest();
        fileOutputStreamTest();
    }

    private static void fileOutputStreamTest() {
        System.out.println("# fileOutputStreamTest");
        int somedata[] = {84, 104, 105, 115, 32, 105, 115, 32, 97, 32, 116, 101, 115, 116, 32, 102, 105, 108, 101, -1};
        try (FileOutputStream myFile = new FileOutputStream("output.dat")){
            for(int i = 0; i < somedata.length; i++){
                myFile.write(somedata[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileInputStreamTest() {
        System.out.println("# fileInputStreamTest");
        try {
            FileInputStream myFile = new FileInputStream("input.dat");
            boolean eof = false;
            while(!eof){
                int byteValue = myFile.read();
                System.out.print(byteValue + " ");
                if(byteValue == -1){
                    eof = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }
}
/*
output:
# fileInputStreamTest
84 104 105 115 32 105 115 32 97 32 116 101 115 116 32 102 105 108 101 -1
# fileOutputStreamTest

open output.dat
 */