package com.company.app;

import rx.Observable;
import rx.observables.GroupedObservable;

import java.util.concurrent.atomic.AtomicInteger;

public class App
{
    public static void main( String[] args )
    {
        final AtomicInteger batch = new AtomicInteger(0);
//        Observable.range(1, 5)
        Observable<GroupedObservable<Integer, Integer>> groupedObservableObservable = Observable.just(1, 2, 3, 4, 5)
//                .groupBy(i -> i % 2 == 0)
                .groupBy(i -> batch.getAndIncrement() % 2);
        groupedObservableObservable
                .subscribe(grouped -> grouped.toList().subscribe(v -> {
                    System.out.println(Thread.currentThread().getName() + ": receive " + v);
                }));
//                .subscribe(v -> System.out.println(v));
//                .subscribe(v -> System.out.println(Thread.currentThread().getName() + ": receive " + v));

        sleep(2000);

    }

    static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
main: receive [1, 3, 5]
main: receive [2, 4]
 */
