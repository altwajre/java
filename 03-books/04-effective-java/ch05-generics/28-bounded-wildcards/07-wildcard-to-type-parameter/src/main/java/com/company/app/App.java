package com.company.app;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App
{
    public static <T extends Comparable<? super T>> T max(List<? extends T> list){
        /*
        Compile error:
        Error:(10, 38) java: incompatible types: java.util.Iterator<capture#1 of ? extends T> cannot be converted to
        java.util.Iterator<T>
         */
//        Iterator<T> i = list.iterator();

        // The elements returned by the iterator's next() method are of some subtype of T, so it works.
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while(i.hasNext()){
            T t = i.next();
            if(t.compareTo(result) > 0){
                result = t;
            }
        }
        return result;
    }
    public static void main( String[] args )
    {
        List<String> list = Arrays.asList("b", "e", "a");
        System.out.println(max(list));
    }
}
/*
output:
e
 */
