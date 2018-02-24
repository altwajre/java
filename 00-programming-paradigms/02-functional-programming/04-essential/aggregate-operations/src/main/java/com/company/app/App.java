package com.company.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
public class App
{
    public static void main( String[] args )
    {
        arraySum();

        mapSum();
    }

    private static void mapSum() {
        System.out.println("#mapSum");
        Map<String, Integer> taxes = new HashMap<>();
        taxes.put("wa", 10);
        taxes.put("ca", 11);
        taxes.put("az", 12);

        int sum = taxes.values().stream().mapToInt(i -> i).sum();
        System.out.println(sum);
    }

    private static void arraySum() {
        System.out.println("#arraySum");
        List<String> friends = Arrays.asList("Tom", "Dick", "Harry", "Nate", "Bill", "Will", "Neal");
        int sum = friends.stream()
            .mapToInt(s -> s.length())
            .sum();
        System.out.println(sum);
    }
}
