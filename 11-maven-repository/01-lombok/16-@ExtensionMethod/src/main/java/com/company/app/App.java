package com.company.app;

import lombok.experimental.ExtensionMethod;

//https://dzone.com/articles/lomboks-extension-methods
class Extensions {
    public static boolean isAllCap(String orgString) {
        return !orgString.matches(".*[a-z].*");
    }
}

@ExtensionMethod(Extensions.class)
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!".isAllCap() );
    }
}
