package com.company.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.company.app.Memoizer.callMemoized;

/*
https://www.youtube.com/watch?v=ElFrskby_7M

 */
class RodCutterBasic{
    final List<Integer> prices;

    RodCutterBasic(final List<Integer> pricesForLength) {
        this.prices = pricesForLength;
    }
    public int maxProfit(final int length){
        int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
//        System.out.println(profit);
        for(int i = 1; i < length; i++){
            int priceWhenCut = maxProfit(i) + maxProfit(length - i);
            if(profit < priceWhenCut){
                profit = priceWhenCut;
            }
        }
        return profit;
    }
}
class Memoizer{
    public static <T, R> R callMemoized(final BiFunction<Function<T,R>, T, R> function, final T input){
        Function<T, R> memoized = new Function<T, R>() {
            public final Map<T, R> store = new HashMap<>();
            @Override
            public R apply(final T t) {
                R r = store.computeIfAbsent(t, key -> function.apply(this, key));
                return r;
            }
        };
        return memoized.apply(input);
    }
}

class RodCutterMemoized{
    final List<Integer> prices;
    RodCutterMemoized(List<Integer> pricesForLength) {
        this.prices = pricesForLength;
    }
    public int maxProfit(final int rodLength){
        BiFunction<Function<Integer, Integer>, Integer, Integer> compute = (func, length) -> {
            int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
            for(int i = 1; i < length; i++){
                int r1 = func.apply(i);
                int r2 = func.apply(length - 1);
                int priceWhenCut = r1 + r2;
                if(profit < priceWhenCut){
                    profit = priceWhenCut;
                }
            }
            return profit;
        };
        return callMemoized(compute, rodLength);
    }
}
public class App
{
    private static void rodCutterMemoizedTest() {
        final RodCutterMemoized rodCutterMemoized = new RodCutterMemoized(priceValues);
        System.out.println(rodCutterMemoized.maxProfit(5));
        System.out.println(rodCutterMemoized.maxProfit(22));
    }
    static final List<Integer> priceValues = Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
    static void run(final RodCutterBasic rodCutter){
        System.out.println(rodCutter.maxProfit(5));
//        System.out.println(rodCutter.maxProfit(22));
    }
    private static void rodCutterBasicTest() {
        final RodCutterBasic rodCutter = new RodCutterBasic(priceValues);
        run(rodCutter);
    }
    public static void main( String[] args )
    {
        System.out.println("#rodCutterBasicTest");
        rodCutterBasicTest();
        System.out.println("#rodCutterMemoizedTest");
        rodCutterMemoizedTest();
        System.out.println("#functionTest");
        functionTest();
        System.out.println("#mapComputeIfAbsentTest");
        mapComputeIfAbsentTest();
    }

    private static void mapComputeIfAbsentTest() {
        Map<Integer, Integer> cache = new ConcurrentHashMap<>();
        priceValues.forEach(n -> {
            cache.computeIfAbsent(n, key -> {
                System.out.print(n + " ");
                return n * 2;
            });
        });
        System.out.println("");
        cache.forEach((k, v) -> {
            System.out.printf("key=%s,value=%s; ", k, v);
        });
    }

    private static void functionTest() {
        // input one arg String, and return Integer
        Function<String, Integer> function = s -> s.length();
        System.out.println("Tom length="+function.apply("Tom"));

        // input two args String and Integer, and return Integer
        BiFunction<String, Integer, Integer> biFunction = (s, n) -> s.length() + n;
        System.out.println("Tom length+5="+biFunction.apply("Tom", 5));
    }
}
/*
output:
#rodCutterBasicTest
10
#rodCutterMemoizedTest
10
44
#functionTest
Tom length=3
Tom length+5=8
#mapComputeIfAbsentTest
2 1 8 9 15
key=1,value=2; key=2,value=4; key=8,value=16; key=9,value=18; key=15,value=30;
 */
