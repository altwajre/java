package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Compiler error
// Generic Function interface
interface Function<T>{
    T apply(T arg1, T args);
}
// List-based generic reduction with wildcard type
public class App
{
    // Although lists can both consume and produce values
    // 1, list not wildcard: suppose you have a List<Integer>, and you want to reduce it with a Function<Number>. It won't
    // compile
    // 2, Producer and Consumer: the parameter f represents a function that both consumes and produces E instances, so a
    // wildcard type would be inappropriate for it.
    static <E> E reduce(List<E> list, Function<E> f, E initVal) {
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

//        List<Double> doubleList = Arrays.asList(2.718281828, 3.141592654,
//                1.61803399);
//        System.out.println(reduce(doubleList, MAX, Double.NEGATIVE_INFINITY));
    }
}
/*
Compile error:
Error:(41, 28) java: method reduce in class com.company.app.App cannot be applied to given types;
  required: java.util.List<E>,com.company.app.Function<E>,E
  found: java.util.List<java.lang.Integer>,com.company.app.Function<java.lang.Number>,int
  reason: inferred type does not conform to equality constraint(s)
    inferred: java.lang.Number
    equality constraints(s): java.lang.Number,java.lang.Integer
 */
