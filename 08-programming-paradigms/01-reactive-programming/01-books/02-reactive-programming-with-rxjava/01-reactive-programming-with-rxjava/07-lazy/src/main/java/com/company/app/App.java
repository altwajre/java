package com.company.app;

import rx.Observable;

import java.util.function.Consumer;

// Observable is lazy and will not start until subscribed to so all composition can be done before data starts flowing
// Subscription, not construction starts work:
//   Due to the laziness of an Observable, creating one does not actually cause any work to happen.
// Observables can be reused:
//   Because the Observable is lazy, it also means a particular instance can be invoked more than once.
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
