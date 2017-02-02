package com.company.app;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        List<Number> list = Arrays.asList(1, 2, 3);
        Observable<Integer> observable = Observable
                .from(list)
// cast() operator, which is a shortcut for the map(v -> someClass.cast(v)).
// The initial Observable instance here emits values of type Number, but they are actually Integer instances,
// so we can use the cast() operator to represent them as Integer instances.
                .cast(Integer.class);
        observable.subscribe(
                (v) -> System.out.println(v),
                (e) -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
}
/*
output:
1
2
3
Completed!
 */
