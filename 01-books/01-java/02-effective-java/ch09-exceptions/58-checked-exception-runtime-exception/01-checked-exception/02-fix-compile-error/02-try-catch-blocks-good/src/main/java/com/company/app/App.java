package com.company.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
Good approach
You should give meaningful message for each exception type so that it would be easy for someone to understand the error.
 */
public class App {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        /*
        This constructor FileInputStream(File filename) throws
        FileNotFoundException which is a checked exception
         */
        try {
            fileInputStream = new FileInputStream("myfile.txt");
        } catch (FileNotFoundException e) {
            System.out.println("The specified file is not present at the given path");
        }

        int k;
        /*
        read() method of FileInputStream class also throws
        a checked exception: IOException
         */
        try {
            while ((k = fileInputStream.read()) != -1) {
                System.out.println(((char) k));
            }

        /*
        close() method closes the file input stream throws IOException
         */
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("I/O error occured: " + e);
        }
    }
}
/*
output:
Exception in thread "main" The specified file is not present at the given path
java.lang.NullPointerException
	at com.company.app.App.main(App.java:26)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */
