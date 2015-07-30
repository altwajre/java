package com.company.app;

public class App
{
    /*
    https://en.wikipedia.org/wiki/Factorial
    For example, the factorial of 4 is
    4! = 1 * 2 * 3 * 4 = 24
     */
    static int factorial(int n){
        int result = 1;
        for(int i = 1; i <= n; i++){
            result = result * i;
        }
        return result;
    }
    public static void main( String[] args )
    {
        System.out.println("7!: " + factorial(7));
    }
}
