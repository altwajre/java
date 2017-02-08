package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
        Observable<Integer> observable = Observable
                .just("1", "two", "three", "4")
                .map(Integer::parseInt)
                .doOnError(s -> System.err.println("*doOnError() log: " + s));

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
}
/*
output:
1
*doOnError() log: java.lang.NumberFormatException: For input string: "two"
java.lang.NumberFormatException: For input string: "two"
 */
