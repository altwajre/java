package com.company.app;

import rx.Observable;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        getDataFromLocalMemorySync_test();

        getDataFromNetworkAsync_test();
    }

    private static void getDataFromNetworkAsync_test() {
        System.out.println("#getDataFromNetworkAsync_test");

        // It will receive 5 strings (15 were emitted but the first 10 were dropped), and then un-subscribe (ignoring
        // or stopping the rest of the strings that were to be emitted).
        getDataFromNetworkAsync()
                .skip(10)
                .take(5)
                .map(s -> s + "_transformed")
                .subscribe(System.out::println);
    }
/*
output:
#getDataFromNetworkAsync_test
10_transformed
11_transformed
12_transformed
13_transformed
14_transformed
 */

    private static Observable<String> getDataFromNetworkAsync() {
        return Observable
                .range(0, 100)
                .map(Object::toString);
    }

    private static void getDataFromLocalMemorySync_test() {
        System.out.println("#getDataFromLocalMemorySync_test");

        Stream<String> stringStream = getDataFromLocalMemorySync();

        // It will retrieve 100 strings from getDataFromLocalMemorySync()
        // uncomment code below to test Iterable<String> contains 100 strings
//        System.out.println(stringStream
//                .count());

        stringStream
                .skip(10)
                .limit(5)
                .map(s -> s + "_transformed")
                .forEach(System.out::println);
    }
/*
output:
#getDataFromLocalMemorySync_test
10_transformed
11_transformed
12_transformed
13_transformed
14_transformed
 */

    private static Stream<String> getDataFromLocalMemorySync() {
        return IntStream
                .range(0, 100)
                .mapToObj(Integer::toString);
    }
}
