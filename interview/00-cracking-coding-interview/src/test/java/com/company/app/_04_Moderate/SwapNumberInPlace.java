package com.company.app._04_Moderate;

import org.junit.Test;

public class SwapNumberInPlace {

    @Test
    public void Test() {
        swap(9, 4);
    }

    static void swap(int a, int b) {
        // a = 9, b = 4
        a = a - b; // a = 9 - 4 = 5
        b = a + b; // b = 5 + 5 = 9
        a = b - a; // a = 9 - 5 = 4

        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
}
/*
output:
a: 4
b: 9
 */
