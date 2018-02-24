package com.company.app;

import rx.Observable;
import rx.Single;
import rx.schedulers.Schedulers;

public class App
{
    public static void main( String[] args )
    {
        mergeWith_test();

        merge_test();
    }

    private static void merge_test() {
        System.out.println("#merge_test");
        Single<String> s1 = getDataAsSingle(1);
        Single<String> s2 = getDataAsSingle(2);

//        Observable<String> o3 = Single.merge(s1, s2);
        Observable<String> o3 = s1.mergeWith(s2);
        o3.subscribe(System.out::println);
    }
/*
output:
#merge_test
Done: 1
Done: 2
 */

    static Single<String> getDataAsSingle(int i){
        return Single.just("Done: " + i);
    }

    private static void mergeWith_test() {
        System.out.println("#mergeWith_test");
        Observable<String> a_merge_b = getDataA().mergeWith(getDataB());
        a_merge_b
                .subscribe(System.out::println);
    }
/*
output:
#mergeWith_test
DataA
or
DataB
 */

    static Single<String> getDataA() {
        return Single
                .<String>create(o -> o.onSuccess("DataA"))
                .subscribeOn(Schedulers.io());
    }

    static Single<String> getDataB() {
        return Single.just("DataB")
                .subscribeOn(Schedulers.io());
    }
}
