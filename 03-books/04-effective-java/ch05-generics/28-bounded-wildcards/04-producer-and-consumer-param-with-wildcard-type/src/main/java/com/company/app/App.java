package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Generic Function interface
interface Function<T>{
    T apply(T arg1, T args);
}
// List-based generic reduction with wildcard type
public class App
{
    // Although lists can both consume and produce values
    // 1, Producer: the reduce() method uses its list parameter only as an producer, so its declaration should use a
    // wildcard type that extends E.
    // 2, Producer and Consumer: the parameter f represents a function that both consumes and produces E instances, so a
    // wildcard type would be inappropriate for it.
    static <E> E reduce(List<? extends E> list, Function<E> f, E initVal) {
        List<E> snapshot;
        synchronized (list) {
            snapshot = new ArrayList<E>(list);
        }
        E result = initVal;
        for (E e : snapshot)
            result = f.apply(result, e);
        return result;
    }

    private static final Function<Number> MAX = new Function<Number>() {
        public Number apply(Number n1, Number n2) {
            return Double.compare(n1.doubleValue(), n2.doubleValue()) > 0 ? n1
                    : n2;
        }
    };
    // Wildcard type for parameter that serves as an E producer
    public static void main( String[] args )
    {
        // We can use a Number function to reduce a list of Integer or Double
        List<Integer> intList = Arrays.asList(2, 7, 1, 8, 2, 8, 1, 8, 2, 8);
        System.out.println(reduce(intList, MAX, Integer.MIN_VALUE));

        List<Double> doubleList = Arrays.asList(2.718281828, 3.141592654,
                1.61803399);
        System.out.println(reduce(doubleList, MAX, Double.NEGATIVE_INFINITY));
    }
}
/*
output:
8
3.141592654
 */
