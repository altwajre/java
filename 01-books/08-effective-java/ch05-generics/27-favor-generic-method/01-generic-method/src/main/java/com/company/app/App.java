package com.company.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class RawType{
    // Uses raw types - unacceptable!
    // Compile warning: Information:java: uses unchecked or unsafe operations.
    public static Set union(Set s1, Set s2){
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }
}
public class App
{
    // Generic method
    public static <E> Set<E> union(Set<E> s1, Set<E> s2){
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }
    public static void main( String[] args )
    {
        Set<String> guys = new HashSet<String>(Arrays.asList("Tom", "Dick", "Harry"));
        Set<String> stooges = new HashSet<String>(Arrays.asList("Larry", "Moe", "Curly"));
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio);
    }
}
/*
output:
[Moe, Tom, Harry, Larry, Curly, Dick]
 */
