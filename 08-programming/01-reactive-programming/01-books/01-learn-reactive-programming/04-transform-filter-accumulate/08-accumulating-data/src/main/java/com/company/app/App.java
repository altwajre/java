package com.company.app;

import rx.Observable;
import rx.subscriptions.Subscriptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static Observable<String> from(final Path path) {
        return Observable.<String>create(subscriber -> {
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
                while ((line = reader.readLine()) != null && !subscriber.isUnsubscribed()) {
                    subscriber.onNext(line);
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            } catch (IOException ioe) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onError(ioe);
                }
            }
        });
    }

    public static void main(String[] args) {
        test_scan();

        test_scan_last();

        test_scan_with_initialvalue();
    }

    private static void test_scan_with_initialvalue() {
        System.out.println("#test_scan_with_initialvalue");

// This example counts the number of lines in a file. The file Observable instance emits the lines of
// the file specified by the given path, one-by-one. We use the scan (T, Func2) operator with
// a seed value of 0 to count the lines by adding one to the accumulated count on every line.
        Path path = Paths.get("src", "main", "resources", "letters.txt");
        // path value = src/main/resources/letters.txt

        Observable<String> file = from(
                path
        );

        Observable<Integer> observable = file.scan(0, (p, v) -> p + 1);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_scan_with_initialvalue
0
1
2
3
4
5
Completed!
 */

    private static void test_scan_last() {
        System.out.println("#test_scan_last");
        Observable<Integer> observable = Observable
                .range(1, 10)
                .scan((p, v) -> p + v);

        observable.last().subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_scan_last
55
Completed!
 */

    private static void test_scan() {
        System.out.println("#test_scan");
        Observable<Integer> observable = Observable
                .range(1, 10)
                .scan((p, v) -> p + v);

        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_scan
1
3
6
10
15
21
28
36
45
55
Completed!
 */
}
