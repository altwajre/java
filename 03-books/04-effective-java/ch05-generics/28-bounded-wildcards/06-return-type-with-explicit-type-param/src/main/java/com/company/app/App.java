package com.company.app;

import java.util.HashSet;
import java.util.Set;

// Explicit type parameter
// Generic union method with wildcard types
public class App
{
    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2){
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }
    // Simple program to execute flexible generic method
    public static void main( String[] args )
    {
        Set<Integer> integers = new HashSet<Integer>();
        integers.add(1);
        integers.add(3);
        integers.add(5);

        Set<Double> doubles = new HashSet<Double>();
        doubles.add(2.0);
        doubles.add(4.0);
        doubles.add(6.0);

        // Need explicit type parameter: App.<Number> to avoid the compile error
        Set<Number> numbers = App.<Number> union(integers, doubles);
        System.out.println(numbers);
    }
}
/*
output:
[2.0, 4.0, 1, 3, 5, 6.0]
 */
