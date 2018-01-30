package com.company.app;

import rx.Observable;

import java.util.concurrent.TimeUnit;

// The sample operator periodically "dips" into the sequence and emits only the most recently emitted item during each dip
public class App
{
    public static void main( String[] args )
    {
        Observable<Integer> bursty = Observable.range(1, 10000);
        bursty
                .sample(100, TimeUnit.MILLISECONDS)
                .subscribe(s -> System.out.println(s));
    }
}
/*
output:
10000
 */
