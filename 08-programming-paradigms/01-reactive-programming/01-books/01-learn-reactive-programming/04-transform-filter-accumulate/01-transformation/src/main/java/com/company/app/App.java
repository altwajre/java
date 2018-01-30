package com.company.app;

import rx.Observable;
import rx.Subscription;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        Observable<String> mapped = Observable
                .just(2,3,5,8)
                .map(v -> v * 3)
                .map(v -> (v % 2 == 0) ? "even" : "odd");
        subscribePrint(mapped, "map");
    }
}
/*
output:
main|map : even
main|map : odd
main|map : odd
main|map : even
map ended!
 */
