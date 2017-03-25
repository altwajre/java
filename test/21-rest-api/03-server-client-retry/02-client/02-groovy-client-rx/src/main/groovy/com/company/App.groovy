package com.company

import rx.Observable;

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.Response

class App {
    static void main(String[] args) {

        System.out.println("#retry_getAll_test")

        Observable<Response> observable = Observable
                .<Response> create { s ->
            String url = "http://localhost:8080/contacts"
            Response response = ClientBuilder.newClient()
                    .target(url)
                    .request()
                    .get()

            if (response.getStatus() != 200) {
                System.err.println(response.getStatus())
                s.onError(new RuntimeException())
            }

            s.onNext(response)
            s.onCompleted()
        }
        .retry(6) // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                { v ->
                    v.bufferEntity()
                    String response = v.readEntity(String.class)
                    System.out.println(response)
                },
                { e -> System.err.println(e) },
                { System.out.println("Completed!") }
        )

    }
}
