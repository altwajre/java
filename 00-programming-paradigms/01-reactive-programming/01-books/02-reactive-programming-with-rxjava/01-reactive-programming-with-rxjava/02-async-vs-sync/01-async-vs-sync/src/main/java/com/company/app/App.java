package com.company.app;

import rx.Observable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

class Callback {
    private Consumer<String> onResponse = x -> {};
    private Consumer<Exception> onFailure = x -> {};

    Callback onResponse(Consumer<String> consumer){
        System.out.println("*Callback.onResponse() is invoked to hookup callback");
        this.onResponse = consumer;
        return this;
    }

    Callback onFailure(Consumer<Exception> consumer){
        this.onFailure = consumer;
        return this;
    }

    public Consumer<String> getOnResponse() {return onResponse;}
    public Consumer<Exception> getOnFailure() {return onFailure;}
}

public class App
{
    static Map<Integer, String> cache = new ConcurrentHashMap<>();

    public static void main( String[] args )
    {
        // In Memory Data - book
        sync_test();

        // async - book
        async_test();

        System.out.println("cache: " + cache);
    }

    private static Callback getDataAsync(int key) {
        final Callback callback = new Callback();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("*App.getDataAsync() simulates http request");
            // Consumer.accept() invokes callback
            String value = key + "_async";
            callback.getOnResponse().accept(value);
        }).start();
        return callback;
    }

    private static void async_test() {
        System.out.println("#async_test");
        Integer key = 2;
        Observable<Object> observable = Observable.create(s -> {
            String value = getFromCache(key);
            if(value != null){
                s.onNext(value);
                s.onCompleted();
            }
            else {
                getDataAsync(key)
                .onResponse(v -> {
                    putInCache(key, v);
                    s.onNext(v);
                    s.onCompleted();
                })
                .onFailure(e -> s.onError(e));
            }
        });

        observable.subscribe(v -> System.out.println("value=" + v));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void putInCache(Integer key, String value) {
        cache.put(key, value);
    }

    private static String getFromCache(int key) {
        return cache.get(key);
    }

    private static void sync_test() {
        System.out.println("#sync_test");
        Integer key = 1;
        cache.put(key, key + "_sync");

        Observable<Object> observable = Observable.create(s -> {
            s.onNext(cache.get(key));
            s.onCompleted();
        });

        observable.subscribe(v -> System.out.println("value=" + v));
    }
}
/*
output:
#sync_test
value=1_sync
#async_test
*Callback.onResponse() is invoked to hookup callback
*App.getDataAsync() simulates http request
value=2_async
cache: {1=1_sync, 2=2_async}
 */
