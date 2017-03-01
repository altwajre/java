package com.company.app;

import rx.Observable;
import rx.schedulers.Schedulers;

// http://tomstechnicalblog.blogspot.com/2016/02/rxjava-understanding-observeon-and.html
// Use subscribeOn(Scheduler) to kick off tasks in worker thread
// Observable.interval() already specifies a subscribeOn() internally that emits on the computation scheduler
public class App {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Tom", "Dick", "Harry");

        Observable<Integer> lengthObservable = source
                .subscribeOn(Schedulers.computation())
                .map(String::length);

        lengthObservable
                .subscribe(s -> System.out.println(Thread.currentThread().getName() + ": " + s));

        sleep(3000);
    }

    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
RxComputationScheduler-1: 3
RxComputationScheduler-1: 4
RxComputationScheduler-1: 5
 */
