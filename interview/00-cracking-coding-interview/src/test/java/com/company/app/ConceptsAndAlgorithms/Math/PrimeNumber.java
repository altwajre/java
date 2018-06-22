package com.company.app.ConceptsAndAlgorithms.Math;

import org.junit.Test;

/*
https://en.wikipedia.org/wiki/Prime_number
It is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 */
public class PrimeNumber {

    @Test
    public void Test() {
        System.out.println(isPrime(25));  // false
        System.out.println(isPrime(13));  // true
    }

    static boolean isPrime(int number) {
        if (number == 2) return true;
        if (number < 2) return false;

        if (number % 2 == 0) return false;

        for (int i = 3; i < number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
/*
output:
false
true
 */
