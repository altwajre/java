package com.company.app;

import org.json.simple.JSONObject;
import rx.Observable;
import rx.schedulers.Schedulers;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// subscribeOn(Scheduler) to kick off tasks in worker threads could cause race condition
public class App {

    static int id = 18;
    public static void main(String[] args) {

        retry_getAll_test();

        retry_post_test();

        retry_getAll_test();

        retry_put_test();

        retry_get_test();

        retry_delete_test();

        retry_getAll_test();

        sleep(3000);

    }

    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void retry_delete_test() {

        System.out.println("#retry_delete_test");

        Observable<Response> observable = Observable
                .<Response>create(s -> {
                    String url = "http://localhost:8080/contacts/" + id;
                    Response response = ClientBuilder.newClient()
                            .target(url)
                            .request()
                            .delete();

                    if (response.getStatus() != 204) {
                        System.err.println(response.getStatus());
                        s.onError(new RuntimeException());
                    }

                    s.onNext(response);
                    s.onCompleted();
                })
                .subscribeOn(Schedulers.io())
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + response);
                },
                e -> System.err.println(Thread.currentThread().getName() + ": " + e),
                () -> System.out.println("Completed!")
        );
    }

    private static void retry_put_test() {

        System.out.println("#retry_put_test");

        Observable<Response> observable = Observable
                .<Response>create(s -> {
                    String url = "http://localhost:8080/contacts/" + id;
                    JSONObject body = new JSONObject();
                    body.put("id", id);
                    body.put("name", "Put_Will");
                    Response response = ClientBuilder.newClient()
                            .target(url)
                            .request()
                            .put(Entity.entity(body, MediaType.APPLICATION_JSON));

                    if (response.getStatus() != 200) {
                        System.err.println(response.getStatus());
                        s.onError(new RuntimeException());
                    }

                    s.onNext(response);
                    s.onCompleted();
                })
                .subscribeOn(Schedulers.io())
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + response);
                },
                e -> System.err.println(Thread.currentThread().getName() + ": " + e),
                () -> System.out.println("Completed!")
        );
    }

    private static void retry_post_test() {

        System.out.println("#retry_post_test");

        Observable<Response> observable = Observable
                .<Response>create(s -> {
                    String url = "http://localhost:8080/contacts";
                    JSONObject body = new JSONObject();
                    body.put("id", id);
                    body.put("name", "Will");
                    Response response = ClientBuilder.newClient()
                            .target(url)
                            .request()
                            .post(Entity.entity(body, MediaType.APPLICATION_JSON));

                    if (response.getStatus() != 201) {
                        System.err.println(response.getStatus());
                        s.onError(new RuntimeException());
                    }

                    s.onNext(response);
                    s.onCompleted();
                })
                .subscribeOn(Schedulers.io())
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    // createdRecordUrl=http://localhost:8080/18
                    String createdRecordUrl = v.getLocation().toString();
                    System.out.println(createdRecordUrl);
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + response);
                },
                e -> System.err.println(Thread.currentThread().getName() + ": " + e),
                () -> System.out.println("Completed!")
        );
    }

    private static void retry_getAll_test() {

        System.out.println("#retry_getAll_test");

        Observable<Response> observable = Observable
                .<Response>create(s -> {
                    String url = "http://localhost:8080/contacts";
                    Response response = ClientBuilder.newClient()
                            .target(url)
                            .request()
                            .get();

                    if (response.getStatus() != 200) {
                        System.err.println(response.getStatus());
                        s.onError(new RuntimeException());
                    }

                    s.onNext(response);
                    s.onCompleted();
                })
                .subscribeOn(Schedulers.io())
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + response);
                },
                e -> System.err.println(Thread.currentThread().getName() + ": " + e),
                () -> System.out.println("Completed!")
        );
    }

    private static void retry_get_test() {

        System.out.println("#retry_get_test");

        Observable<Response> observable = Observable
                .<Response>create(s -> {
                    String url = "http://localhost:8080/contacts/" + id;
                    Response response = ClientBuilder.newClient()
                            .target(url)
                            .request()
                            .get();

                    if (response.getStatus() != 200) {
                        System.err.println(response.getStatus());
                        s.onError(new RuntimeException());
                    }

                    s.onNext(response);
                    s.onCompleted();
                })
                .subscribeOn(Schedulers.io())
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + response);
                },
                e -> System.err.println(Thread.currentThread().getName() + ": " + e),
                () -> System.out.println("Completed!")
        );
    }
}
/*
output:
 */
