package com.company.app;

import rx.Observable;
import rx.Subscription;

import java.util.Arrays;
import java.util.List;

public class App
{
    public static <T> Subscription subscribePrint(Observable<T> observable,
                                                  String name) {
        return observable.subscribe(
                (v) -> System.out.println(Thread.currentThread().getName()
                        + "|" + name + ": " + v),
                (e) -> {
                    System.err.println("Error from " + name + ":");
                    System.err.println(e);
                },
                () -> System.out.println(name + " ended!"));
    }

    public static void main( String[] args )
    {
        test_group_keyselector();

        test_group_keyselector_elementselector();
    }

    private static void test_group_keyselector_elementselector() {
        List<String> albums = Arrays.asList(
                "The Piper at the Gates of Dawn",
                "A Saucerful of Secrets",
                "More", "Ummagumma",	"Atom Heart Mother",
                "Meddle", "Obscured by Clouds",
                "The Dark Side of the Moon",
                "Wish You Were Here", "Animals", "The Wall"
        );
        Observable
                .from(albums)
                .groupBy(
                        album -> album.replaceAll("[^mM]", "").length(),
                        album -> album.replaceAll("[mM]", "*")
                ).subscribe(obs -> subscribePrint(obs, obs.getKey() + " occurences of 'm'"));
    }
/*
output:
main|0 occurences of 'm': The Piper at the Gates of Dawn
main|0 occurences of 'm': A Saucerful of Secrets
main|1 occurences of 'm': *ore
main|4 occurences of 'm': U**agu**a
main|2 occurences of 'm': Ato* Heart *other
main|1 occurences of 'm': *eddle
main|0 occurences of 'm': Obscured by Clouds
main|1 occurences of 'm': The Dark Side of the *oon
main|0 occurences of 'm': Wish You Were Here
main|1 occurences of 'm': Ani*als
main|0 occurences of 'm': The Wall
0 occurences of 'm' ended!
1 occurences of 'm' ended!
2 occurences of 'm' ended!
4 occurences of 'm' ended!
 */

    private static void test_group_keyselector() {
        List<String> albums = Arrays.asList(
                "This is awesome",
                "Tom",
                "Hello world",
                "Will",
                "looks good"
        );
        Observable
                .from(albums)
                .groupBy(album -> album.split(" ").length)
                .subscribe(obs -> subscribePrint(obs, obs.getKey() + " word(s)"));
    }
}
