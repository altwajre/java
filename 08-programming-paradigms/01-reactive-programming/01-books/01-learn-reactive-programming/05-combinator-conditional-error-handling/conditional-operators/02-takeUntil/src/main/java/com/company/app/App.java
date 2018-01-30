package com.company.app;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
        Observable<String> words = Observable
                .just("one", "way", "or", "another", "I'll", "learn", "RxJava")
                .zipWith(
                        Observable.interval(200L, TimeUnit.MILLISECONDS),
                        (x, y) -> x
                );
        Observable<Long> interval = Observable
                .interval(500L, TimeUnit.MILLISECONDS);

        Observable<String> observable = words.takeUntil(interval);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
