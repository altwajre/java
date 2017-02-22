package com.company.app;

import rx.Observable;

// how do you take advantage of concurrency and/or parallelism with RxJava? Composition.
public class App
{
    public static void main( String[] args )
    {
        Observable<String> a = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("one");
                s.onNext("two");
                s.onCompleted();
            }).start();
        });

        Observable<String> b = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("three");
                s.onNext("four");
                s.onCompleted();
            }).start();
        });

        Observable<String> merge = Observable.merge(a, b);

        merge.subscribe(v -> System.out.println(v));
    }
}
/*
output:
one
two
three
four
 */
