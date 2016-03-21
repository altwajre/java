package com.company.app;

import java.util.HashMap;
import java.util.Map;

public class App
{
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
    static int naiveRecursive(int n){
        int f = 0;
        if (n <= 2) f = 1;
        else {
            f = naiveRecursive(n -1) + naiveRecursive(n -2);
        }
        return f;
    }

    /*
      output:
      fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
      index:     0  1  2  3  4  5  6  7   8   9
     */
    static int iteration(int numberOfTerms){
        int first = 0;
        int second = 1;
        int next = 0;
//        System.out.print("fibonacci: " + first + ", " + second);
        int thirdIndex = 2;
        for(int i = thirdIndex; i <= numberOfTerms; i++){
            next = first + second;
//            System.out.print(", " + next);
            first = second;
            second = next;
        }
        return next;
    }

    // https://www.youtube.com/watch?v=OQ5jsbhAv_M&list=PLfMspJ0TLR5HRFu2kLh3U4mvStMO8QURm
    static int memoizedDpRecursive(int n, Map<Integer, Integer> memo){
        int f = 0;
        if(memo.containsKey(n)) return memo.get(n);
        if (n <= 2) f = 1;
        else {
            f = memoizedDpRecursive(n - 1, memo) + memoizedDpRecursive(n -2, memo);
        }
        memo.put(n, f);
        return f;
    }

    public static void main( String[] args )
    {
        System.out.println("Iteration");
        System.out.println(iteration(9));

        System.out.println("NaiveRecursive");
        System.out.println(naiveRecursive(9));

        System.out.println("MemoizedDpRecursive");
        System.out.println(memoizedDpRecursive(9, new HashMap<Integer, Integer>()));
    }
    /*
    output:
    Iteration
    34
    NaiveRecursive
    34
    MemoizedDpRecursive
    34
     */
}
