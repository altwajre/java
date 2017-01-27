package com.company.app;

import rx.Observable;
import rx.Subscription;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

class User{
    private final String firstName;
    private final String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

public class App
{
    static <T> Subscription subscribePrint(Observable<T> observable, String name){
        return observable.subscribe(
                (v) -> System.out.println(name + " : " + v),
                (e) -> {
                    System.err.println("Error from " + name + ":");
                    System.err.println(e.getMessage());
                },
                () -> System.out.println(name + " ended!")
        );
    }

    static <T> Observable<T> fromIterable(final Iterable<T> iterable){
        return Observable.create(sub -> {
           try{
               Iterator<T> iterator = iterable.iterator();
               while(iterator.hasNext()) {
                   sub.onNext(iterator.next());
               }
               sub.onCompleted();
           }
           catch (Exception e){
               sub.onError(e);
           }
        });
    }

    public static void main( String[] args )
    {

        test_observable_from();

        test_observable_just();

        test_observable_interval();

        test_observable_timer();

        test_observable_error();

        test_observable_empty();

        test_observable_never();

        test_observable_range();

        test_observable_create();

    }

    private static void test_observable_create() {
        System.out.println("#test_observable_create");

        List<String> list = Arrays.asList("Tom", "Dick", "Harry");
        Observable<String> pub = fromIterable(list);
        System.out.println("*Subscriber_1");
        pub.subscribe(System.out::println);

        System.out.println("*Subscriber_2");
        pub.subscribe(sub -> System.out.print(sub + ", "));
    }
/*
output:
#test_observable_create
*Subscriber_1
Tom
Dick
Harry
*Subscriber_2
Tom, Dick, Harry,
 */

    private static void test_observable_range() {
        System.out.println("#test_observable_range");

        final String name = "Range Observable";

        Observable
                .range(1, 3)
                .subscribe(
                        (v) -> System.out.println(name + " : " + v),
                        (e) -> {
                            System.err.println("Error from " + name + ":");
                            System.err.println(e.getMessage());
                        },
                        () -> System.out.println(name + " ended!")
                );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_observable_range
Range Observable : 1
Range Observable : 2
Range Observable : 3
Range Observable ended!
 */

    private static void test_observable_never() {
        System.out.println("#test_observable_never");

        final String name = "Never Observable";

        Observable
                .never()
                .subscribe(
                        (v) -> System.out.println(name + " : " + v),
                        (e) -> {
                            System.err.println("Error from " + name + ":");
                            System.err.println(e.getMessage());
                        },
                        () -> System.out.println(name + " ended!")
                );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_observable_never
 */

    private static void test_observable_empty() {
        System.out.println("#test_observable_empty");

        final String name = "Empty Observable";

        Observable
                .empty()
                .subscribe(
                        (v) -> System.out.println(name + " : " + v),
                        (e) -> {
                            System.err.println("Error from " + name + ":");
                            System.err.println(e.getMessage());
                        },
                        () -> System.out.println(name + " ended!")
                );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_observable_empty
Empty Observable ended!
 */

    private static void test_observable_error() {
        System.out.println("#test_observable_error");

        final String name = "Error Observable";

        Observable
                .error(new Exception("Test Error!"))
                .subscribe(
                        (v) -> System.out.println(name + " : " + v),
                        (e) -> {
                            System.err.println("Error from " + name + ":");
                            System.err.println(e.getMessage());
                        },
                        () -> System.out.println(name + " ended!")
                );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_observable_error
Error from Error Observable:
Test Error!
 */

    private static void test_observable_timer() {
        System.out.println("#test_observable_timer");

        final String name = "Timer Observable";

        Observable
                .timer(1L, TimeUnit.SECONDS)
                .subscribe(
                        (v) -> System.out.println(name + " : " + v),
                        (e) -> {
                            System.err.println("Error from " + name + ":");
                            System.err.println(e.getMessage());
                        },
                        () -> System.out.println(name + " ended!")
                );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_observable_timer
Timer Observable : 0
Timer Observable ended!
 */

    private static void test_observable_interval() {
        System.out.println("#test_observable_interval");

        final String name = "Interval Observable";

        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe(
                        (v) -> System.out.println(name + " : " + v),
                        (e) -> {
                            System.err.println("Error from " + name + ":");
                            System.err.println(e.getMessage());
                        },
                        () -> System.out.println(name + " ended!")
                );

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_observable_interval
Interval Observable : 0
Interval Observable : 1
Interval Observable : 2
Interval Observable : 3
Interval Observable : 4
 */

    private static void test_observable_just() {
        System.out.println("#test_observable_just");

        System.out.println("##String");
        Observable<String> pubS = Observable.just("Tom", "Dick", "Harry");
        pubS.subscribe(System.out::println);

        System.out.println("##Object");
        Observable<String> pubO = Observable
                .just(new User("Tom", "Lee"))
                .map(u -> u.getFirstName() + " " + u.getLastName());
        pubO.subscribe(System.out::println);
    }
/*
output:
#test_observable_just
##String
Tom
Dick
Harry
##Object
Tom Lee
 */

    private static void test_observable_from() {
        System.out.println("#test_observable_from");

        System.out.println("##test_from_iterable");
        List<String> list = Arrays.asList("Tom", "Dick", "Harry");
        Observable<String> pubI = Observable.from(list);

        System.out.println("*Subscriber_1");
        pubI.subscribe(System.out::println);

        System.out.println("*Subscriber_2");
        pubI.subscribe(sub -> System.out.print(sub + ", "));

        System.out.println("\n##test_from_array");
        Observable<Integer> pubA = Observable.from(new Integer[]{3, 5, 8});
        pubA.subscribe(System.out::println);
    }
/*
output:
#test_from
##test_from_iterable
*Subscriber_1
Tom
Dick
Harry
*Subscriber_2
Tom, Dick, Harry,
##test_from_array
3
5
8
 */

}
