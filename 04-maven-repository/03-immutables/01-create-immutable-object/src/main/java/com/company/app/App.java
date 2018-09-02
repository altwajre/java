package com.company.app;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        FoobarValue value = ImmutableFoobarValue.builder()
                .foo(2)
                .bar("Bar")
                .addBuz(1, 3, 4)
                .build(); // FoobarValue{foo=2, bar=Bar, buz=[1, 3, 4], crux={}}
        int foo = value.foo();
        System.out.println(foo);
        List<Integer> buz = value.buz();
        System.out.println(buz);
    }
}
/*
http://immutables.github.io/getstarted.html

output:
2
[1, 3, 4]
 */
