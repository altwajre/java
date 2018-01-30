package com.company.app;

import rx.Observable;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.function.Consumer;

class Callback {
    private Consumer<String> onResponse = x -> {
    };
    private Consumer<Exception> onFailure = x -> {
    };

    Callback onResponse(Consumer<String> consumer) {
        System.out.println("*Callback.onResponse() is invoked to hookup callback");
        this.onResponse = consumer;
        return this;
    }

    Callback onFailure(Consumer<Exception> consumer) {
        this.onFailure = consumer;
        return this;
    }

    public Consumer<String> getOnResponse() {
        return onResponse;
    }

    public Consumer<Exception> getOnFailure() {
        return onFailure;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Observable<Object> observable = Observable.create(s -> {
            int id = 2;
            getDataAsync(id)
                    .onResponse(v -> {
                        s.onNext(v);
                        s.onCompleted();
                    })
                    .onFailure(e -> s.onError(e));
        });

        observable.subscribe(v -> System.out.println("value=" + v));

        System.out.println("Waiting...");

        Thread.sleep(2000);

        System.out.println("END");
    }

    private static Callback getDataAsync(int id) {
        final Callback callback = new Callback();
        new Thread(() -> {

            Response response = ClientBuilder.newClient()
                    .target("http://localhost:8080/contacts/" + id)
                    .request()
                    .get();
            response.bufferEntity();
            String result = response.readEntity(String.class);

            callback.getOnResponse().accept(result);
        }).start();
        return callback;
    }
}
/*
output:
*Callback.onResponse() is invoked to hookup callback
Waiting...
value={"id":2,"name":"Dick"}
END
 */
