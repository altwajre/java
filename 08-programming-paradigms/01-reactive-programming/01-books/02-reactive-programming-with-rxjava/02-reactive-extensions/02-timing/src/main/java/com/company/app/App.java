package com.company.app;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class App
{
    static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main( String[] args )
    {
        test_timer();

        test_interval();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test_interval() {
        System.out.println("#test_interval");
        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe((Long zero) -> log(zero));
    }

    private static void test_timer() {
        System.out.println("#test_timer");
        Observable
                .timer(1, TimeUnit.SECONDS)
                .subscribe((Long zero) -> log(zero));
    }
}
/*
output:
#test_timer
#test_interval
RxComputationScheduler-1: 0
RxComputationScheduler-2: 0
RxComputationScheduler-2: 1
RxComputationScheduler-2: 2
RxComputationScheduler-2: 3
RxComputationScheduler-2: 4
 */
