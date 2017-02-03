package com.company.app;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        test_filter();

        test_takelast();

        test_take();

        test_last();

        test_first();

        test_takeLastbuffer();

        test_lastordefault();

        test_skiplast();

        test_skip();

        test_elementat();

        test_distinct();

        test_distinctuntilchanged();

        test_oftype();

    }

    private static void test_oftype() {
        System.out.println("#test_oftype");
        Observable<?> various = Observable
                .from(Arrays.asList("1", 2, 3.0, 4, 5L));

// The ofType() operator creates an Observable instance that emits only the items emitted by the source of a given type.
// It is a shortcut to: filter(v -> Class.isInstance(v)).
        Observable<Integer> observable = various.ofType(Integer.class);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_oftype
2
4
Completed!
 */

    private static void test_distinctuntilchanged() {
        System.out.println("#test_distinctuntilchanged");
        Observable<String> words = Observable
                .just("One", "of", "the", "few", "of", "the", "crew", "crew");

// The distinctUntilChanged() operator returns emits all items emitted by the source Observable instance
// that are distinct from their immediate predecessors.
        Observable<String> observable = words.distinctUntilChanged();

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_distinctuntilchanged
One
of
the
few
of
the
crew
Completed!
 */

    private static void test_distinct() {
        System.out.println("#test_distinct");
        Observable<String> words = Observable
                .just("One", "of", "the", "few", "of", "the", "crew", "crew");
        Observable<String> observable = words.distinct();

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_distinct
One
of
the
few
crew
Completed!
 */

    private static void test_elementat() {
        System.out.println("#elementAt");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> observable = numbers.elementAt(5);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#elementAt
8
Completed!
 */

    private static void test_skip() {
        System.out.println("#skip");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> observable = numbers.skip(4);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#skip
21
8
98
103
55
Completed!
 */

    private static void test_skiplast() {
        System.out.println("#skipLast");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> observable = numbers.skipLast(4);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#skipLast
1
13
32
45
21
Completed!
 */

    private static void test_lastordefault() {
        System.out.println("#lastOrDefault");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> observable = numbers.lastOrDefault(200);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        System.out.println("*test_empty");
        Observable.empty().lastOrDefault(200).subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#lastOrDefault
55
Completed!
*test_empty
200
Completed!
 */

    private static void test_takeLastbuffer() {
        System.out.println("#takeLastBuffer");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);

// The takeLastBuffer() method behaves much like the takeLast() method, but the Observable instance created
// by it emits only one item - a List instance containing the last N items from the source.
        Observable<List<Integer>> observable = numbers.takeLastBuffer(4);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#takeLastBuffer
[8, 98, 103, 55]
Completed!
 */

    private static void test_first() {
        System.out.println("#first");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> observable = numbers.first();

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#first
1
Completed!
 */

    private static void test_last() {
        System.out.println("#test_last");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> observable = numbers.last();

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_last
55
Completed!
 */

    private static void test_take() {
        System.out.println("#take");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> observable = numbers.take(4);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#take
1
13
32
45
Completed!
 */

    private static void test_takelast() {
        System.out.println("#test_takelast");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> observable = numbers.takeLast(4);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_takelast
8
98
103
55
Completed!
 */

    private static void test_filter() {
        System.out.println("#test_filter");
        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
        Observable<Integer> even = numbers
                .filter(n -> n % 2 == 0);

        even.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_filter
32
8
98
Completed!
 */
}
