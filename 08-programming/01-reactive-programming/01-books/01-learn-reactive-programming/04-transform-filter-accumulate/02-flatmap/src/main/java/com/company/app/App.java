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
        Observable<String> filesObservable = listFolder(Paths.get("src", "main", "resources"), "{foo.txt,lorem.txt}")
                .flatMap(path -> from(path));

        subscribePrint(filesObservable, "FS");

    }
}
/*
output:
main|FS : foo_1
main|FS : foo_2
main|FS : lorem_1
main|FS : lorem_2
 */
