package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        rotate1((List<Integer>) numbers.clone());
        rotate2((List<Integer>) numbers.clone());
        rotate3((List<Integer>) numbers.clone());

        System.out.println("\n   End: " + Arrays.toString(numbers.toArray()));
    }

    private static void rotate1(List<Integer> numbers) {
        System.out.println("rotate1");
        System.out.println("Before: " + Arrays.toString(numbers.toArray()));
        Collections.rotate(numbers, 1);
        System.out.println(" After: " + Arrays.toString(numbers.toArray()));
    }

    private static void rotate2(List<Integer> numbers) {
        System.out.println("rotate2");
        System.out.println("Before: " + Arrays.toString(numbers.toArray()));
        Collections.rotate(numbers, 2);
        System.out.println(" After: " + Arrays.toString(numbers.toArray()));
    }

    private static void rotate3(List<Integer> numbers) {
        System.out.println("rotate3");
        System.out.println("Before: " + Arrays.toString(numbers.toArray()));
        Collections.rotate(numbers, 3);
        System.out.println(" After: " + Arrays.toString(numbers.toArray()));
    }
}
/*
output:
rotate1
Before: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 After: [9, 0, 1, 2, 3, 4, 5, 6, 7, 8]
rotate2
Before: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 After: [8, 9, 0, 1, 2, 3, 4, 5, 6, 7]
rotate3
Before: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 After: [7, 8, 9, 0, 1, 2, 3, 4, 5, 6]

   End: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 */
