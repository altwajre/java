package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        String name = "Tom";
        System.out.println( "Concat: Hello " + name + "!" );

        System.out.format("Format: Hello %s!", name);
    }
}
