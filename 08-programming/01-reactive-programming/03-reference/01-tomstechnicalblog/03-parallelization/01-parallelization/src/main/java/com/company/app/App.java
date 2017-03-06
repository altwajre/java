package com.company.app;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        sequential();

        parallelSchedulersComputation();

        parallelSchedulersIO();
    }

    private static void parallelSchedulersIO() {
        System.out.println("#parallelSchedulersIO");

        long startTime = System.nanoTime();
        Observable<Integer> vals = Observable.range(1, 10);
        vals
                .flatMap(val -> Observable.just(val) // PARALLEL HERE
                        .subscribeOn(Schedulers.io())
                        .map(i -> intenseCalculation(i)), 4) // Limit 4 concurrent processes.
                .doAfterTerminate(() -> {
                    long estimatedTime = System.nanoTime() - startTime;
                    long elapsedTime = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
                    System.out.println(Thread.currentThread().getName() + " COMPLETED in " + elapsedTime + " millis");
                })
                .toList()
                .subscribe(val -> System.out.println(Thread.currentThread().getName() + ": receive " + val));

        sleep(2000);
    }
/*
output:
#parallelSchedulersIO
RxIoScheduler-2: emitting 1
RxIoScheduler-3: emitting 2
RxIoScheduler-4: emitting 3
RxIoScheduler-5: emitting 4
RxIoScheduler-6: emitting 5
RxIoScheduler-4: emitting 6
RxIoScheduler-5: emitting 7
RxIoScheduler-2: emitting 8
RxIoScheduler-4: emitting 9
RxIoScheduler-3: emitting 10
RxIoScheduler-2 COMPLETED in 838 millis
RxIoScheduler-2: receive [3, 4, 1, 6, 2, 5, 7, 10, 9, 8]
 */

// The flatMap() has to merge emissions from multiple Observables happening on multiple threads,
// but it cannot allow concurrent onNext() calls to happen down the chain including the Subscriber.
// It will not block and sync either because that would undermine the benefits of RxJava. Instead of blocking,
// it will re-use the thread currently emitting something out of the flatMap().
    private static void parallelSchedulersComputation() {
        System.out.println("#parallelSchedulersComputation");

        long startTime = System.nanoTime();
        Observable<Integer> vals = Observable.range(1, 10);
        vals
                .flatMap(val -> Observable.just(val) // PARALLEL HERE
                        .subscribeOn(Schedulers.computation())
                        .map(i -> intenseCalculation(i)))
                .doAfterTerminate(() -> {
                    long estimatedTime = System.nanoTime() - startTime;
                    long elapsedTime = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
                    System.out.println(Thread.currentThread().getName() + " COMPLETED in " + elapsedTime + " millis");
                })
                .toList()
                .subscribe(val -> System.out.println(Thread.currentThread().getName() + ": receive " + val));

        sleep(2000);
    }
/*
output:
#parallelSchedulersComputation
RxComputationScheduler-2: emitting 1
RxComputationScheduler-3: emitting 2
RxComputationScheduler-4: emitting 3
RxComputationScheduler-5: emitting 4
RxComputationScheduler-6: emitting 5
RxComputationScheduler-7: emitting 6
RxComputationScheduler-8: emitting 7
RxComputationScheduler-1: emitting 8
RxComputationScheduler-3: emitting 10
RxComputationScheduler-2: emitting 9
RxComputationScheduler-2 COMPLETED in 539 millis
RxComputationScheduler-2: receive [4, 7, 8, 6, 2, 1, 5, 10, 3, 9]
 */

    private static void sequential() {
        System.out.println("#sequential");

        long startTime = System.nanoTime();
        Observable<Integer> vals = Observable.range(1, 10);
        vals
                .subscribeOn(Schedulers.computation())
                .map(i -> intenseCalculation(i))
                .doAfterTerminate(() -> {
                    long estimatedTime = System.nanoTime() - startTime;
                    long elapsedTime = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
                    System.out.println(Thread.currentThread().getName() + " COMPLETED in " + elapsedTime + " millis");
                })
                .subscribe(val -> System.out.println(Thread.currentThread().getName() + ": receive " + val));

        sleep(4000);
    }
/*
output:
#sequential
RxComputationScheduler-1: emitting 1
RxComputationScheduler-1: receive 1
RxComputationScheduler-1: emitting 2
RxComputationScheduler-1: receive 2
RxComputationScheduler-1: emitting 3
RxComputationScheduler-1: receive 3
RxComputationScheduler-1: emitting 4
RxComputationScheduler-1: receive 4
RxComputationScheduler-1: emitting 5
RxComputationScheduler-1: receive 5
RxComputationScheduler-1: emitting 6
RxComputationScheduler-1: receive 6
RxComputationScheduler-1: emitting 7
RxComputationScheduler-1: receive 7
RxComputationScheduler-1: emitting 8
RxComputationScheduler-1: receive 8
RxComputationScheduler-1: emitting 9
RxComputationScheduler-1: receive 9
RxComputationScheduler-1: emitting 10
RxComputationScheduler-1: receive 10
RxComputationScheduler-1 COMPLETED in 2657 millis
 */

    static int intenseCalculation(int i) {
        System.out.println(Thread.currentThread().getName() + ": emitting " + i);
        sleep(randInt(100, 500));
        return i;
    }

    static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
