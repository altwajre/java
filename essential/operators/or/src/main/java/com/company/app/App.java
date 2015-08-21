package com.company.app;

/*
output:
false || false = false
false || true = true
true || false = true
true || true = true

 */
public class App 
{
    public static void main( String[] args )
    {
        boolean x = false;
        boolean y = false;
        boolean result = x || y;
        System.out.println(x + " || " + y + " = " + result);

        x = false;
        y = true;
        result = x || y;
        System.out.println(x + " || " + y + " = " + result);

        x = true;
        y = false;
        result = x || y;
        System.out.println(x + " || " + y + " = " + result);

        x = true;
        y = true;
        result = x || y;
        System.out.println(x + " || " + y + " = " + result);
    }
}
