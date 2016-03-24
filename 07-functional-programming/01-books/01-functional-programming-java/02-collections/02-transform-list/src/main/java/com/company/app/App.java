package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    final static List<String> names = Arrays.asList("Tom", "Dick", "Harry");
    static void imperative(List<String> names){
        final List<String> uppercaseNames = new ArrayList<String>();
        for(String name : names){
            uppercaseNames.add(name.toLowerCase());
        }
        for(String name : uppercaseNames){
            System.out.print(name + " ");
        }
        System.out.println("");
    }
    /*
    Functional
    1, run the block in the context of each element in the collection
    2, map method collects the result of running the lambda expression and returns the result collection
    3, print elements in results using the forEach method
     */
    static void functionalLambda(List<String> names){
        System.out.print("transform to uppercase: ");
        names.stream()
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.print(s + " "));
        System.out.println("");

        System.out.print("returns different type: ");
        names.stream()
                .map(s -> s.length())
                .forEach(c -> System.out.print(c + " "));
        System.out.println("");
    }
    static void functionalMethodRef(List<String> names){
        System.out.println("method references: ");
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("");
    }
    public static void main( String[] args )
    {
        System.out.println("#imperative");
        imperative(names);
        System.out.println("#functionalLambda");
        functionalLambda(names);
        functionalMethodRef(names);
    }
}
/*
output:
#imperative
tom dick harry
#functionalLambda
transform to uppercase: TOM DICK HARRY
returns different type: 3 4 5
method references:
TOM
DICK
HARRY

 */
