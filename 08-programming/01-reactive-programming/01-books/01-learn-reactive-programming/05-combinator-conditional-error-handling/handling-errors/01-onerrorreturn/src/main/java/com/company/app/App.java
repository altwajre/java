package com.company.app;

import rx.Observable;

public class App
{
    public static void main( String[] args )
    {
// The Integer::parseInt method will succeed in converting the string 1 and 2 to Integer values,
// but it will fail on three with a NumberFormatException exception.
// This exception will be passed to the onErrorReturn() method, which will return the number -1.
// The numbers Observable instance will emit the number - 1 and complete. So the output will be 1, 2, -1, OnCompleted notification.
        Observable<Integer> numbers = Observable
                .just("1", "2", "three", "4", "5")
                .map(Integer::parseInt)
                .onErrorReturn(e -> -1);

        numbers.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Completed!")
        );
    }
}
/*
output:
1
2
-1
Completed!
 */
