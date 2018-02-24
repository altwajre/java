package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
        Observable.create(pub -> {
            pub.onNext("Hello World from rxjava");
            pub.onCompleted();
        }).subscribe(sub -> {
            System.out.println(sub);
        });
    }
}
/*
output:
Hello World from rxjava
 */
