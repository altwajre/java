package com.company.app;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
        Observable<String> greetings = Observable
                .just("Hello", "Hi")
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

        test_concat(greetings, names, punctuation);

        test_startwith(greetings, names, punctuation);
    }

    private static void test_startwith(Observable<String> greetings, Observable<String> names, Observable<String> punctuation) {
        System.out.println("#test_startwith");
        Observable<String> observable = punctuation
                .startWith(names)
                .startWith(greetings);

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
/*
output:
#test_startwith
Hello
Hi
Tom
Dick
.
?
!
Completed!
 */

    private static void test_concat(Observable<String> greetings, Observable<String> names, Observable<String> punctuation) {
        System.out.println("#test_concat");
        Observable<String> concat = Observable
                .concat(greetings, names, punctuation);

        concat.subscribe(
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
/*
output:
#test_concat
Hello
Hi
Tom
Dick
.
?
!
Completed!
 */
}
