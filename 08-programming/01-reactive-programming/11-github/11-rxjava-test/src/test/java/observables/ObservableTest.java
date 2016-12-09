package observables;

import org.junit.Assert;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.ConnectableObservable;
import rx.schedulers.TestScheduler;
import rx.subscriptions.BooleanSubscription;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ObservableTest {

    @Test
    public void fromArrayTest(){

        String[] items = new String[] {"one", "two", "three"};
        Observable<String> observable = Observable.from(items);
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void fromListTest(){
        ArrayList<String> items = new ArrayList<>();
        items.add("1");
        items.add("2");
        items.add("3");
        Observable<String> observable = Observable.from(items);
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Hi " + s);
            }
        });
    }

    @Test
    public void justArgs3Test(){
        Observable<String> observable = Observable.just("one", "two", "three");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("hi " + s);
            }
        });
    }

    @Test
    public void justArgs1Test(){
        Observable<String> observable = Observable.just("one");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("hi " + s);
            }
        });
    }

    @Test
    public void createTest(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onNext("22");
                subscriber.onNext("333");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void countFewItemsTest(){
        Observable<String> observable = Observable.just("a", "b", "c", "d");
        observable.count().subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void countZeroItemsTest(){
        Observable<String> observable = Observable.empty();
        observable.count().subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void countErrorTest(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onError(new RuntimeException());
            }
        });
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void takeFirstWithPredicateTest(){
        Observable<Integer> observable = Observable.just(1, 3, 4, 5, 6, 3);
        observable.takeFirst(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer value) {
                return value % 2 == 0;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void takeFirstWithPredicateNoMatchingTest(){
        Observable<Integer> observable = Observable.just(1, 3, 7, 5);
        observable.takeFirst(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer value) {
                return value % 2 == 0;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void takeFirstTest(){
        Observable<Integer> observable = Observable.just(1, 2, 3);
        observable.take(1).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void takeFirstWithEmptyTest(){
        Observable<Integer> observable = Observable.empty();
        observable.take(1).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void rangeTest(){
        Observable<Integer> observable = Observable.range(8, 5);
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void reduceTest(){
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);
        observable.reduce(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer t1, Integer t2) {
                return t1 + t2;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void reduceWithInitialValueTest(){
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);
        observable.reduce(8, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer t1, Integer t2) {
                return t1 + t2;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void onSubscribeFailsTest(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                throw new RuntimeException("bad impl");
            }
        });
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void materializeDematerializeChainingTest(){
        Observable<Integer> observable = Observable.just(1);
        Observable<Integer> chained = observable.materialize().dematerialize();
        chained.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void observableSyncTest(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onNext("2");
                subscriber.onNext("three");
                subscriber.onNext("4");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void observableErrorInSyncTest(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onNext("2");
                throw new NumberFormatException();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void observableAsyncTest(){
        final CountDownLatch latch = new CountDownLatch(1);
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                final BooleanSubscription booleanSubscription = new BooleanSubscription();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            if(!booleanSubscription.isUnsubscribed()){
                                subscriber.onNext("1");
                                subscriber.onNext("2");
                                subscriber.onNext("three");
                                subscriber.onNext("4");
                                subscriber.onCompleted();
                            }
                        }
                        finally {
                            latch.countDown();
                        }
                    }
                }).start();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });

        // wait for async sequence to complete
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void publishAsyncTest(){

        final CountDownLatch latch = new CountDownLatch(1);
        ConnectableObservable<String> connectableObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        subscriber.onNext("1");
                        subscriber.onNext("2");
                        subscriber.onCompleted();
                        latch.countDown();
                    }
                }).start();
            }
        }).publish();

        connectableObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Subscription subscription = connectableObservable.connect();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription.unsubscribe();
    }

    @Test
    public void publishAsyncMultipleSubscribeTest(){

        final CountDownLatch latch = new CountDownLatch(1);
        ConnectableObservable<String> connectableObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        subscriber.onNext("1");
                        subscriber.onNext("2");
                        subscriber.onCompleted();
                        latch.countDown();
                    }
                }).start();
            }
        }).publish();

        Action1<String> subscriptonAction = new Action1<String>() {
            @Override
            public void call(String s) {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " - " + s);
            }
        };

        connectableObservable.subscribe(subscriptonAction);
        connectableObservable.subscribe(subscriptonAction);

        Subscription subscription = connectableObservable.connect();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription.unsubscribe();
    }

    @Test
    public void publishSyncTest(){

        ConnectableObservable<String> connectableObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onNext("2");
                subscriber.onCompleted();
            }
        }).publish();
        Action1<String> subscriptonAction = new Action1<String>() {
            @Override
            public void call(String s) {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " - " + s);
            }
        };

        connectableObservable.subscribe(subscriptonAction);
        connectableObservable.subscribe(subscriptonAction);
        Subscription subscription = connectableObservable.connect();
        subscription.unsubscribe();

    }

    // http://reactivex.io/documentation/operators/replay.html
    @Test
    public void replaySyncTest(){

        ConnectableObservable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("one");
                subscriber.onCompleted();
            }
        }).replay();  // if change it to publish(), the subscriber1 below won't get called

        Subscription subscription = observable.connect();

        Action1<String> subscriber1 = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        observable.subscribe(subscriber1);

        subscription.unsubscribe();

    }

    @Test
    public void replayAsyncTest(){

        final CountDownLatch latch = new CountDownLatch(2);

        ConnectableObservable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext("one");
                        subscriber.onCompleted();
                    }
                }).start();
            }
        }).replay();  // if change it to publish(), the subscriber1 below won't get called

        Subscription subscription = observable.connect();

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
                latch.countDown();
            }
        });

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription.unsubscribe();

    }

    @Test
    public void cacheSyncTest(){

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("one");
                subscriber.onCompleted();
            }
        }).cache();

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

    }

    @Test
    public void cacheAsyncTest(){

        final CountDownLatch latch = new CountDownLatch(2);

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext("one");
                        subscriber.onCompleted();
                    }
                }).start();
            }
        }).cache();

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
                latch.countDown();
            }
        });

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
                latch.countDown();
            }
        });

        try {
            latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void errorThrowAsyncTest(){
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<Throwable> exception = new AtomicReference<>();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try{
                            subscriber.onError(new Error("failure"));
                        }
                        catch (Throwable e){
                            exception.set(e);
                        }

                        latch.countDown();
                    }
                }).start();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });

        try {
            latch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(exception.get());

    }

    @Test
    public void ofTypeTest(){
        Observable<String> observalbe = Observable.just(1, "abc", false, 2L).ofType(String.class);

        observalbe.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void ofTypeWithPolymorphismTest(){
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(1);
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(2);

        Observable<List> observable = Observable.just(l1, l2, "123").ofType(List.class);

        observable.subscribe(new Action1<List>() {
            @Override
            public void call(List list) {
                System.out.println(list);
            }
        });
    }

    @Test
    public void containsTest(){
        Observable<Boolean> observable = Observable.just("a", "b", null).contains("b");
        observable.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println(aBoolean);
            }
        });
    }

    @Test
    public void containsNotExistTest(){
        Observable<Boolean> observable = Observable.just("a", "b", null).contains("c");
        observable.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println(aBoolean);
            }
        });
    }

    @Test
    public void containsNullTest(){
        Observable<Boolean> observable = Observable.just("a", "b", null).contains(null);
        observable.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println(aBoolean);
            }
        });
    }

    @Test
    public void containsWithEmptyObservableTest(){
        Observable<Boolean> observable = Observable.empty().contains("a");
        observable.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println(aBoolean);
            }
        });
    }

    @Test
    public void ignoreElementsTest(){
        // subscribe Action1.call() was never called
        Observable<Integer> observable = Observable.just(1, 2, 3).ignoreElements();
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void startWithSchedulerTest(){
        TestScheduler scheduler = new TestScheduler();
    }
}
