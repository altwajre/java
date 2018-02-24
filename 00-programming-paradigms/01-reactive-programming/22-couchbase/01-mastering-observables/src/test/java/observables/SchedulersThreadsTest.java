package observables;

import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

public class SchedulersThreadsTest {

    @Test
    public void noSchedulerTest(){
        Observable
                .just("a", "bb", "ccc")
                .map(s -> {
                    System.out.println("map() - " + Thread.currentThread().getName());
                    return s.length();
                })
                .subscribe(i -> System.out.println("subscribe() - Got: " + i + " " + Thread.currentThread().getName()));
    }
    /*
    output:
    map() - main
    subscribe() - Got: 1 main
    map() - main
    subscribe() - Got: 2 main
    map() - main
    subscribe() - Got: 3 main
     */

    @Test
    public void subscribeOnComputationSchedulerTest(){
        Observable
                .just("a", "bb", "ccc")
                .map(s -> {
                    System.out.println("map() - " + Thread.currentThread().getName());
                    return s.length();
                })
                .subscribeOn(Schedulers.computation())
                .subscribe(i -> System.out.println("subscribe() - Got: " + i + " " + Thread.currentThread().getName()));
    }
    /*
    output:
    map() - RxComputationScheduler-1
    subscribe() - Got: 1 RxComputationScheduler-1
    map() - RxComputationScheduler-1
    subscribe() - Got: 2 RxComputationScheduler-1
    map() - RxComputationScheduler-1
    subscribe() - Got: 3 RxComputationScheduler-1
     */

    @Test
    public void observeOnComputationSchedulerTest(){
        Observable
                .just("a", "bb", "ccc")
                .map(s -> {
                    System.out.println("map() - " + Thread.currentThread().getName());
                    return s.length();
                })
                .observeOn(Schedulers.computation())
                .subscribe(i -> System.out.println("subscribe() - Got: " + i + " " + Thread.currentThread().getName()));
    }
    /*
    output:
    map() - main
    map() - main
    map() - main
    subscribe() - Got: 1 RxComputationScheduler-1
    subscribe() - Got: 2 RxComputationScheduler-1
    subscribe() - Got: 3 RxComputationScheduler-1
     */

}
