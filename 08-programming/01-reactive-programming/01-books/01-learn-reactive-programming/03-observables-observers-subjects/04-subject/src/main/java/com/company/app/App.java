package com.company.app;

import rx.Observable;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class ReactiveSum{
    public BehaviorSubject<Double> a = BehaviorSubject.create(0.0);
    public BehaviorSubject<Double> b = BehaviorSubject.create(0.0);
    public BehaviorSubject<Double> c = BehaviorSubject.create(0.0);

    public ReactiveSum() {
        Observable.combineLatest(a, b, (x, y) -> x + y).subscribe(c);
    }
}

public class App
{
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

    public static void main( String[] args )
    {
        test_subject();

        test_reactivesum();

    }

    private static void test_reactivesum() {
        System.out.println("#test_reactivesum");
        ReactiveSum sum = new ReactiveSum();
        subscribePrint(sum.c.asObservable(), "Sum");
        sum.a.onNext(2.0);
        sum.b.onNext(3.0);
    }
/*
output:
#test_reactivesum
main|Sum : 0.0
main|Sum : 2.0
main|Sum : 5.0
 */

    private static void test_subject() {
        System.out.println("#test_subject");
        Observable<Long> interval = Observable.interval(100L, TimeUnit.MILLISECONDS);

        Subject<Long, Long> publishSubject = PublishSubject.create();
        interval.subscribe(publishSubject);

        Subscription sub1 = subscribePrint(publishSubject, "First");
        Subscription sub2 = subscribePrint(publishSubject, "Second");

        Subscription sub3 = null;

        try {
            Thread.sleep(300L);
            publishSubject.onNext(555L);
            sub3 = subscribePrint(publishSubject, "Third");
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sub1.unsubscribe();
        sub2.unsubscribe();
        sub3.unsubscribe();

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Subscription sub4 = subscribePrint(publishSubject, "Fourth");

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sub4.unsubscribe();

        System.out.println( "----------------------------------" );
    }
/*
output:
#test_subject
RxComputationScheduler-1|First : 0
RxComputationScheduler-1|Second : 0
RxComputationScheduler-1|First : 1
RxComputationScheduler-1|Second : 1
RxComputationScheduler-1|First : 2
RxComputationScheduler-1|Second : 2
main|First : 555
main|Second : 555
RxComputationScheduler-1|First : 3
RxComputationScheduler-1|Second : 3
RxComputationScheduler-1|Third : 3
RxComputationScheduler-1|First : 4
RxComputationScheduler-1|Second : 4
RxComputationScheduler-1|Third : 4
RxComputationScheduler-1|First : 5
RxComputationScheduler-1|Second : 5
RxComputationScheduler-1|Third : 5
RxComputationScheduler-1|First : 6
RxComputationScheduler-1|Second : 6
RxComputationScheduler-1|Third : 6
RxComputationScheduler-1|First : 7
RxComputationScheduler-1|Second : 7
RxComputationScheduler-1|Third : 7
RxComputationScheduler-1|Fourth : 13
RxComputationScheduler-1|Fourth : 14
RxComputationScheduler-1|Fourth : 15
RxComputationScheduler-1|Fourth : 16
RxComputationScheduler-1|Fourth : 17
----------------------------------
 */
}
