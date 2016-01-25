package com.company.app;

/*
https://en.wikipedia.org/wiki/Prime_number
It is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 */
public class App
{
    static boolean isPrime(int number){
        if(number == 2) return true;
        if(number < 2) return false;
        // return false if it is an even number
        if(number % 2 == 0) return false;
        for(int i = 3; i <= Math.sqrt(number); i+=2){
            if(number % i == 0) return false;
        }
        return true;
    }
    public static void main( String[] args )
    {
        System.out.println(isPrime(25));  // false
        System.out.println(isPrime(13));  // true
    }
}
/*
output:
false
true
 */

