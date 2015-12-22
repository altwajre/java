package com.company.app;

import java.util.HashSet;
import java.util.Set;

// Compile error when return type without explicit type parameter
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

        // Missing explicit type parameter
        Set<Number> numbers = union(integers, doubles);
    }
}
/*
Error:(26, 36) java: incompatible types: java.util.Set<java.lang.Number&java.lang.Comparable<? extends
java.lang.Number&java.lang.Comparable<?>>> cannot be converted to java.util.Set<java.lang.Number>
 */
