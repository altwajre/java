package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        System.setProperty("com.company.version", "0.0.1");
        System.out.println(System.getProperty("com.company.version"));
        System.out.println(System.getProperty("path.separator"));
        System.getProperties().list(System.out);
    }
}
