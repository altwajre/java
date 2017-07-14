package com.company.app;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/*
IntPredicate is specialized version of functional interface that avoids autoboxing
 */
public class App
{
    public static void main( String[] args )
    {
        IntPredicate noboxingNumber = (int i) -> i % 2 == 0;
        System.out.println("IntPredicate: "+noboxingNumber.test(1000));

        Predicate<Integer> boxingNumber = (Integer i) -> i % 2 == 1;
        System.out.println("Predicate: "+boxingNumber.test(1000));
    }
}
/*
output:
IntPredicate: true
Predicate: false
 */