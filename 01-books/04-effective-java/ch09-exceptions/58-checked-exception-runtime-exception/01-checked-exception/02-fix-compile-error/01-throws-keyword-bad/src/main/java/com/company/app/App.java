package com.company.app;

import java.io.FileInputStream;
import java.io.IOException;

/*
Bad approach.
Declare the exception using throws keyword.
As we know that all three occurrences of checked exceptions are inside main() method so one way to avoid the compilation
error is: Declare the exception in the method using throws keyword.
 */
public class App
{
    public static void main( String[] args ) throws IOException // throws keyword
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
output:
Exception in thread "main" java.io.FileNotFoundException: myfile.txt (No such file or directory)
	at java.io.FileInputStream.open0(Native Method)
	at java.io.FileInputStream.open(FileInputStream.java:195)
	at java.io.FileInputStream.<init>(FileInputStream.java:138)
	at java.io.FileInputStream.<init>(FileInputStream.java:93)
	at com.company.app.App.main(App.java:20)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */
