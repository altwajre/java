package com.company.app;

import rx.Observable;
import rx.schedulers.Schedulers;

// http://tomstechnicalblog.blogspot.com/2016/02/rxjava-understanding-observeon-and.html

public class App {
    public static void main(String[] args) {

        subscribeOn_test();

        observeOn_test();

    }

// It is often helpful in the middle of an Observable chain to switch to another Scheduler.
// For example, you may press a button on a UI and it kicks off work on a computation thread,
// which frees up the UI thread so the UI does not freeze. But when the computation is done,
// it needs to be displayed back on the UI.

// Effectively, you can only use one subscribeOn(), but you can have any number of observeOn() operators.
// You can switch emissions from one thread to another with ease using observeOn().
// But do not use it everywhere for the sake of.
// Only use it when you find a calculation is intense enough that it needs to be offloaded to another thread.

// observeOn() operator allowing a Scheduler (thread_1) to start the next emission without
// waiting for the current emission to reach the Subscriber.
    private static void observeOn_test() {
        System.out.println("#observeOn_test");
        Observable<String> source = Observable.just("Tom", "Dick", "Harry");

        source
                .subscribeOn(Schedulers.computation())
                .map(String::length)
                .doOnNext(i -> System.out.println("Emitting " + i + " on thread " + Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .map(i -> i * 2)
                .subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));
        sleep(1000);
    }
/*
output:
#observeOn_test
Emitting 3 on thread RxComputationScheduler-3
Emitting 4 on thread RxComputationScheduler-3
Emitting 5 on thread RxComputationScheduler-3
Received 6 on thread RxComputationScheduler-2
Received 8 on thread RxComputationScheduler-2
Received 10 on thread RxComputationScheduler-2
 */

// Use subscribeOn(Scheduler) to kick off tasks in a worker thread
// Observable.interval() already specifies a subscribeOn() internally that emits on the computation scheduler

// Choosing a Scheduler
// There are several other Schedulers such as Schedulers.io(), which is optional for IO-related tasks (and it caches and re-uses threads to increase efficiency).
// Then there is Schedulers.newThread() which simply creates a new thread for each subscription.
// You have to be careful with both of these because in theory they could create an unlimited number of threads (this can cause bad performance).
// For computational tasks, you should use Schedulers.computation() so the number of threads are limited based on the number of cores your machine has.
    private static void subscribeOn_test() {
        System.out.println("#subscribeOn_test");
        Observable<String> source = Observable.just("Tom", "Dick", "Harry");

        Observable<Integer> lengthObservable = source
                .subscribeOn(Schedulers.computation())
                .map(String::length);

        lengthObservable
                .subscribe(s -> System.out.println(Thread.currentThread().getName() + ": " + s));
        sleep(1000);
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
#subscribeOn_test
RxComputationScheduler-1: 3
RxComputationScheduler-1: 4
RxComputationScheduler-1: 5
 */
