package com.company.app;

import rx.Observable;

// Composition takes advantage of concurrency and/or parallelism with RxJava
public class App
{
    public static void main( String[] args )
    {
        Observable<String> a = Observable.create(s -> {
            new Thread(() -> {
                String threadName = "Pub - " + Thread.currentThread().getName();

                s.onNext(threadName + ": one");
                s.onNext(threadName + ": two");
                s.onCompleted();
            }).start();
        });

        Observable<String> b = Observable.create(s -> {
            new Thread(() -> {
                String threadName = "Pub - " + Thread.currentThread().getName();
                s.onNext(threadName+ ": three");
                s.onNext(threadName +": four");
                s.onCompleted();
            }).start();
        });

        Observable<String> merge = Observable.merge(a, b);

        merge.subscribe(v -> System.out.println("Sub - " + Thread.currentThread().getName() + ": " + v));
    }
}
/*
output:
Sub - Thread-0: Pub - Thread-0: one
Sub - Thread-0: Pub - Thread-0: two
Sub - Thread-1: Pub - Thread-1: three
Sub - Thread-1: Pub - Thread-1: four
 */
