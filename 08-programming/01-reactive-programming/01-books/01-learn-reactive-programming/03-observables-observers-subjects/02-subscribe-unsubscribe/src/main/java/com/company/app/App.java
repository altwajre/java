package com.company.app;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App
{
    static <T> Observable<T> fromIterable(final Iterable<T> iterable){
        return Observable.create(sub -> {
            try{
                Iterator<T> iterator = iterable.iterator();
                while(iterator.hasNext()) {
                    if(sub.isUnsubscribed()){
                        return;
                    }
                    sub.onNext(iterator.next());
                }
                if(!sub.isUnsubscribed()){
                    sub.onCompleted();
                }
            }
            catch (Exception e){
                if(!sub.isUnsubscribed()){
                    sub.onError(e);
                }
            }
        });
    }

    public static void main( String[] args ) throws Exception {
        test_names();

        test_unsubscribe();

    }

    private static void test_unsubscribe() throws IOException {
        Path path = Paths.get("src", "main", "resources", "lorem_big.txt");
//        Path path = Paths.get("src", "main", "resources");
        List<String> data = Files.readAllLines(path);
        Observable<String> observable = fromIterable(data).subscribeOn(Schedulers.computation());

        final String name = "Files";
        Subscription subscription = observable
                .subscribe(
                        (v) -> System.out.println(name + " : " + v),
                        (e) -> {
                            System.err.println("Error from " + name + ":");
                            System.err.println(e.getMessage());
                        },
                        () -> System.out.println(name + " ended!")
                );

        System.out.println("Before unsubscribe!");
        System.out.println("-------------------");
//        Thread.sleep(1);
        subscription.unsubscribe();
        System.out.println("-------------------");
        System.out.println("After unsubscribe!");
    }
/*
output:
Before unsubscribe!
-------------------
Files : 1
Files : 2
Files : 3
Files : 4
Files : 5
-------------------
After unsubscribe!
 */


    private static void test_names() {
        List<String> list = Arrays.asList("Tom", "Dick", "Harry");
        Observable<String> pub = fromIterable(list);
        System.out.println("*Subscriber_1");
        pub.subscribe(System.out::println);

        System.out.println("*Subscriber_2");
        pub.subscribe(sub -> System.out.print(sub + ", "));
    }
/*
output:
*Subscriber_1
Tom
Dick
Harry
*Subscriber_2
Tom, Dick, Harry,
 */
}
