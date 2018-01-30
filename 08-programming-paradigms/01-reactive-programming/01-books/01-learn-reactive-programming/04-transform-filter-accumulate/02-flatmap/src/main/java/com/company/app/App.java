package com.company.app;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class App {
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

    static Observable<Path> listFolder(Path dir, String glob) {
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

// Note the use of the Subscriber.add() operator to add a new Subscription instance to the subscriber,
// created using the Subscriptions.create() operator. This method creates a Subscription instance using an action.
// This action will be executed when the Subscription instance is unsubscribed,
// which means when the Subscriber instance is unsubscribed in this case.
// So this is similar to putting the closing of the stream in the final block.
                subscriber.add(Subscriptions.create(() -> {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));

                String line = null;
                while ((line = reader.readLine()) != null && !subscriber.isUnsubscribed()) {
                    subscriber.onNext(line);
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            } catch (IOException e) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public static void main(String[] args) {

        test_func();

        test_onnext_onerror_oncompleted();

        test_func1_func2();

        test_flatmapIterable();

        test_switchmap();
    }

    private static void test_switchmap() {
        System.out.println("#test_switchmap");
        Observable<String> obs = Observable
                .interval(40L, TimeUnit.MILLISECONDS)
                .switchMap(v -> {
                    System.out.println("v=" + v);
                    return Observable
                            .timer(0L, 10L, TimeUnit.MILLISECONDS)
                            .map(u -> "observable <" + (v + 1) + "> : " + (v + u));
                        }
                );
        subscribePrint(obs, "switchMap");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_switchmap
v=0
RxComputationScheduler-2|switchMap : observable <1> : 0
RxComputationScheduler-2|switchMap : observable <1> : 1
RxComputationScheduler-2|switchMap : observable <1> : 2
RxComputationScheduler-2|switchMap : observable <1> : 3
v=1
RxComputationScheduler-3|switchMap : observable <2> : 1
RxComputationScheduler-3|switchMap : observable <2> : 2
RxComputationScheduler-3|switchMap : observable <2> : 3
RxComputationScheduler-3|switchMap : observable <2> : 4
v=2
RxComputationScheduler-4|switchMap : observable <3> : 2
RxComputationScheduler-4|switchMap : observable <3> : 3
RxComputationScheduler-4|switchMap : observable <3> : 4
RxComputationScheduler-4|switchMap : observable <3> : 5
v=3
RxComputationScheduler-5|switchMap : observable <4> : 3
RxComputationScheduler-5|switchMap : observable <4> : 4
RxComputationScheduler-5|switchMap : observable <4> : 5
RxComputationScheduler-5|switchMap : observable <4> : 6
v=4
RxComputationScheduler-6|switchMap : observable <5> : 4
RxComputationScheduler-6|switchMap : observable <5> : 5
RxComputationScheduler-6|switchMap : observable <5> : 6
RxComputationScheduler-6|switchMap : observable <5> : 7
v=5
RxComputationScheduler-7|switchMap : observable <6> : 5
RxComputationScheduler-7|switchMap : observable <6> : 6
RxComputationScheduler-7|switchMap : observable <6> : 7
RxComputationScheduler-7|switchMap : observable <6> : 8
v=6
RxComputationScheduler-7|switchMap : observable <6> : 9
RxComputationScheduler-8|switchMap : observable <7> : 6
RxComputationScheduler-8|switchMap : observable <7> : 7
RxComputationScheduler-8|switchMap : observable <7> : 8
RxComputationScheduler-8|switchMap : observable <7> : 9
v=7
RxComputationScheduler-1|switchMap : observable <8> : 7
RxComputationScheduler-1|switchMap : observable <8> : 8
RxComputationScheduler-1|switchMap : observable <8> : 9
RxComputationScheduler-1|switchMap : observable <8> : 10
v=8
RxComputationScheduler-2|switchMap : observable <9> : 8
RxComputationScheduler-2|switchMap : observable <9> : 9
RxComputationScheduler-2|switchMap : observable <9> : 10
RxComputationScheduler-2|switchMap : observable <9> : 11
RxComputationScheduler-2|switchMap : observable <9> : 12
v=9
RxComputationScheduler-3|switchMap : observable <10> : 9
RxComputationScheduler-3|switchMap : observable <10> : 10
RxComputationScheduler-3|switchMap : observable <10> : 11
RxComputationScheduler-3|switchMap : observable <10> : 12
v=10
RxComputationScheduler-4|switchMap : observable <11> : 10
RxComputationScheduler-4|switchMap : observable <11> : 11
RxComputationScheduler-4|switchMap : observable <11> : 12
RxComputationScheduler-4|switchMap : observable <11> : 13
v=11
RxComputationScheduler-4|switchMap : observable <11> : 14
RxComputationScheduler-5|switchMap : observable <12> : 11
RxComputationScheduler-5|switchMap : observable <12> : 12
RxComputationScheduler-5|switchMap : observable <12> : 13
 */

    private static void test_flatmapIterable() {
        System.out.println("#test_flatmapIterable");
        Observable<? extends Serializable> flatMapIterable = Observable
                .just(
                        Arrays.asList(2, 4),
                        Arrays.asList("two", "four")
                )
                .flatMapIterable(l -> l);

        subscribePrint(flatMapIterable, "flatMapIterable");

        System.out.println("*flatMapIterable(l -> l) is the same as flatMap(l -> Observable.from(l))");
        Observable<? extends Serializable> flatMap = Observable
                .just(
                        Arrays.asList(2, 4),
                        Arrays.asList("two", "four")
                )
                .flatMap(l -> Observable.from(l));
        subscribePrint(flatMap, "flatMap");

    }
/*
output:
#test_flatmapIterable
main|flatMapIterable : 2
main|flatMapIterable : 4
main|flatMapIterable : two
main|flatMapIterable : four
flatMapIterable ended!
*flatMapIterable(l -> l) is the same as flatMap(l -> Observable.from(l))
main|flatMap : 2
main|flatMap : 4
main|flatMap : two
main|flatMap : four
flatMap ended!
 */

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
