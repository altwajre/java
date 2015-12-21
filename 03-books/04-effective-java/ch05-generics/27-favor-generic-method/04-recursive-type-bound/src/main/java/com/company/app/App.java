package com.company.app;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
Recursive type bound is <T extends Comparable<T>>
Note that the type parameter T is also part of the signature of the super interface Comparable<T>
It helps achieving mutual comparability: it ensures that you can only compare object of type T. Without the type bound,
Comparable compares any two Object s. With the type bound, the compiler can ensure that only two objects of type T
are compared.
 */
public class App
{
    // Using a recursive type bound to express mutual comparability
    public static <T extends Comparable<T>> T max(List<T> list){
        Iterator<T> i = list.iterator();
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
        List<String> list = Arrays.asList("a", "e", "b");
        System.out.println(max(list));
    }
}
/*
output:
e
 */
