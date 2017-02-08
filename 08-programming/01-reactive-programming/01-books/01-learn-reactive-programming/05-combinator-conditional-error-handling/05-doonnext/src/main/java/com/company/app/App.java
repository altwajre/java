package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
        Observable<String> observable = Observable
                .just("Tom", "Dick", "Harry")
                .doOnNext(s -> System.out.println("*doOnNext() log: " + s));
        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
}
/*
output:
*doOnNext() log: Tom
Tom
*doOnNext() log: Dick
Dick
*doOnNext() log: Harry
Harry
Completed!
 */
