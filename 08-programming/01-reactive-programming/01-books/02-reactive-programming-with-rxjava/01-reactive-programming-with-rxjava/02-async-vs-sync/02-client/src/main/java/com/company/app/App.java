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
        // In Memory Data
//        sync_test();

        async_test();

    }

    private static void async_test() {
        Integer key = 1;
        Observable<Object> observable = Observable.create(s -> {
            String name = getFromCache(key);
            if(name != null){
                s.onNext(name);
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

        observable.subscribe(System.out::println);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void putInCache(Integer key, String value) {
        cache.put(key, value);
    }

    private static Callback getDataAsync(int key) {
        final Callback callback = new Callback();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("*App.getDataAsync() invoke callback");
            // Consumer.accept() invokes callback
            callback.getOnResponse().accept(key + ":123");
        }).start();
        return callback;
    }

    private static String getFromCache(int key) {
        return cache.get(key);
    }

    private static void sync_test() {
        Integer key = 128;
        cache.put(key, "Phil");

        Observable<Object> observable = Observable.create(s -> {
            s.onNext(cache.get(key));
            s.onCompleted();
        });

        observable.subscribe(System.out::println);
    }
}
