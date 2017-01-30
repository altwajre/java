package com.company.app;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class App {
    public static <T> Subscription subscribePrint(Observable<T> observable,
                                                  String name) {
        return observable.subscribe(
                (v) -> System.out.println(Thread.currentThread().getName()
                        + "|" + name + " : " + v),
                (e) -> {
                    System.err.println("Error from " + name + ":");
                    System.err.println(e);
                    System.err.println(Arrays
                            .stream(e.getStackTrace())
                            .limit(5L)
                            .map(stackEl -> "  " + stackEl)
                            .collect(Collectors.joining("\n"))
                    );
                },
                () -> System.out.println(name + " ended!"));
    }

    public static void main(String[] args) {
        test_subscribe_interval_publish();

        test_publish();

        test_replay();

        test_refcount();

    }

    private static void test_subscribe_interval_publish() {
        Observable<Long> intervalPub = Observable.interval(100L, TimeUnit.MILLISECONDS);
        Subscription sub1 = subscribePrint(intervalPub, "First");
        Subscription sub2 = subscribePrint(intervalPub, "Second");

        Subscription sub3 = null;

        try {
            Thread.sleep(300L);
            sub3 = subscribePrint(intervalPub, "Third");
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sub1.unsubscribe();
        sub2.unsubscribe();
        sub3.unsubscribe();
    }
/*
output:
RxComputationScheduler-1|First : 0
RxComputationScheduler-2|Second : 0
RxComputationScheduler-1|First : 1
RxComputationScheduler-2|Second : 1
RxComputationScheduler-1|First : 2
RxComputationScheduler-2|Second : 2
RxComputationScheduler-1|First : 3
RxComputationScheduler-2|Second : 3
RxComputationScheduler-3|Third : 0
RxComputationScheduler-1|First : 4
RxComputationScheduler-2|Second : 4
RxComputationScheduler-3|Third : 1
RxComputationScheduler-1|First : 5
RxComputationScheduler-2|Second : 5
RxComputationScheduler-3|Third : 2
 */

    private static void test_refcount() {
        System.out.println("#test_refcount");
        Observable<Long> interval = Observable.interval(100L, TimeUnit.MILLISECONDS);
        Observable<Long> refCount = interval.publish().refCount();
        Subscription sub1 = subscribePrint(refCount, "First");
        Subscription sub2 = subscribePrint(refCount, "Second");

        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sub1.unsubscribe();
        sub2.unsubscribe();

        Subscription sub3 = subscribePrint(refCount, "Third");

        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sub3.unsubscribe();
    }
/*
output:
#test_refcount
RxComputationScheduler-1|First : 0
RxComputationScheduler-1|Second : 0
RxComputationScheduler-1|First : 1
RxComputationScheduler-1|Second : 1
RxComputationScheduler-1|First : 2
RxComputationScheduler-1|Second : 2
RxComputationScheduler-2|Third : 0
RxComputationScheduler-2|Third : 1
 */

    // receive all the notifications have been emitted before our subscription and then to continue receiving incoming ones.
    private static void test_replay() {
        System.out.println("#test_replay");
        Observable<Long> interval = Observable.interval(100L, TimeUnit.MILLISECONDS);
        ConnectableObservable<Long> published = interval.replay();
        Subscription sub1 = subscribePrint(published, "First");
        Subscription sub2 = subscribePrint(published, "Second");

        published.connect();

        Subscription sub3 = null;

        try {
            Thread.sleep(300L);
            sub3 = subscribePrint(published, "Third");
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sub1.unsubscribe();
        sub2.unsubscribe();
        sub3.unsubscribe();
    }
/*
output:
#test_replay
RxComputationScheduler-1|First : 0
RxComputationScheduler-1|Second : 0
RxComputationScheduler-1|First : 1
RxComputationScheduler-1|Second : 1
RxComputationScheduler-1|First : 2
RxComputationScheduler-1|Second : 2
main|Third : 0
main|Third : 1
main|Third : 2
RxComputationScheduler-1|Third : 3
RxComputationScheduler-1|First : 3
RxComputationScheduler-1|Second : 3
RxComputationScheduler-1|Third : 4
RxComputationScheduler-1|First : 4
RxComputationScheduler-1|Second : 4
RxComputationScheduler-1|Third : 5
RxComputationScheduler-1|First : 5
RxComputationScheduler-1|Second : 5
 */

    private static void test_publish() {
        System.out.println("#test_publish");
        Observable<Long> interval = Observable.interval(100L, TimeUnit.MILLISECONDS);
        ConnectableObservable<Long> published = interval.publish();
        Subscription sub1 = subscribePrint(published, "First");
        Subscription sub2 = subscribePrint(published, "Second");

        published.connect();

        Subscription sub3 = null;

        try {
            Thread.sleep(300L);
            sub3 = subscribePrint(published, "Third");
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sub1.unsubscribe();
        sub2.unsubscribe();
        sub3.unsubscribe();
    }
}
/*
output:
#test_publish
RxComputationScheduler-1|First : 0
RxComputationScheduler-1|Second : 0
RxComputationScheduler-1|First : 1
RxComputationScheduler-1|Second : 1
RxComputationScheduler-1|First : 2
RxComputationScheduler-1|Second : 2
RxComputationScheduler-1|First : 3
RxComputationScheduler-1|Second : 3
RxComputationScheduler-1|Third : 3
RxComputationScheduler-1|First : 4
RxComputationScheduler-1|Second : 4
RxComputationScheduler-1|Third : 4
RxComputationScheduler-1|First : 5
RxComputationScheduler-1|Second : 5
RxComputationScheduler-1|Third : 5
 */
