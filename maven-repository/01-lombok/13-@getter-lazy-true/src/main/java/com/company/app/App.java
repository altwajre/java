package com.company.app;

import lombok.Getter;

class GetterLazyExample{
    @Getter(lazy = true)
    private final double[] cached = expensive();
    private double[] expensive(){
        double[] result = new double[1000000];
        for(int i = 0; i < result.length; i++){
            result[i] = Math.asin(i);
        }
        return result;
    }
}
public class App {
    public static void main(String... args){
        GetterLazyExample getterLazyExample = new GetterLazyExample();
        System.out.println(getterLazyExample.getCached());
    }
}
/*
https://projectlombok.org/features/GetterLazy.html
You can let lombok generate a getter which will calculate a value once, the first time this getter is called, and cache
it from then on. This can be useful if calculating the value takes a lot of CPU, or the value takes a lot of memory.
 */