package com.company.app;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

class FooException extends RuntimeException {
    public FooException() {
        super("Foo!");
    }
}

class BooException extends RuntimeException {
    public BooException() {
        super("Boo!");
    }
}

class ErrorEmitter implements Observable.OnSubscribe<Integer> {
    private int throwAnErrorCounter = 3;

    @Override
    public void call(Subscriber<? super Integer> subscriber) {
        subscriber.onNext(1);
        subscriber.onNext(2);
        if (throwAnErrorCounter > 2) {
            throwAnErrorCounter--;
            System.out.println("onError: FooException; throwAnErrorCounter=" + throwAnErrorCounter);
            subscriber.onError(new FooException());
            return;
        }
        if (throwAnErrorCounter > 0) {
            throwAnErrorCounter--;
            System.out.println("onError: BooException; throwAnErrorCounter=" + throwAnErrorCounter);
            subscriber.onError(new BooException());
            return;
        }
        subscriber.onNext(3);
        subscriber.onNext(4);
        subscriber.onCompleted();
    }
}

public class App {
    public static void main(String[] args) {
//        test_retry();

        test_retrywhen();

    }

    private static void test_retrywhen() {
        System.out.println("#test_retrywhen");

        // For delayed retries, the retryWhen() operator capable of very complex retrying logic.
        Observable<Integer> observable = Observable
                .create(new ErrorEmitter());

        Observable<Integer> when = observable
                .retryWhen(attempts -> {
                    System.out.println(attempts.getClass().getName());
                    return attempts.flatMap(error -> {
                        if (error instanceof FooException) {
                            return Observable.timer(200L, TimeUnit.MILLISECONDS)
                                    .doOnNext(number -> {
                                        System.err.println("Delaying...");
                                        System.out.println(number + 99);
                                    });
                        }
                        return Observable.error(error);
                    });
                })
                .retry((attempts, error) -> {
                    System.out.println(attempts.getClass().getName() + ": attempts=" + attempts);
                    return (error instanceof BooException) && attempts < 3;
                });

        when.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
output:
#test_retrywhen
rx.Observable
1
2
onError: FooException; throwAnErrorCounter=2
Delaying...
99
1
2
onError: BooException; throwAnErrorCounter=1
java.lang.Integer: attempts=1
rx.Observable
1
2
onError: BooException; throwAnErrorCounter=0
java.lang.Integer: attempts=2
rx.Observable
1
2
3
4
Completed!
 */

    private static void test_retry() {
        System.out.println("#test_retry");
// Because the initial value of the throwAnErrorCounter field is three, it will retry three times,
// and when the counter becomes zero, the Observable instance will complete, OnCompleted notification.
        Observable<Integer> observable = Observable
                .create(new ErrorEmitter())
                .retry();
        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
/*
output:
#test_retry
1
2
onError: FooException; throwAnErrorCounter=2
1
2
onError: BooException; throwAnErrorCounter=1
1
2
onError: BooException; throwAnErrorCounter=0
1
2
3
4
Completed!
 */
}
