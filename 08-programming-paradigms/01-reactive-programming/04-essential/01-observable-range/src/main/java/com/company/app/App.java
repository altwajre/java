package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
        Observable<Integer> range = Observable.range(10, 5);
        range.subscribe(s -> System.out.println(s));
    }
}
/*
output:
10
11
12
13
14
 */
