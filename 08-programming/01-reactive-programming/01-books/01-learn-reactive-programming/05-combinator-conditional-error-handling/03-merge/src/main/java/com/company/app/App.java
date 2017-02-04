package com.company.app;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
        Observable<String> greetings = Observable
                .just("Hello", "Hi", "Howdy", "Yo")
                .zipWith(
                        Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (value, i) -> value
                );

        Observable<String> names = Observable
                .just("Tom", "Dick")
                .zipWith(
                        Observable.interval(150L, TimeUnit.MILLISECONDS),
                        (value, i) -> value
                );

        Observable<String> punctuation = Observable
                .just(".", "?", "!")
                .zipWith(
                        Observable.interval(110L, TimeUnit.MILLISECONDS),
                        (value, i) -> value
                );

        Observable<String> combined = Observable
                .merge(greetings, names, punctuation);

        combined.subscribe(
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
Hello
.
Tom
Hi
?
Dick
Howdy
!
Yo
Completed!
 */
