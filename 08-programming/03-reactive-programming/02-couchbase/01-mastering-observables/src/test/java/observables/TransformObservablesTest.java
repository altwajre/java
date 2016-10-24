package observables;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TransformObservablesTest {

    @Test
    public void transformToMapTest(){
        Observable
                .interval(10, TimeUnit.MILLISECONDS)
                .take(20)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long input) {
                        if(input % 3 == 0){
                            return "Fizz";
                        }
                        else if(input % 5 == 0){
                            return "Buzz";
                        }
                        return Long.toString(input);
                    }
                })
                .toBlocking()
                .forEach(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    @Test
    public void flatMapTest(){
        Map<String, Observable<String>> bucket = new HashMap<>();
        bucket.put("doc1", Observable.just("document 1"));
        bucket.put("doc2", Observable.just("document 2"));
        bucket.put("doc3", Observable.just("document 3"));

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
}
