package com.company

import rx.Observable;

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.Response

class App {
    static void main(String[] args) {

        println("#retry_getAll_test")

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
        .retry { attempts, error ->
            println ("attempts: ${attempts} - error: ${error}")
            sleep(1000) // delay
            (error instanceof Exception) && attempts < 8
        }
//        .retry(6) // since server fail 5 times, retry(3) will fail.

        observable.subscribe(
                { v ->
                    v.bufferEntity()
                    String response = v.readEntity(String.class)
                    println(response)
                },
                { e -> System.err.println(e) },
                { println("Completed!") }
        )

    }
}
/*
output:
#retry_getAll_test
400
attempts: 1
400
attempts: 2
attempts: 3
400
attempts: 4
400
400
attempts: 5
[{"id":1,"name":"Tom"},{"id":2,"name":"Dick"},{"id":3,"name":"Harry"}]
Completed!
 */

