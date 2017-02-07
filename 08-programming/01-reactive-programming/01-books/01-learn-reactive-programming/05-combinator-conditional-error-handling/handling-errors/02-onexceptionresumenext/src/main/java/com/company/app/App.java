package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
//  Output 1, 2, 128, 168, OnCompleted notification because, after the Exception raised on 'three',
// the defaultOnError Observable instance passed to onExceptionResumeNext() method will begin emitting,
// replacing the source Observable instance for all the Subscriber methods.
        Observable<Integer> defaultOnError = Observable
                .just(128, 168);
        Observable<Integer> numbers = Observable
                .just("1", "2", "three", "4", "5")
                .map(Integer::parseInt)
                .onExceptionResumeNext(defaultOnError);
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
