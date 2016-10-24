package observables;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.HashMap;
import java.util.Map;

public class ObservablesTest {

    @Test
    public void java8VsJava6Test() {

        Map<String, Observable<String>> bucket = new HashMap<>();
        bucket.put("doc1", Observable.just("document 1"));
        bucket.put("doc2", Observable.just("document 2"));
        bucket.put("doc3", Observable.just("document 3"));

        System.out.println("# Java 8");
        Observable
                .just("doc1", "doc2", "doc3")
                .flatMap(bucket :: get)
                .subscribe(d -> System.out.println("Got: " + d));

        // Java 6 - Loads 3 documents in parallel
        System.out.println("# Java 6");
        Observable
                .just("doc1", "doc2", "doc3")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String id) {
                        return bucket.get(id);
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String document) {
                System.out.println("Got: " + document);
            }
        });

    }

    @Test
    public void consumeObservableTest(){
        Observable
                .just(1, 2, 3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed Observable.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Whoops: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Got: " + integer);
                    }
                });
    }

    @Test
    public void consumeObservableHasErrorTest(){
        Observable
                .just(1, 2, 3)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if(integer.equals(2)){
                            throw new RuntimeException("I don't like 2");
                        }
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed Observable.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Whoops: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Got: " + integer);
                    }
                });
    }

    // The subscribe() also returns a Subscription which you can use to unsubscribe and therefore do not receive further events
    @Test
    public void subscriptionTest(){
        Subscription subscription = Observable
                .just(1, 2, 3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed Observable.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Whoops: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Got: " + integer);
                    }
                });

        subscription.unsubscribe();
    }

    @Test
    public void consumeObservableWithAction1Test(){
        Observable
                .just(1, 2, 3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("Got: " + integer);
                    }
                });
    }

}
