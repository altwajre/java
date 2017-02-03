package com.company.app;

import rx.Observable;
import rx.schedulers.TimeInterval;

import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
        Observable<TimeInterval<Long>> observable = Observable
                .timer(0L, 150L, TimeUnit.MILLISECONDS)
                .timeInterval();
        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
TimeInterval [intervalInMilliseconds=4, value=0]
TimeInterval [intervalInMilliseconds=150, value=1]
TimeInterval [intervalInMilliseconds=151, value=2]
TimeInterval [intervalInMilliseconds=150, value=3]
TimeInterval [intervalInMilliseconds=150, value=4]
TimeInterval [intervalInMilliseconds=149, value=5]
TimeInterval [intervalInMilliseconds=151, value=6]
 */
