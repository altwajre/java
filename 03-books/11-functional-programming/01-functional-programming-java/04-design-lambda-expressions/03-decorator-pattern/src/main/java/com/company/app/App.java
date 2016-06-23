package com.company.app;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

class Camera{
    private Function<Color, Color> filter;
    public Camera() {
        setFilters();
    }
    public Color capture(final Color inputColor){
        final Color processedColor = filter.apply(inputColor);
        // ... more processing of color ...
        return processedColor;
    }
    // varargs: it may be passed as an array or as a sequence of arguments, it can be used only in the final argument position.
    public void setFilters(final Function<Color, Color>... filters){
        filter = Stream.of(filters)
                // compose combines or chains multiple Functions
                .reduce((filter, next) -> filter.compose(next))
                // The Function interface has an identity static method the does the same as .orElse(color -> color)
                .orElseGet(Function::identity); // .orElse(color -> color);
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println("#decoratorPattern");
        decoratorPattern();

        System.out.println("#functionComposition");
        functionComposition();
    }

    private static void decoratorPattern() {
        final Camera camera = new Camera();
        final Consumer<String> printCaptured =
                s -> System.out.printf("with %s: %s\n", s, camera.capture(new Color(200, 100, 200)));
        printCaptured.accept("no filter");

        camera.setFilters(Color::brighter);
        printCaptured.accept("brighter filter");

        camera.setFilters(Color::darker);
        printCaptured.accept("darker filter");

        camera.setFilters(Color::brighter, Color::darker);
        printCaptured.accept("brighter & darker filter");
    }

    private static void functionComposition() {
        Function<Integer, Integer> plus1 = e -> e + 1;
        Function<Integer, Integer> time2 = e -> e * 2;
        System.out.println("2 time2 and-then plus1: "+plus1.compose(time2).apply(2));
        System.out.println("2 plus1 and-then time2: "+plus1.andThen(time2).apply(2));
    }
}
/*
output:
#decoratorPattern
with no filter: java.awt.Color[r=200,g=100,b=200]
with brighter filter: java.awt.Color[r=255,g=142,b=255]
with darker filter: java.awt.Color[r=140,g=70,b=140]
with brighter & darker filter: java.awt.Color[r=200,g=100,b=200]
#functionComposition
2 time2 and-then plus1: 5
2 plus1 and-then time2: 6
 */
