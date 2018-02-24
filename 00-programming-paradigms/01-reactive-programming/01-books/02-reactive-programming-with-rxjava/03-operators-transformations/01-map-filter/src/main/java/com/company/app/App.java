package com.company.app;

import rx.Observable;

import static rx.Observable.empty;
import static rx.Observable.just;

public class App
{
    static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main( String[] args )
    {
        test_filter();

        test_map();

        test_filter_map();

        test_flatmap();

    }

    private static void test_flatmap() {
        System.out.println("#test_flatmap");
        Observable<Integer> numbers = Observable.just(2, 10, 18);

        System.out.println("map");
        numbers.map(x -> x * 2).subscribe(sub -> System.out.print(sub + ", "));
        System.out.println("\nfilter");
        numbers.filter(x -> x != 10).subscribe(sub -> System.out.print(sub + ", "));

        //equivalent
        System.out.println("\nflatMap just()");
        numbers.flatMap(x -> just(x * 2)).subscribe(sub -> System.out.print(sub + ", "));
        System.out.println("\nflatMap just() empty()");
        numbers.flatMap(x -> (x != 10) ? just(x) : empty()).subscribe(sub -> System.out.print(sub + ", "));
    }
/*
output:
map
4, 20, 36,
filter
2, 18,
flatMap just()
4, 20, 36,
flatMap just() empty()
2, 18,
 */

    private static void test_filter_map() {
        System.out.println("#test_filter_map");
        Observable // publisher
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(s -> System.out.println("C: " + s))
                .filter(s -> s.length() < 4)
                .subscribe(sub -> System.out.println("D: " + sub));
    }
/*
output:
A: 8
B: 8
C: #80
D: #80
A: 9
A: 10
B: 10
C: #100
 */

    private static void test_map() {
        System.out.println("#test_map");
        Observable<String> peoplePub = Observable.just("#Tom", "Dick", "#Harry");
        Observable<Integer> resultPub = peoplePub.map(s -> s.length());

        resultPub.subscribe(sub -> log(sub)); // .subscribe() will trigger pub to push item to subscriber
    }
/*
output:
main: 4
main: 4
main: 6
 */

    private static void test_filter() {
        System.out.println("#test_filter");
        Observable<String> peoplePub = Observable.just("#Tom", "Dick", "#Harry");
        Observable<String> resultPub = peoplePub.filter(s -> s.startsWith("#"));

        resultPub.subscribe(sub -> log(sub)); // .subscribe() will trigger pub to push item to subscriber
    }
/*
output:
main: #Tom
main: #Harry
 */
}
