package observables;

import org.junit.Test;
import rx.Observable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class errorHandlingTest {

    // returns a default value when exception occurs
    @Test
    public void returnsDefaultTest() {
        Observable
                .just("Apples", "Bananas")
                .doOnNext(s -> {
                    throw new RuntimeException("I don't like: " + s);
                })
                .onErrorReturn(throwable -> {
                    System.err.println("Oops: " + throwable.getMessage());
                    return "Default value";
                })
                .subscribe(System.out::println);
    }

    @Test
    public void retryTest() {
        Observable
                .just(1, 2, 3)
                .doOnNext(integer -> {
                    int random = new Random().nextInt(5);
                    System.out.println("random=" + random);
                    if (random % 2 == 0) {
                        throw new RuntimeException("Boo! " + integer);
                    }
                })
                .retry()
                .subscribe(System.out::println);
    }
    /*
    output:
    random=0
    random=4
    random=3
    1
    random=1
    2
    random=1
    3
     */

    @Test
    public void retryWhenTest() {
        Observable
                .range(1, 10)
                .doOnNext(integer -> {
                    if (new Random().nextInt(10) + 1 == 5) {
                        throw new RuntimeException("Boo!");
                    }
                })
                .retryWhen(attempts ->
                        attempts.zipWith(Observable.range(1, 3), (n, i) -> i)
                                .flatMap(i -> {
                                    System.out.println("delay retry by " + i + " second(s)");
                                    return Observable.timer(i, TimeUnit.SECONDS);
                                }))
                .distinct()
                .subscribe(System.out::println);

    }
    /*
    output:
    1
    2
    3
    4
    5
    delay retry by 1 second(s)
     */

}
