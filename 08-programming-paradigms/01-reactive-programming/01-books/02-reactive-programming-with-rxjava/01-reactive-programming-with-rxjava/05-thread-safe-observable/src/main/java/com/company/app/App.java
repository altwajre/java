package com.company.app;

import rx.Observable;

// The contract is that events onNext(), onCompleted(), onError() can never be emitted concurrently.
// A single Observable stream must always be serialized and thread-safe.
// Each event can be emitted from a different thread, as long as the emissions are not concurrent.
// This means no interleaving or simultaneous execution of onNext().
public class App {
    public static void main(String[] args) throws Exception {

        Observable<Object> observable = Observable.create(s -> {
            new Thread(() -> {
                String threadName = "Pub - " + Thread.currentThread().getName();
                s.onNext(threadName + ": one");
                s.onNext(threadName + ": two");
                s.onNext(threadName + ": three");
                s.onNext(threadName + ": four");
                s.onCompleted();
            }).start();
        });

        observable.subscribe(v -> System.out.println("Sub - " + Thread.currentThread().getName() + ": " + v));

        System.out.println(Thread.currentThread().getName() + ": waiting");

        Thread.sleep(500);

        System.out.println(Thread.currentThread().getName() + ": END");

    }
}
/*
output:
main: waiting
Sub - Thread-0: Pub - Thread-0: one
Sub - Thread-0: Pub - Thread-0: two
Sub - Thread-0: Pub - Thread-0: three
Sub - Thread-0: Pub - Thread-0: four
main: END
 */
