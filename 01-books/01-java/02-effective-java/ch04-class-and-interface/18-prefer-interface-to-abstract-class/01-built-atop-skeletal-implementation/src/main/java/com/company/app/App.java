package com.company.app;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

public class App
{
    // Concrete implementation built atop skeletal implementation
    static List<Integer> iniArrayAsList(final int[] a){
        if(a == null){
            throw new NullPointerException();
        }
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int i) {
                return a[i];  // Autoboxing
            }
            @Override
            public Integer set(int i, Integer val){
                int oldVal = a[i];
                a[i] = val;    // Auto-unboxing
                return oldVal; // Autoboxing
            }
            @Override
            public int size() {
                return a.length;
            }
        };
    }
    public static void main( String[] args )
    {
        int[] a = new int[10];
        for(int i = 0; i < a.length; i++){
            a[i] = i;
        }
        List<Integer> list = iniArrayAsList(a);
        Collections.shuffle(list);
        System.out.println(list);
    }
}
/*
output:
[5, 4, 9, 0, 3, 2, 8, 6, 7, 1]
 */
