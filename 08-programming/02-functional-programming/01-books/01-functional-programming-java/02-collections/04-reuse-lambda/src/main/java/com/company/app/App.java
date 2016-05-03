package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// assign lambda expression to a variable and reuse it, just like with object
public class App
{
    final static List<String> friends = Arrays.asList("Tom", "Dick", "Harry", "Nate", "Will", "Neal");
    final static List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");
    final static List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
    public static void main( String[] args )
    {
        final Predicate<String> startsWithN = s -> s.startsWith("N");

        final long countFriendsStartN = friends.stream()
                .filter(startsWithN)
                .count();
        System.out.println("countFriendsStartN: " + countFriendsStartN);

        final long countEditorsStartN = editors.stream()
                .filter(startsWithN)
                .count();
        System.out.println("countEditorsStartN: " + countEditorsStartN);

        final long countComradesStartN = comrades.stream()
                .filter(startsWithN)
                .count();
        System.out.println("countComradesStartN: " + countComradesStartN);
    }
}
/*
output:
countFriendsStartN: 2
countEditorsStartN: 0
countComradesStartN: 1
 */