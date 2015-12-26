package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
Use case: Select/extract from an object
public interface Function<T, R>{
  R apply(T t);
}
 */
public class App
{
    public static<T, R> List<R> map(List<T> list, Function<T, R> f){
        List<R> result = new ArrayList<>();
        for(T t : list){
            result.add(f.apply(t));
        }
        return result;
    }
    public static void main( String[] args )
    {
        List<Integer> list = map(Arrays.asList("Tom", "Dick", "Harry"), (String s) -> s.length());
        System.out.println(list);

        System.out.println("#Function: (String s) -> s.length();");
        Function<String, Integer> f = (String s) -> s.length();
        System.out.println(f.apply("hello"));
    }
}
/*
output:
[3, 4, 5]
#Function: (String s) -> s.length();
5
 */
