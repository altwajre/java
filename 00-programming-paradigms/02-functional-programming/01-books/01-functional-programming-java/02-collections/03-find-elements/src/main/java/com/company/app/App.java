package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App
{
    final static List<String> names = Arrays.asList("Tom", "Lee", "Harry", "Nate", "Will", "Neal");
    static void imperative(List<String> names) {
        final List<String> startsWithN = new ArrayList<String>();
        for(String name : names){
            if(name.startsWith("N")){
                startsWithN.add(name);
            }
        }
        System.out.printf("Found %d names", startsWithN.size());
    }
    static void functional(List<String> names){
        final List<String> startsWithN = names.stream()
                .filter(s -> s.startsWith("N"))
                .collect(Collectors.toList());
        System.out.printf("Found %d names", startsWithN.size());
    }
    public static void main( String[] args )
    {
        System.out.println("#imperative");
        imperative(names);
        System.out.println("\n#functional");
        functional(names);

    }
}
