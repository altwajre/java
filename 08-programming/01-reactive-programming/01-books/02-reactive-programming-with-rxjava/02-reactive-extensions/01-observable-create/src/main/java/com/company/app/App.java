package com.company.app;

import rx.Observable;
import rx.Subscription;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

public class App {
    static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main(String[] args) {
        test_1();

        test_2_low_level();

        test_3_multiple_subscribers();

        test_4_multiple_subscribers_cache();

        test_5_infinite_streams();

        test_6_try_catch();

        test_7_fromcallable();

    }

    private static void test_7_fromcallable() {
        System.out.println("#test_7_fromcallable");
        Observable<Integer> pub = Observable
                .fromCallable(() -> 5);

        log("Starting");
        pub.subscribe(sub -> log("Element A: " + sub));
        pub.subscribe(sub -> log("Element B: " + sub));
        log("Exit");
    }
/*
output:
main: Starting
main: Element A: 5
main: Element B: 5
main: Exit
 */

    private static void test_6_try_catch() {
        System.out.println("#test_6_try_catch");
        Observable<Integer> pub = Observable
                .create(
                        subscriber -> {
                            try{
                                log("Create");
                                subscriber.onNext(5);
                                subscriber.onCompleted();
                                log("Completed");
                            }
                            catch (Exception e){
                                subscriber.onError(e);
                            }
                        });

        log("Starting");
        pub.subscribe(sub -> log("Element A: " + sub));
        pub.subscribe(sub -> log("Element B: " + sub));
        log("Exit");
    }
/*
output:
#test_6_try_catch
main: Starting
main: Create
main: Element A: 5
main: Completed
main: Create
main: Element B: 5
main: Completed
main: Exit
 */

    private static void test_5_infinite_streams() {
        System.out.println("#test_5_infinite_streams");
        Observable<BigInteger> pub = Observable
                .create(
                        subscriber -> {
                            Runnable r = () -> {
                                BigInteger i = BigInteger.ZERO;
                                while (!subscriber.isUnsubscribed()) {
                                    subscriber.onNext(i);
                                    i = i.add(ONE);
                                }
                                System.out.println(Thread.currentThread().getName() + ": END while");
                            };
                            new Thread(r).start();
                        }
                );

        log("Starting");
        Subscription subscribeA = pub.subscribe(sub -> log("Element A: " + sub));
        Subscription subscribeB = pub.subscribe(sub -> log("Element B: " + sub));
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscribeA.unsubscribe();
        subscribeB.unsubscribe();

        log("Exit");
    }
/*
output:
main: Starting
Thread-0: Element A: 0
Thread-1: Element B: 0
Thread-0: Element A: 1
Thread-0: Element A: 2
Thread-1: Element B: 1
Thread-0: Element A: 3
Thread-0: Element A: 4
Thread-0: Element A: 5
Thread-1: Element B: 2
Thread-1: Element B: 3
Thread-0: Element A: 6
Thread-0: Element A: 7
Thread-1: Element B: 4
Thread-0: Element A: 8
Thread-0: END while
main: Exit
Thread-1: Element B: 5
Thread-1: END while
 */

    private static void test_3_multiple_subscribers() {
        System.out.println("#test_3_multiple_subscribers");
        Observable<Integer> pub = Observable
                .create(
                        subscriber -> {
                            log("Create");
                            subscriber.onNext(5);
                            subscriber.onNext(6);
                            subscriber.onCompleted();
                            log("Completed");
                        });

        log("Starting");
        pub.subscribe(sub -> log("Element A: " + sub));
        pub.subscribe(sub -> log("Element B: " + sub));
        log("Exit");
    }
/*
output:
#test_3_multiple_subscribers
main: Starting
main: Create
main: Element A: 5
main: Element A: 6
main: Completed
main: Create
main: Element B: 5
main: Element B: 6
main: Completed
main: Exit
 */

    // avoid calling create() for each subscriber and simply reuse events that were already computed.
    private static void test_4_multiple_subscribers_cache() {
        System.out.println("#test_4_multiple_subscribers_cache");
        Observable<Object> pub = Observable
                .create(
                        subscriber -> {
                            log("Create");
                            subscriber.onNext(5);
                            subscriber.onNext(6);
                            subscriber.onCompleted();
                            log("Completed");
                        }).cache();

        log("Starting");
        pub.subscribe(sub -> log("Element A: " + sub));
        pub.subscribe(sub -> log("Element B: " + sub));
        log("Exit");
    }
/*
output:
#test_4_multiple_subscribers_cache
main: Starting
main: Create
main: Element A: 5
main: Element A: 6
main: Completed
main: Create
main: Element B: 5
main: Element B: 6
main: Completed
main: Exit
 */

    private static void test_2_low_level() {
        System.out.println("#test_2_low_level");
        Observable<Integer> pub = Observable
                .create(subscriber -> {
                    log("Create");
                    subscriber.onNext(5);
                    subscriber.onNext(6);
                    subscriber.onNext(7);
                    subscriber.onCompleted();
                    log("Completed");
                });
        log("Starting");
        pub.subscribe(sub -> log("Element: " + sub));
        log("Exit");
    }
/*
output:
main: Starting
main: Create
main: Element: 5
main: Element: 6
main: Element: 7
main: Completed
main: Exit
 */

    private static void test_1() {
        System.out.println("#test_1");
        log("Before");
        Observable
                .range(5, 3)
                .subscribe(sub -> {
                    log(sub);
                });
        log("After");
    }
/*
output:
main: Before
main: 5
main: 6
main: 7
main: After
 */
}
