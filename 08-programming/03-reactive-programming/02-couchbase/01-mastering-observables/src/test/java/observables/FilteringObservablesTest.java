package observables;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class FilteringObservablesTest {

    @Test
    public void filterTest(){
        Observable
                .just(1, 2, 3, 4)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 2;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }
    /*
    output:
    3
    4
     */

    // take only the first 2 values emitted
    @Test
    public void takeTest(){
        Observable
                .just(1, 2, 3, 4)
                .take(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    @Test
    public void firstTest(){
        Observable
                .just(1, 2, 3, 4)
                .first()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }
    /*
    output:
    1
     */

    @Test
    public void lastTest(){
        Observable
                .just(1, 2, 3, 4)
                .last()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }
    /*
    output:
    4
     */

    @Test
    public void distinctTest(){
        Observable
                .just(1, 2, 1, 3, 4, 2)
                .distinct()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }
    /*
    output:
    1
    2
    3
    4
     */

}
