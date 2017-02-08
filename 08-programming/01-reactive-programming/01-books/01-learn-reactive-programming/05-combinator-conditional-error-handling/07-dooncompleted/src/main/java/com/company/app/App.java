package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
        Observable<String> observable = Observable
                .just("Tom", "Dick", "Harry")
                .doOnCompleted(() -> System.out.println("*doOnCompleted() log COMPLETED!"));
        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
}
/*
output:
Tom
Dick
Harry
*doOnCompleted() log COMPLETED!
Completed!
 */
