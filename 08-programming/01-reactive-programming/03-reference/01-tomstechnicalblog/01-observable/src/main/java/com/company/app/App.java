package com.company.app;

import rx.Observable;

// http://tomstechnicalblog.blogspot.com/2015/10/understanding-observables.html
public class App
{
    public static void main( String[] args )
    {
        Observable<String> mover1 = Observable.<String>create(s -> {
            String item = "ITEM";
            s.onNext(item + "_1");
            s.onNext(item + "_2");
            s.onCompleted();
        }).retry();

        Observable<String> mover2 = mover1.map(item -> {
            System.out.println("put " + item + " in box");
            return "BOX has " + item;
        });

        mover2.subscribe(box -> System.out.println("put " + box + " in truck"));
    }
}
/*
output:
put ITEM_1 in box
put BOX has ITEM_1 in truck
put ITEM_2 in box
put BOX has ITEM_2 in truck
 */
