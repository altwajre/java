package com.company.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class App {
    static final List<BigDecimal> prices = Arrays.asList(
            new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("17"),
            new BigDecimal("20"), new BigDecimal("15"), new BigDecimal("18"),
            new BigDecimal("45"), new BigDecimal("12")
    );

    static void imperative() {
        BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            if (price.compareTo(BigDecimal.valueOf(20)) > 0) {
                totalOfDiscountedPrices =
                        totalOfDiscountedPrices.add(price.multiply(BigDecimal.valueOf(0.9)));
            }
        }
        // (30 + 40) * 0.9 = 67.5
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }
    static void functional(){
        final BigDecimal totalOfDiscountedPrices = prices.stream()
                .filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
                .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }
    private static void testStreamReduce() {
        List<BigDecimal> numbers = Arrays.asList(
                new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
        System.out.println(numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add));

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("bb");
        list.add("ccc");
        list.add("dddd");
        int s = list.stream().map(w -> w.length())
                .mapToInt(Integer::new)
                .sum();
        System.out.println(s);

        Stream<Integer> lengthStream = list.stream()
                .map(w -> w.length());
        int sum = lengthStream.reduce(0, (x, y) -> {
            System.out.println("x="+x);
            System.out.println("y="+y);
            return x + y;
        } );
        System.out.println(sum);
    }
    public static void main(String[] args) {
        System.out.println("imperative");
        imperative();
        System.out.println("functional");
        functional();

        testStreamReduce();
    }
}
/*
output:
imperative
Total of discounted prices: 67.5
functional
Total of discounted prices: 67.5
6
10
x=0
y=1
x=1
y=2
x=3
y=3
x=6
y=4
10
 */
