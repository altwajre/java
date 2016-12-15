package com.company.app;

import java.io.*;

public class App {
    public static void main(String[] args) {
        readerTest();

        writerTest();
    }

    private static void writerTest() {
        System.out.println("# writerTest");
        try(FileOutputStream myFile = new FileOutputStream("output.txt");
            Writer out = new BufferedWriter(new OutputStreamWriter(myFile, "UTF8"));){
            String myAddress = "123 Broadway, New York, NY 10011";
            out.write(myAddress);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void readerTest() {
        System.out.println("# readerTest");
        StringBuffer buffer = new StringBuffer();
        try (FileInputStream myFile = new FileInputStream("input.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(myFile, "UTF8");
             Reader reader = new BufferedReader(inputStreamReader);) {
            int ch; // the code of one character
            while ((ch = reader.read()) > -1) {
                buffer.append((char) ch);
            }
            buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(buffer.toString());
    }
}
/*
output:
# readerTest
This is a test file
# writerTest

open output.txt
 */
