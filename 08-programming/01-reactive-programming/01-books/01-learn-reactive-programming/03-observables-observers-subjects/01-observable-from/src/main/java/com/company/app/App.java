package com.company.app;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        test_from_iterable();

        test_from_array();
    }

    private static void test_from_array() {
        System.out.println("\n#test_from_array");
        Observable<Integer> pub = Observable.from(new Integer[]{3, 5, 8});
        pub.subscribe(System.out::println);
    }
/*
output:
#test_from_array
3
5
8
 */

    private static void test_from_iterable() {
        System.out.println("#test_from_iterable");
        List<String> list = Arrays.asList("Tom", "Dick", "Harry");
        Observable<String> pub = Observable.from(list);

        System.out.println("*Subscriber_1");
        pub.subscribe(System.out::println);

        System.out.println("*Subscriber_2");
        pub.subscribe(sub -> System.out.print(sub + ", "));
    }
/*
output:
#test_from_iterable
*Subscriber_1
Tom
Dick
Harry
*Subscriber_2
Tom, Dick, Harry,
 */

}
