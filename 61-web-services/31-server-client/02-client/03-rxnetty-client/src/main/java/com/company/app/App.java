package com.company.app;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;
import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
        // TODO: figure out making following code to work.
        Observable<ByteBuf> observable = HttpClient
                .newClient("172.24.44.32", 8080)
                .createGet("/contacts")
                .flatMap(HttpClientResponse::getContent);

        observable
                .map(bb -> bb.toString())
                .subscribe(System.out::println);

    }
}
