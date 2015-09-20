package com.company.app;

/*

Question:
Write a function to swap a number in place without temporary variables.

 */
public class App 
{
    static void swap(int a, int b){
        // Example for a = 9, b = 4
        a = a - b; // a = 9 - 4 = 5
        b = a + b; // b = 5 + 4 = 9
        a = b - a; // a = 9 - 5 = 4
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
    public static void main( String[] args )
    {
        int a = 1672;
        int b = 9332;
        swap(a, b);
    }
}
