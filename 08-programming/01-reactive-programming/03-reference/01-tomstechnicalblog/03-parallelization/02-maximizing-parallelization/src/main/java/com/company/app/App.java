package com.company.app;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    private static final Random rand = new Random();

    public static void main(String[] args) {

        schedulersComputation();

        maximizingParallelizationSchedulersComputation();

        roundRobinParallelization();

        maximizingParallelizationRoundRobinParallelization();

    }

    private static void maximizingParallelizationRoundRobinParallelization() {
        System.out.println("#maximizingParallelizationRoundRobinParallelization");
        long startTime = System.nanoTime();

        final AtomicInteger batch = new AtomicInteger(0);

        int threadCt = Runtime.getRuntime().availableProcessors();

        ExecutorService executor = Executors.newFixedThreadPool(threadCt);

        Scheduler scheduler = Schedulers.from(executor);

        Observable.range(1, 100)
                .groupBy(i -> batch.getAndIncrement() % threadCt)
                .flatMap(g -> g.observeOn(scheduler)
                        .map(i -> intenseCalculation(i)))
                .doAfterTerminate(() -> {
                    long estimatedTime = System.nanoTime() - startTime;
                    long elapsedTime = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
                    System.out.println(Thread.currentThread().getName() + " COMPLETED in " + elapsedTime + " millis");
                    executor.shutdown();
                })
                .subscribe(v -> System.out.println(Thread.currentThread().getName() + ": receive " + v));
    }
/*
output:
#maximizingParallelizationRoundRobinParallelization
pool-1-thread-2: emitting 1
pool-1-thread-3: emitting 2
pool-1-thread-4: emitting 3
pool-1-thread-5: emitting 4
pool-1-thread-6: emitting 5
...
pool-1-thread-4: receive 91
pool-1-thread-4: emitting 99
pool-1-thread-5: receive 100
pool-1-thread-4: receive 99
pool-1-thread-4 COMPLETED in 4291 millis
 */

    private static void roundRobinParallelization() {
        System.out.println("#roundRobinParallelization");

        long startTime = System.nanoTime();

        final AtomicInteger batch = new AtomicInteger(0);

        Observable.range(1, 100)
                .groupBy(i -> batch.getAndIncrement() % 5)
                .doOnNext(v -> System.out.println(Thread.currentThread().getName() + ": " + v))
                .flatMap(g -> g.observeOn(Schedulers.io())
                        .map(i -> intenseCalculation(i)))
                .doAfterTerminate(() -> {
                    long estimatedTime = System.nanoTime() - startTime;
                    long elapsedTime = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
                    System.out.println(Thread.currentThread().getName() + " COMPLETED in " + elapsedTime + " millis");
                })
                .subscribe(v -> System.out.println(Thread.currentThread().getName() + ": receive " + v));

        sleep(7000);
    }
/*
output:
#roundRobinParallelization
main: rx.internal.operators.OperatorGroupBy$GroupedUnicast@36aa7bc2
main: rx.internal.operators.OperatorGroupBy$GroupedUnicast@6d86b085
RxIoScheduler-2: emitting 1
main: rx.internal.operators.OperatorGroupBy$GroupedUnicast@161cd475
RxIoScheduler-3: emitting 2
main: rx.internal.operators.OperatorGroupBy$GroupedUnicast@47089e5f
RxIoScheduler-4: emitting 3
main: rx.internal.operators.OperatorGroupBy$GroupedUnicast@38cccef
RxIoScheduler-5: emitting 4
RxIoScheduler-6: emitting 5
...
RxIoScheduler-5: receive 94
RxIoScheduler-5: emitting 99
RxIoScheduler-5: receive 99
RxIoScheduler-6: receive 100
RxIoScheduler-6 COMPLETED in 6704 millis
 */

    private static void maximizingParallelizationSchedulersComputation() {
        System.out.println("#maximizingParallelizationSchedulersComputation");

        long startTime = System.nanoTime();

        int threadCt = Runtime.getRuntime().availableProcessors();
        System.out.println(threadCt);

        ExecutorService executor = Executors.newFixedThreadPool(threadCt);

        Scheduler scheduler = Schedulers.from(executor);

        Observable.range(1, 100)
                .flatMap(v -> Observable.just(v)
                        .subscribeOn(scheduler)
                        .map(i -> intenseCalculation(i)))
                .doAfterTerminate(() -> {
                    long estimatedTime = System.nanoTime() - startTime;
                    long elapsedTime = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
                    System.out.println(Thread.currentThread().getName() + " COMPLETED in " + elapsedTime + " millis");
                    executor.shutdown();
                })
                .subscribe(v -> System.out.println(Thread.currentThread().getName() + ": receive " + v));
    }
/*
output:
#maximizingParallelizationSchedulersComputation
8
pool-1-thread-1: emitting 1
pool-1-thread-2: emitting 2
pool-1-thread-3: emitting 3
pool-1-thread-4: emitting 4
pool-1-thread-5: emitting 5
pool-1-thread-6: emitting 6
...
pool-1-thread-3: receive 95
pool-1-thread-1: receive 99
pool-1-thread-8: receive 100
pool-1-thread-8 COMPLETED in 3928 millis
 */

    // NOT maximizing parallelization
    private static void schedulersComputation() {
        System.out.println("#schedulersComputation");

        long startTime = System.nanoTime();

        Observable.range(1, 100)
                .flatMap(v -> Observable.just(v)
                        .subscribeOn(Schedulers.computation())
                        .map(i -> intenseCalculation(i)))
                .doAfterTerminate(() -> {
                    long estimatedTime = System.nanoTime() - startTime;
                    long elapsedTime = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
                    System.out.println(Thread.currentThread().getName() + " COMPLETED in " + elapsedTime + " millis");
                })
                .subscribe(v -> System.out.println(Thread.currentThread().getName() + ": receive " + v));

        sleep(6000);
    }
/*
output:
#schedulersComputation
RxComputationScheduler-1: emitting 1
RxComputationScheduler-2: emitting 2
RxComputationScheduler-3: emitting 3
RxComputationScheduler-4: emitting 4
RxComputationScheduler-5: emitting 5
RxComputationScheduler-6: emitting 6
...
RxComputationScheduler-4: receive 92
RxComputationScheduler-4: emitting 100
RxComputationScheduler-4: receive 100
RxComputationScheduler-4 COMPLETED in 4445 millis
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
