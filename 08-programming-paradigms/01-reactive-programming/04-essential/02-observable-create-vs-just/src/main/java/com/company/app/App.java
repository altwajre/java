package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
        observable_create_test();

        observable_just_test();
    }

    private static void observable_just_test() {
        System.out.println("#observable_just_test");
        Observable<String> source = Observable.just("Tom", "Dick", "Harry");

        source.subscribe(System.out::println);
    }

    private static void observable_create_test() {
        System.out.println("#observable_create_test");
        Observable<Object> source = Observable.create(s -> {
            s.onNext("Tom");
            s.onNext("Dick");
            s.onNext("Harry");
            s.onCompleted();
        });

        source.subscribe(System.out::println);
    }
}
/*
output:
#observable_create_test
Tom
Dick
Harry
#observable_just_test
Tom
Dick
Harry
 */
