package observables;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

public class CombiningObservablesTest {

    // merge all emitted values by the source observables in the order they arrive
    @Test
    public void mergeTest(){
        Observable<Integer> evens = Observable.just(2, 4, 6, 8, 10);
        Observable<Integer> odds = Observable.just(1, 3, 5, 7, 9);
        Observable
                .merge(evens, odds)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }
    /*
    output:
    2
    4
    6
    8
    10
    1
    3
    5
    7
    9
     */

    // combine two streams in the strictly same order, defined by a function
    @Test
    public void zipTest(){
        Observable<Integer> evens = Observable.just(2, 4, 6, 8, 10);
        Observable<Integer> odds = Observable.just(1, 3, 5, 7, 9);
        Observable
                .zip(evens, odds, (v1, v2) -> v1 + " + " + v2 + " is: " + (v1 + v2))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

    }
    /*
    output:
    2 + 1 is: 3
    4 + 3 is: 7
    6 + 5 is: 11
    8 + 7 is: 15
    10 + 9 is: 19
     */

}
