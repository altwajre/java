package observables;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

import java.util.HashMap;
import java.util.List;
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
    /*
    output:
    Fizz
    1
    2
    Fizz
    4
    Buzz
    Fizz
    7
    8
    Fizz
    Buzz
    11
    Fizz
    13
    14
    Fizz
    16
    17
    Fizz
    19
     */

    @Test
    public void flatMapTest(){

        Map<String, Observable<MyDocument>> bucket = new HashMap<>();
        bucket.put("doc1", Observable.just(new MyDocument("document 1")));
        bucket.put("doc2", Observable.just(new MyDocument("document 2")));
        bucket.put("doc3", Observable.just(new MyDocument("document 3")));

        Observable
                .just("doc1", "doc2", "doc3")
                .flatMap(new Func1<String, Observable<MyDocument>>() {
                    @Override
                    public Observable<MyDocument> call(String id) {
                        System.out.println(Thread.currentThread().getName());
                        return bucket.get(id);
                    }
                })
                .subscribe(new Action1<MyDocument>() {
                    @Override
                    public void call(MyDocument document) {
                        System.out.println(Thread.currentThread().getName() + " got: " + document.getName());
                    }
                });
    }
    /*
    output:
    main
    main got: document 1
    main
    main got: document 2
    main
    main got: document 3
     */

    // scan() applies a function to each value emitted by an Observable
    @Test
    public void scanTest(){

        Observable
                .just(1, 2, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer v1, Integer v2) {
                        return v1 + v2;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("Sum: " + integer);
                    }
                });

    }
    /*
    output:
    Sum: 1
    Sum: 3
    Sum: 6
    Sum: 10
    Sum: 15
     */

    @Test
    public void groupByTest(){
        Observable
                .just(1, 2, 3, 4, 5)
                .groupBy(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Action1<GroupedObservable<Boolean, Integer>>() {
                    @Override
                    public void call(GroupedObservable<Boolean, Integer> grouped) {
                        grouped.toList().subscribe(new Action1<List<Integer>>() {
                            @Override
                            public void call(List<Integer> integers) {
                                System.out.println(integers + " (Even: " + grouped.getKey() + ")");
                            }
                        });
                    }
                });
    }
    /*
    output:
    [1, 3, 5] (Even: false)
    [2, 4] (Even: true)
     */

}
