package com.company.app;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class App
{
    public static <T> Subscription subscribePrint(Observable<T> observable,
                                                  String name) {
        return observable.subscribe(
                (v) -> System.out.println(Thread.currentThread().getName()
                        + "|" + name + " : " + v), (e) -> {
                    System.err.println("Error from " + name + ":");
                    System.err.println(e);
                    System.err.println(Arrays
                            .stream(e.getStackTrace())
                            .limit(5L)
                            .map(stackEl -> "  " + stackEl)
                            .collect(Collectors.joining("\n"))
                    );
                }, () -> System.out.println(name + " ended!"));
    }

    static Observable<Path> listFolder(Path dir, String glob){
        return Observable.create(subscriber -> {
            try {
                DirectoryStream<Path> stream = Files.newDirectoryStream(dir, glob);
                subscriber.add(Subscriptions.create(() -> {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
                Observable.from(stream).subscribe(subscriber);
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    static Observable<String> from(final Path path) {
        return Observable.create(subscriber -> {
            try {
                BufferedReader reader = Files.newBufferedReader(path);
                subscriber.add(Subscriptions.create(() -> {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
                String line = null;
                while((line = reader.readLine()) != null && !subscriber.isUnsubscribed()){
                    subscriber.onNext(line);
                }
                if(!subscriber.isUnsubscribed()){
                    subscriber.onCompleted();
                }
            } catch (IOException e) {
                if(!subscriber.isUnsubscribed()){
                    subscriber.onError(e);
                }
            }
        });
    }

    public static void main( String[] args ) {

//        test_func();

//        test_onnext_onerror_oncompleted();

        test_func1_func2();

    }

    private static void test_func1_func2() {
        System.out.println("#test_func1_func2");
        Observable<Integer> flatMapped = Observable
                .just(5, 432)
                .flatMap(
                        v -> Observable.range(v, 2),
                        (x, y) -> x + y // x=v, y=Observable.range(v, 2)
                );
        subscribePrint(flatMapped, "flatMap");
    }
/*
output:
#test_func1_func2
main|flatMap : 10
main|flatMap : 11
main|flatMap : 864
main|flatMap : 865
flatMap ended!
 */

    private static void test_onnext_onerror_oncompleted() {
        System.out.println("#test_onnext_onerror_oncompleted");
        Observable<Integer> flatMapped = Observable
                .just(-1, 0, 1)
                .map(v -> 2 / v)
                .flatMap(
                        v -> Observable.just(v),
                        e -> Observable.just(0),
                        () -> Observable.just(42)
                );

        subscribePrint(flatMapped, "flatMap");
    }
/*
output:
#test_onnext_onerror_oncompleted
main|flatMap : -2
main|flatMap : 0
flatMap ended!
 */

    private static void test_func() {
        System.out.println("#test_func");
        Observable<String> filesObservable = listFolder(Paths.get("src", "main", "resources"), "{foo.txt,lorem.txt}")
                .flatMap(path -> from(path));

        subscribePrint(filesObservable, "FS");
    }
/*
output:
#test_func
main|FS : foo_1
main|FS : foo_2
main|FS : lorem_1
main|FS : lorem_2
 */
}
