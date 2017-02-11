package com.company.app;

import rx.Observable;
import rx.Subscriber;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

class HttpGetRequest implements Observable.OnSubscribe<Response>{
    private String url;
    private int expectedStatusCode;

    public HttpGetRequest(String url, int expectedStatusCode){
        this.url = url;
        this.expectedStatusCode = expectedStatusCode;
    }

    public void call(Subscriber<? super Response> subscriber) {
        Response response = ClientBuilder.newClient()
                .target(url)
                .request()
                .get();

        if(response.getStatus() != expectedStatusCode){
            System.err.println(response.getStatus());
            subscriber.onError(new RuntimeException());
            return;
        }

        subscriber.onNext(response);
        subscriber.onCompleted();

    }
}

class HttpPostRequest implements Observable.OnSubscribe<Response>{
    private String url;
    private int expectedStatusCode;

    public HttpPostRequest(String url, int expectedStatusCode){
        this.url = url;
        this.expectedStatusCode = expectedStatusCode;
    }

    public void call(Subscriber<? super Response> subscriber) {
        Response response = ClientBuilder.newClient()
                .target(url)
                .request()
                .get();

        if(response.getStatus() != expectedStatusCode){
            System.err.println(response.getStatus());
            subscriber.onError(new RuntimeException());
            return;
        }

        subscriber.onNext(response);
        subscriber.onCompleted();

    }
}

public class App
{
    public static void main( String[] args )
    {
        simple_jersey_client_test();

        rx_jersey_client_test();

    }

    private static void rx_jersey_client_test() {
        Observable<Response> observable = Observable
                .create(new HttpGetRequest("http://localhost:8080/contacts", 200))
                .retry(6); // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                v -> {
                    v.bufferEntity();
                    String result = v.readEntity(String.class);
                    System.out.println(result);
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
