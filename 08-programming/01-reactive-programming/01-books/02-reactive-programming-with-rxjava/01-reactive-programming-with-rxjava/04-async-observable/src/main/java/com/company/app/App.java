package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        Observable.<Integer>create(s -> {
            new Thread(() -> s.onNext(18), "Thread: " + Thread.currentThread().getName()).start();
        })
                .doOnNext(i -> System.out.println("doOnNext: "+Thread.currentThread().getName()))
                .filter(i -> i % 2 == 0)
                .map(i -> "Value " + i + " processed on " + Thread.currentThread().getName())
                .subscribe(s -> System.out.println("Some value => " + s));

        System.out.println("Waiting...");
        Thread.sleep(1000);
        System.out.println("END");
    }
}
/*
output:
Waiting...
doOnNext: Thread: main
Some value => Value 18 processed on Thread: main
END
 */
