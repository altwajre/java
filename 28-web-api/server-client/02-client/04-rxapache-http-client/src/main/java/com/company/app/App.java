package com.company.app;

import com.google.gson.Gson;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.HttpAsyncClient;
import rx.Observable;
import rx.apache.http.ObservableHttp;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class App {
    static Map<String, Set<Map<String, Object>>> cache = new ConcurrentHashMap<>();

    static Observable<Map<String, Object>> fromCache(String url) {
        return Observable.from(getCache(url)).defaultIfEmpty(null)
                .flatMap(json -> (json == null) ? Observable.never() : Observable.just(json))
                .doOnNext(json -> json.put("json_cached", true));
    }

    static Set<Map<String, Object>> getCache(String url) {
        if (!cache.containsKey(url)) {
            cache.put(url, new HashSet<Map<String, Object>>());
        }
        return cache.get(url);
    }

    static Observable<Map> requestJson(HttpAsyncClient client,
                                       String url) {
        Observable<String> rawResponse = ObservableHttp
                .createGet(url, client)
                .toObservable()
                .flatMap(resp -> resp.getContent()
                        .map(bytes -> new String(bytes, java.nio.charset.StandardCharsets.UTF_8)))
                .retry(5)
                .cast(String.class)
                .map(String::trim)
                .doOnNext(resp -> getCache(url).clear());

        Observable<String> objects = rawResponse
                .filter(data -> data.startsWith("{"))
                .map(data -> "[" + data + "]");

        Observable<String> arrays = rawResponse
                .filter(data -> data.startsWith("["));

        Observable<Map> response = arrays.ambWith(objects)
                .map(data -> new Gson().fromJson(data, List.class)).flatMapIterable(list -> list)
                .cast(Map.class)
                .doOnNext(json -> getCache(url).add((Map<String, Object>) json));

        return Observable.amb(fromCache(url), response);
    }

    public static void main(String[] args) {

        try (CloseableHttpAsyncClient client = HttpAsyncClients.createDefault()) {

            client.start();

            CountDownLatch latch = new CountDownLatch(1);

            Observable<Map> response = requestJson(client, "http://localhost:8080/contacts");
            Observable<Map> observable = response.finallyDo(() -> latch.countDown());

            observable.subscribe(
                    v -> System.out.println(v),
                    e -> System.err.println(e),
                    () -> System.out.println("Completed!")
            );

            try {
                latch.await();
            } catch (InterruptedException e) {
            }


        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
/*
output:
{id=1.0, name=Tom}
{id=2.0, name=Dick}
{id=3.0, name=Harry}
Completed!
 */
