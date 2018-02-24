package com.company.app;

import rx.Observable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// A multivalued Observable type is also useful when composing single-valued responses.
public class App
{
    public static void main( String[] args )
    {
        future_test();

        observable_zip_test();

        observable_merge_test();

    }

    static Observable<String> getDataAsObservable(int i){
        return Observable.just("Done: " + i + "\n");
    }

    private static void observable_merge_test() {
        System.out.println("#observable_merge_test - emitted as soon as they are ready");
        Observable<String> o1 = getDataAsObservable(1);
        Observable<String> o2 = getDataAsObservable(2);
        Observable<String> o3 = Observable.merge(o1, o2);

        o3.subscribe(System.out::println);
    }
/*
output: - emitted as soon as they are ready
#observable_merge_test
Done: 1

Done: 2
 */

    private static void observable_zip_test() {
        System.out.println("#observable_zip_test");
        Observable<String> o1 = getDataAsObservable(1);
        Observable<String> o2 = getDataAsObservable(2);
        Observable<String> o3 = Observable.zip(o1, o2, (x, y) -> x + y);

        o3.subscribe(System.out::println);
    }
/*
output:
#observable_zip_test
Done: 1
Done: 2
 */

    static CompletableFuture<String> getDataAsFuture(int i){
        return CompletableFuture.completedFuture("Done: " + i + "\n");
    }

    private static void future_test() {
        System.out.println("#future_test - wait until all Futures are completed before emitting anything");
        CompletableFuture<String> f1 = getDataAsFuture(1);
        CompletableFuture<String> f2 = getDataAsFuture(2);

        CompletableFuture<String> f3 = f1.thenCombine(f2, (x, y) -> x + y);

        try {
            System.out.println(f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
/*
output: - wait until all Futures are completed before emitting anything
#future_test
Done: 1
Done: 2
 */

}
