package com.company.app;

import org.glassfish.jersey.client.rx.Rx;
import org.glassfish.jersey.client.rx.rxjava.RxObservableInvoker;
import rx.Observable;

public class App
{
    // https://jersey.java.net/documentation/latest/rx-client.html

    public static void main( String[] args )
    {
        // Note: rx request() takes some time to end the program which is ok.
        Observable<String> observable = Rx.newClient(RxObservableInvoker.class)
                .target("http://localhost:8080/contacts")
                .request()
                .rx()
                .get(String.class);
        observable.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );

        System.out.println("DONE");

    }
}
/*
output:
DONE
[{"id":1,"name":"Tom"},{"id":2,"name":"Dick"},{"id":3,"name":"Harry"}]
Completed!
 */
