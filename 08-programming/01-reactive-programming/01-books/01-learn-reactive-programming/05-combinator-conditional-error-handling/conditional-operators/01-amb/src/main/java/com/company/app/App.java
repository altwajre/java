package com.company.app;

import rx.Observable;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
        test_amb();

        test_amb_random();

    }

    private static void test_amb_random() {
        System.out.println("#test_amb_random");
        Random r = new Random();
        Observable<String> source1 = Observable
                .just("data from source 1")
                .delay(r.nextInt(1000), TimeUnit.MILLISECONDS);

        Observable<String> source2 = Observable
                .just("data from source 2")
                .delay(r.nextInt(1000), TimeUnit.MILLISECONDS);

        Observable<String> amb = Observable.amb(source1, source2);

        amb.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_amb_random
data from source 2
Completed!

or

#test_amb_random
data from source 1
Completed!
 */

    private static void test_amb() {
        System.out.println("#test_amb");
        Observable<String> words = Observable.just("Some", "Other");

        Observable<Long> interval = Observable
                .interval(200L, TimeUnit.MILLISECONDS)
                .take(2);

        Observable<? extends Serializable> amb = Observable.amb(words, interval);

        amb.subscribe(
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
#test_amb
Some
Other
Completed!
 */
}
