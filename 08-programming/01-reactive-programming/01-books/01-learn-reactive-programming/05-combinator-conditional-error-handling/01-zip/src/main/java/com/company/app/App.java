package com.company.app;

import rx.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
        test_zip();

        test_zip_from_interval();

        test_zipwith();
    }

    private static void test_zipwith() {
        System.out.println("#test_zipwith");
        Observable<String> observable = Observable
                .from(Arrays.asList("Z", "I", "P", "P"))
                .zipWith(
                        Observable.interval(200L, TimeUnit.MILLISECONDS),
                        (value, skip) -> value
                );

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_zipwith
Z
I
P
P
Completed!
 */

    private static void test_zip_from_interval() {
        System.out.println("#test_zip_from_interval");
        Observable<String> observable = Observable
                .zip(
                        Observable.from(Arrays.asList("Z", "I", "P", "P")),
                        Observable.interval(200L, TimeUnit.MILLISECONDS),
                        (value, i) -> value
                );

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_zip_from_interval
Z
I
P
P
Completed!
 */

    private static void test_zip() {
        System.out.println("#test_zip");
        Observable<Integer> observable = Observable
                .zip(
                        Observable.just(1, 3, 4),
                        Observable.just(5, 2, 6, 8, 9),
                        (a, b) -> a + b
                );

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_zip
6
5
10
Completed!
 */
}
