package com.company.app.ConceptsAndAlgorithms.RecursionAndDynamicProgramming;

import org.junit.Test;

/*
https://en.wikipedia.org/wiki/Fibonacci_number
MIT: dynamic programming I: Fibonacci, Shortest Paths
https://www.youtube.com/watch?v=OQ5jsbhAv_M
*/
public class Fibonacci {

    @Test
    public void Test() {
        int number = 9;

        System.out.println("#Recursion " + number + ": ");
        System.out.println(recursion(number));

        System.out.println("#Recursion DP " + number + ": ");
        int[] map = new int[number + 1];
        for (int i = 0; i < number + 1; i++) {
            map[i] = -1;
        }
        System.out.println(recursionDP(number, map));

        System.out.println("#Iteration " + number +  ": ");
        iteration(number);
    }

    /*
                                                   (9)r34
                                            (8)r21  +  (7)r13
                                     (7)r13  +  (6)r8
                              (6)r8  +  (5)r5
                        (5)r5  +  (4)r3
                 (4)r3  +  (3)r2
           (3)r2  +   (2)r1
     (2)r1  +  (1)r1
  output:
  34
  because
  fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
  index:     0  1  2  3  4  5  6  7   8   9
 */
    static int recursion(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return recursion(n - 1) + recursion(n - 2);
    }

    static int recursionDP(int n, int[] map) {
        if (n == 1 || n == 2) {
            return 1;
        } else if (map[n] > -1) {
            return map[n];
        } else {
            map[n] = recursionDP(n - 1, map) + recursionDP(n - 2, map);
            return map[n];
        }
    }

    /*
      output:
      fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
      index:     0  1  2  3  4  5  6  7   8   9
     */
    static void iteration(int numberOfTerms) {
        int first = 0;
        int second = 1;
        int next;
        System.out.print("fibonacci: " + first + ", " + second);
        int thirdIndex = 2;
        for (int i = thirdIndex; i <= numberOfTerms; i++) {
            next = first + second;
            System.out.print(", " + next);
            first = second;
            second = next;
        }
    }
}
/*
output:
#Recursion 9:
34
#Recursion DP 9:
34
#Iteration 9:
fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
 */
