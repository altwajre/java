package com.company.app;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App {

    public static void main(String... args) {

        iteratorTest();

        simpleObervable();

        obervableWithListener();
    }

    private static void obervableWithListener() {

        System.out.println("#obervableWithListener");

        List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five");
        Observable<String> observable = Observable.from(list);
        observable.subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String element) {
                        System.out.println(element);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable t) {
                        System.err.println(t); // (1)
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("We've finnished!"); // (2)
                    }
                });
    }

    private static void simpleObervable() {

        System.out.println("#simpleObervable");

        List<String> list = Arrays.asList("rxOne", "Two", "Three", "Four", "Five");
        Observable<String> observable = Observable.from(list);

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String element) {
                System.out.println(element);
            }
        });

    }

    private static void iteratorTest() {

        System.out.println("#iteratorTest");

        List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
