package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class App
{
    final static List<String> friends = Arrays.asList("Tom", "Dick", "Harry", "Nate", "Bill", "Will", "Neal");
    private static void lambdaDup() {
        final Predicate<String> startsWithN = s -> s.startsWith("N");
        final Predicate<String> startsWithB = s -> s.startsWith("B");

        final long countFriendsStartN = friends.stream()
                .filter(startsWithN)
                .count();
        final long countFirendsStartB = friends.stream()
                .filter(startsWithB)
                .count();
        System.out.println("countFriendsStartN: " + countFriendsStartN);
        System.out.println("countFirendsStartB: " + countFirendsStartB);
    }
    public static Predicate<String> checkIfStartsWith(final String letter){
        return s -> s.startsWith(letter);
    }
    private static void returnFunction() {
        final long countFriendsStartN = friends.stream()
                .filter(checkIfStartsWith("N")).count();
        final long countFirendsStartB = friends.stream()
                .filter(checkIfStartsWith("B")).count();
        System.out.println("countFriendsStartN: " + countFriendsStartN);
        System.out.println("countFirendsStartB: " + countFirendsStartB);
    }
    private static void functionVariable() {
//        final Function<String, Predicate<String>> startsWithLetter = (String letter) -> {
//            return s -> s.startsWith(letter);
//        };
        final Function<String, Predicate<String>> startsWithLetter = letter -> s -> s.startsWith(letter);
        final long countFriendsStartN = friends.stream()
                .filter(startsWithLetter.apply("N")).count();
        final long countFirendsStartB = friends.stream()
                .filter(startsWithLetter.apply("B")).count();
        System.out.println("countFriendsStartN: " + countFriendsStartN);
        System.out.println("countFirendsStartB: " + countFirendsStartB);
    }
    public static void main( String[] args )
    {
        System.out.println("#lambdaDup");
        lambdaDup();
        System.out.println("#returnFunction");
        returnFunction();
        System.out.println("#functionVariable");
        functionVariable();
    }
}
/*
output:
#lambdaDup
countFriendsStartN: 2
countFirendsStartB: 1
#returnFunction
countFriendsStartN: 2
countFirendsStartB: 1
#functionVariable
countFriendsStartN: 2
countFirendsStartB: 1
 */
