package observables;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.observables.BlockingObservable;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BlockingObservablesTest {
    // The following code won't print anything because main thread exits before the background thread
    static int counter1 = 0;
    @Test
    public void asyncWithoutLatchTest(){
        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println(Thread.currentThread().getName() + " got: " + ++counter1);
                    }
                });

        System.out.println(Thread.currentThread().getName() + " thread done!");
    }

    // Add a CountDownLatch, which allows you to synchronize between different threads.
    // Always use a CountDownLatch instead of Thread.sleep() between threads.
    // Thread.sleep() does not synchronize anything, it just keeps one thread alive for a specific amount of time.
    // If the actual calls take less time you are wasting time, and if it takes longer you won't get the desired effect.
    static int counter2 = 0;
    @Test
    public void fromAsyncToSyncTest(){

        final CountDownLatch latch = new CountDownLatch(3);
        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println(Thread.currentThread().getName() + " got: " + ++counter2);
                        latch.countDown();
                    }
                });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " thread done!");
    }

    // The following code won't print anything because main thread exits before the background thread
    static int counter3 = 0;
    @Test
    public void asyncWithoutBlockTest(){
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        observable.forEach(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(Thread.currentThread().getName() + " got: " + ++counter3);
            }
        });
        System.out.println(Thread.currentThread().getName() + " thread done!");
    }

    // The following code will run forever
    static int counter4 = 0;
    @Test
    public void asyncWithBlockTest(){
        BlockingObservable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).toBlocking();
        observable.forEach(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(Thread.currentThread().getName() + " got: " + ++counter4);
            }
        });
        System.out.println(Thread.currentThread().getName() + " thread done!");
    }

    // The following code runs 5 times
    static int counter5 = 0;
    @Test
    public void asyncWithBlockTake5Test(){
        Observable
                .interval(1, TimeUnit.SECONDS)
                .take(5)
                .toBlocking()
                .forEach(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println(Thread.currentThread().getName() + " got: " + ++counter5);
                    }
                });
        System.out.println(Thread.currentThread().getName() + " thread done!");
    }

    @Test
    public void singleTest(){
        Integer value = Observable
                .just(1)
                .toBlocking()
                .single();
        System.out.println(value);
    }

    // exception occurs when single more items
    @Test
    public void singleMoreItemsTest(){
        Integer value = Observable
                .just(1, 2)
                .toBlocking()
                .single();
        System.out.println(value);
    }

    // exception occurs when single empty Observable
    @Test
    public void singleEmptyTest(){
        Object single = Observable
                .empty()
                .toBlocking()
                .single();
        System.out.println(single);
    }

    // exception occurs when singleOrDefault more items
    @Test
    public void singleOrDefaultMoreItemsTest(){
        Integer value = Observable
                .just(1, 2)
                .toBlocking()
                .singleOrDefault(8);
        System.out.println(value);
    }

    @Test
    public void singleOrDefaultEmptyTest(){
        Object single = Observable
                .empty()
                .toBlocking()
                .singleOrDefault(8);
        System.out.println(single);
    }

    @Test
    public void toListTest(){
        List<Integer> list = Observable
                .just(1, 2, 3)
                .toList()
                .toBlocking()
                .single();
        System.out.println(list);
    }
}
