package com.company.app;

import rx.Observable;

class House {
    public String getItem(int id) {
        return "ITEM_" + id;
    }
}

// http://tomstechnicalblog.blogspot.com/2015/10/understanding-observables.html
public class App {
    public static void main(String[] args) {

        // Mover 1 on the far left is the source Observable. He creates the emissions by picking items out of the house.
        // He then calls onNext() on Mover 2 who does a map() operation. When his onNext() is called he takes that item
        // and puts it in a Box. Then he calls onNext() on Mover 3 who is the final Subscriber who loads the box on the truck.
        Observable<String> mover1 = Observable
                .<String>create(s -> {
                    new Thread(() -> {
                        try{
                            House house = new House();
                            s.onNext(house.getItem(1));
                            s.onNext(house.getItem(2));
                            s.onCompleted();
                        }
                        catch (Exception e){
                            s.onError(e);
                        }
                    }).start();
                })
                .doOnNext(s -> System.out.println("\nget " + s + " from house"))
                .retry();

        Observable<String> mover2 = mover1
                .map(item -> {
                    putInBox(item);
                    return "BOX";
                });

        mover2
                .subscribe(box -> putInTruck(box));
    }

    static void putInBox(String item) {
        System.out.println("put '" + item + "' in box");
    }

    static void putInTruck(String box) {
        System.out.println("put '" + box + "' in truck");
    }
}
/*
output:
put ITEM_1 in box
put BOX has ITEM_1 in truck
put ITEM_2 in box
put BOX has ITEM_2 in truck
 */
