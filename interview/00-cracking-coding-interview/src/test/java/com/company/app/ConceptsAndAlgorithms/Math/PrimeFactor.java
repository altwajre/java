package com.company.app.ConceptsAndAlgorithms.Math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
https://en.wikipedia.org/wiki/Prime_factor
 */
public class PrimeFactor {

    @Test
    public void Test() {
        printPrimeFactor(360);
        System.out.print("Primes: ");
        System.out.println(primes);
    }

    // divided by prime numbers from small to large. when number divided by 2 has remainder, move to next prime 3
    static List<Integer> primes = new ArrayList<>();
    static void printPrimeFactor(int number){
        // preprocess: add prime numbers in number to list
        for(int i = 2; i <= Math.sqrt(number); i++){
            if(isPrime(i)){
                primes.add(i);
            }
        }

        for(int i = 0; i < primes.size(); i++){
            while(number % primes.get(i) == 0){ // number can be divided by prime number without remainder
                System.out.printf("%s ", primes.get(i));
                number /= primes.get(i);
                System.out.println(number);
            }
        }
    }
    /*
    https://en.wikipedia.org/wiki/Prime_number
    It is a natural number greater than 1 that has no positive divisors other than 1 and itself.
     */
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
}
/*
output:
2 180
2 90
2 45
3 15
3 5
5 1
Primes: [2, 3, 5, 7, 11, 13, 17]
 */
