package com.company.app;

import rx.Observable;
import rx.schedulers.Timestamped;

import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        List<Number> list = Arrays.asList(3, 2);
        Observable<Timestamped<Number>> observable = Observable
                .from(list)
// timestamp() operator adds a timestamp to each emitted value by transforming it into an instance of the Timestamped<T> class.
// This is helpful if, for example, we want to log the output of an Observable
                .timestamp();
        observable.subscribe(
                (v) -> System.out.println(v),
                (e) -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
}
/*
output:
Timestamped(timestampMillis = 1486096263147, value = 3)
Timestamped(timestampMillis = 1486096263151, value = 2)
Completed!
 */
