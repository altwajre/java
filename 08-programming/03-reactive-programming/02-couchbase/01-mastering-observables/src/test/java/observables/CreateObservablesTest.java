package observables;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.HashMap;
import java.util.Map;

public class CreateObservablesTest {

    @Test
    public void justTest() {
        Observable
                .just(1, 2, 3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    // the create() below simulate the just() above
    @Test
    public void createTest() {
        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        try {
                            if (!subscriber.isUnsubscribed()) {
                                for (int i = 1; i <= 3; i++) {
                                    subscriber.onNext(i);
                                }
                                subscriber.onCompleted();
                            }
                        } catch (Exception ex) {
                            subscriber.onError(ex);
                        }
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    @Test
    public void loadDocumentsInParallelTest() {
        Map<String, Observable<MyDocument>> bucket = new HashMap<>();
        bucket.put("doc1", Observable.just(new MyDocument("document 1")));
        bucket.put("doc2", Observable.just(new MyDocument("document 2")));
        bucket.put("doc3", Observable.just(new MyDocument("document 3")));

        System.out.println("# Loads 3 documents in parallel");
        Observable
                .just("doc1", "doc2", "doc3")
                .flatMap(new Func1<String, Observable<MyDocument>>() {
                    @Override
                    public Observable<MyDocument> call(String id) {
                        System.out.println(Thread.currentThread().getName());
                        return bucket.get(id);
                    }
                }).subscribe(new Action1<MyDocument>() {
            @Override
            public void call(MyDocument document) {
                System.out.println(Thread.currentThread().getName() + " got: " + document.getName());
            }
        });

        System.out.println("# Loads one document");
        bucket
                .get("doc1")
                .subscribe(new Action1<MyDocument>() {
                    @Override
                    public void call(MyDocument document) {
                        System.out.println(Thread.currentThread().getName() + " got: " + document.getName());
                    }
                });
    }
}
