package com.company.app.ConceptsAndAlgorithms.Math;

import org.junit.Test;

public class SquareRoot {

    @Test
    public void Test() {
        System.out.println(sqrt(25));  // 5
    }

    static int sqrt(int n){
        int start = 0;
        int end = n;
        while(start < end){
            int mid = (start + end) / 2;
            int multiply = mid * mid;
            if(multiply == n) return mid;
            else if (multiply > n) end = mid;
            else start = mid;
        }
        return -1; // not found
    }
}
/*
output:
5
 */
