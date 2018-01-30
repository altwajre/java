package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
public class App
{
    final static List<String> friends = Arrays.asList("Tom", "Dick", "Harry", "Nate", "Bill", "Will", "Neal");
    public static void main( String[] args )
    {
        totalLengthOfNames();

        findALongNameByUsingOptional();

        findALongNameByUsingDefaultValue();
    }


    private static void findALongNameByUsingDefaultValue() {
        final String unknownOrLonger = friends.stream()
                .reduce("unknown", (n1, n2) -> n1.length() >= n2.length() ? n1 : n2);
        System.out.printf("No name is longer than default value '%s'", unknownOrLonger);
        System.out.println("");
    }

    // use reduce() to compare two elements against each other and pass along the result for further comparison with the rest
    // the final call is returned as the result of the reduce method call
    private static void findALongNameByUsingOptional() {
        final Optional<String> aLongName = friends.stream()
                .reduce((n1, n2) -> n1.length() >= n2.length() ? n1 : n2);
        aLongName.ifPresent( s -> System.out.printf("A longest name: %s ", s));
        System.out.println("");
    }

    private static void totalLengthOfNames() {
        System.out.println("Total number of characters in all names: " +
        friends.stream()
        .mapToInt(s -> s.length())
        .sum());
    }
}
/*
output:
Total number of characters in all names: 28
A longest name: Harry
No name is longer than default value 'unknown'
 */
