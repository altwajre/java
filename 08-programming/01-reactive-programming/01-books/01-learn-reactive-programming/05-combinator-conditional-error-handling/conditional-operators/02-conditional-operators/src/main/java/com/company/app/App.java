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

        test_takeuntil(words, interval);

        test_takewhile(words);

        test_skipuntil(words, interval);

        test_defaultifempty();

    }

    private static void test_defaultifempty() {
        System.out.println("#test_defaultifempty");
        Observable<Object> test = Observable
                .empty()
                .defaultIfEmpty(5);

        test.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_defaultifempty
5
Completed!
 */

    private static void test_skipuntil(Observable<String> words, Observable<Long> interval) {
        System.out.println("#test_skipuntil");
        Observable<String> observable = words.skipUntil(interval);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_skipuntil
or
another
I'll
learn
RxJava
Completed!
 */

    private static void test_takewhile(Observable<String> words) {
        System.out.println("#test_takewhile");
        Observable<String> observable = words.takeWhile(word -> word.length() > 2);

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
#test_takewhile
one
way
Completed!
 */

    private static void test_takeuntil(Observable<String> words, Observable<Long> interval) {
        System.out.println("#test_takeuntil");
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
/*
#test_takeuntil
one
way
Completed!
 */
}
