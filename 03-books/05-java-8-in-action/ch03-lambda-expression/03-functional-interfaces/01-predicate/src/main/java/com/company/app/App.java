package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/*
Use case: A boolean expression
public interface Predicate<T>{
  boolean test(T t);
}
 */
public class App
{
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> results = new ArrayList<>();
        for(T s : list){
            if(predicate.test(s)){
                results.add(s);
            }
        }
        return results;
    }
    public static void main( String[] args )
    {
        List<String> list = Arrays.asList("e", "a", "", "c");
        // create lambda that matches the functional interface abstract method signature
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(list, nonEmptyStringPredicate);
        System.out.println(nonEmpty);
    }
}
/*
output:
[e, a, c]
 */
