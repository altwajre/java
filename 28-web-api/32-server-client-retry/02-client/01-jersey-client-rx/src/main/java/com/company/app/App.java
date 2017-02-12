package com.company.app;

import org.json.simple.JSONObject;
import rx.Observable;
import rx.Subscriber;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

abstract class HttpRequest implements Observable.OnSubscribe<Response> {
    protected String url;
    protected int expectedStatusCode;

    public HttpRequest(String url, int expectedStatusCode) {
        this.url = url;
        this.expectedStatusCode = expectedStatusCode;
    }

    public void call(Subscriber<? super Response> subscriber) {

        Response response = request();

        if (response.getStatus() != expectedStatusCode) {
            System.err.println(response.getStatus());
            subscriber.onError(new RuntimeException());
            return;
        }

        subscriber.onNext(response);
        subscriber.onCompleted();

    }

    abstract protected Response request();
}

class HttpGetRequest extends HttpRequest {

    public HttpGetRequest(String url, int expectedStatusCode) {
        super(url, expectedStatusCode);
    }

    @Override
    protected Response request() {
        return ClientBuilder.newClient()
                .target(url)
                .request()
                .get();
    }
}

class HttpDeleteRequest extends HttpRequest {

    public HttpDeleteRequest(String url, int expectedStatusCode) {
        super(url, expectedStatusCode);
    }

    @Override
    protected Response request() {
        return ClientBuilder.newClient()
                .target(url)
                .request()
                .delete();
    }
}

class HttpPostRequest extends HttpRequest {

    private JSONObject body;

    public HttpPostRequest(String url, JSONObject body, int expectedStatusCode) {
        super(url, expectedStatusCode);

        this.body = body;
    }

    @Override
    protected Response request() {

        return ClientBuilder.newClient()
                .target(url)
                .request()
                .post(Entity.entity(body, MediaType.APPLICATION_JSON));
    }
}

class HttpPutRequest extends HttpRequest {

    private JSONObject body;

    public HttpPutRequest(String url, JSONObject body, int expectedStatusCode) {
        super(url, expectedStatusCode);

        this.body = body;
    }

    @Override
    protected Response request() {

        return ClientBuilder.newClient()
                .target(url)
                .request()
                .put(Entity.entity(body, MediaType.APPLICATION_JSON));
    }
}

public class App {
    static int id = 18;

    public static void main(String[] args) {
//        simple_jersey_client_test();

        retry_getAll_test();

        retry_post_test();

        retry_getAll_test();

        retry_put_test();

        retry_get_test();

        retry_delete_test();

        retry_getAll_test();
    }

    private static void retry_delete_test() {
        System.out.println("#retry_delete_test");
        Observable<Response> observable = Observable
                .create(new HttpDeleteRequest("http://localhost:8080/contacts/" + id, 204))
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    System.out.println(response);
                },
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }


    private static void retry_put_test() {
        System.out.println("#retry_put_test");
        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("name", "Put_Will");

        Observable<Response> observable = Observable
                .create(new HttpPutRequest("http://localhost:8080/contacts/" + id, body, 200))
                .retry(); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    System.out.println(response);
                },
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }

    private static void retry_post_test() {
        System.out.println("#retry_post_test");
        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("name", "Will");

        Observable<Response> observable = Observable
                .create(new HttpPostRequest("http://localhost:8080/contacts", body, 201))
                .retry(); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    // createdRecordUrl=http://localhost:8080/18
                    String createdRecordUrl = v.getLocation().toString();
                    System.out.println(createdRecordUrl);
                    System.out.println(response);
                },
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }

    private static void retry_getAll_test() {
        System.out.println("#retry_getAll_test");
        Observable<Response> observable = Observable
                .create(new HttpGetRequest("http://localhost:8080/contacts", 200))
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    System.out.println(response);
                },
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }

    private static void retry_get_test() {
        System.out.println("#retry_get_test");
        Observable<Response> observable = Observable
                .create(new HttpGetRequest("http://localhost:8080/contacts/" + id, 200))
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String response = v.readEntity(String.class);
                    System.out.println(response);
                },
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }


    private static void simple_jersey_client_test() {
        Response response = ClientBuilder.newClient()
                .target("http://localhost:8080/contacts")
                .request()
                .get();
        System.out.println(response.getStatus());
        response.bufferEntity();
        String result = response.readEntity(String.class);
        System.out.println(result);
    }
}
