package com.company.app;

/*
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("#left shift");
        System.out.println("1 << 3 = " + (1 << 3) + " - binary is 1000");  // 8

        System.out.println("#right shift");
        System.out.println("8 >> 1 = " + (8 >> 1) + " - binary is 100");   // 4
        System.out.println("7 >> 1 = " + (7 >> 1) + " - binary is 11");    // 3
        System.out.println("6 >> 1 = " + (6 >> 1) + " - binary is 11");    // 3
        System.out.println("5 >> 1 = " + (5 >> 1) + " - binary is 10");    // 2
        System.out.println("4 >> 1 = " + (4 >> 1) + " - binary is 10");    // 2
        System.out.println("3 >> 1 = " + (3 >> 1) + " - binary is 1");     // 1
        System.out.println("2 >> 1 = " + (2 >> 1) + " - binary is 1");     // 1
        System.out.println("1 >> 1 = " + (1 >> 1) + " - binary is 0");     // 0
        System.out.println("0 >> 1 = " + (0 >> 1) + " - binary is 0");     // 0
    }
}
