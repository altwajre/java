package com.company.app;

import rx.Observable;

import java.util.function.Consumer;

public class App
{
    public static void main( String[] args )
    {
        String arg = "Tom";
        Observable<String> observable = Observable.create(s -> {
            getDataFromServerWithCallback(arg, data -> { // here getDataFromServerWithCallback() is not yet executed.
                s.onNext(data);
                s.onCompleted();
            });
        });

        observable.subscribe(s -> System.out.println("Subscriber 1: " + s)); // execute getDataFromServerWithCallback() above
        observable.subscribe(s -> System.out.println("Subscriber 2: " + s)); // execute getDataFromServerWithCallback() above

        Observable<String> lazyFallback = Observable.just("Fallback");

        observable
                .doOnNext(s -> {
                    throw new RuntimeException(); // simulate error
                })
                .onErrorResumeNext(lazyFallback)
                .subscribe(s -> System.out.println(s));
    }

    static void getDataFromServerWithCallback(String arg, Consumer<String> consumer){
        double random = Math.random();
        consumer.accept(arg + " - Random=" + random);
    }
}
/*
output:
Subscriber 1: Tom - Random=0.8085336409402086
Subscriber 2: Tom - Random=0.5720160247854644
Fallback
 */
