package com.company.app;

import java.util.Comparator;

public class App
{
    public static void main( String[] args )
    {
        Comparator<Integer> naturalOrder = new Comparator<Integer>() {
            public int compare(Integer first, Integer second) {
                int f = first; // Auto-unboxing
                int s = second; // Auto-unboxing
                return f < s ? -1 : (f == s ? 0 : 1);  // No unboxing
            }
        };

        int result = naturalOrder.compare(new Integer(42), new Integer(42));
        System.out.println(result);


    }
    static Integer i;
}
/*
output:
0

note:
Fixed using == operator to compare two references by unboxing Integer to int first
 */