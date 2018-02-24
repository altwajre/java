package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
        Observable<Integer> defaultOnError = Observable
                .just(128, 168);
        Observable<Integer> numbers = Observable
                .just("1", "2", "three", "4", "5")
                .doOnNext(number -> {
                    assert !number.equals("three");
                })
                .map(Integer::parseInt)
                .onErrorResumeNext(defaultOnError);
        numbers.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
}
/*
output:
1
2
128
168
Completed!
 */
