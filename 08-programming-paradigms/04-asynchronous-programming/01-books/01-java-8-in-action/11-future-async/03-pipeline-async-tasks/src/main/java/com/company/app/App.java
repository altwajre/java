package com.company.app;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.company.app.Util.delay;
import static com.company.app.Util.format;

class Util {
    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
    public static void delay(int timeout) {
        int delay = timeout;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static double format(double number) {
        synchronized (formatter) {
            return new Double(formatter.format(number));
        }
    }
}
class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        delay(1000);
        double discountedPrice = price * (100 - code.percentage) / 100;
        System.out.println("discountedPrice="+discountedPrice);
        return format(discountedPrice);
    }
}
@Getter
@AllArgsConstructor
class Quote {
    private String shopName;
    private double price;
    private Discount.Code discountCode;
    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }
}
class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public double calculatePrice(String product) {
        delay(1000);
        double price = random.nextDouble() * product.charAt(0) + product.charAt(1);
        System.out.println("price="+price);
        return format(price);
    }

    public String getName() {
        return name;
    }
}
class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
        new Shop("LetsSaveBig"),
        new Shop("MyFavoriteShop"),
        new Shop("BuyItAll"),
        new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), runnable -> {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
            .map(shop -> shop.getPrice(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
            .map(shop -> shop.getPrice(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
            // Async retrieve the non-discounted price from each shop
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            // Transform the String returned by a shop into a Quote object when it becomes available
            .map(future -> future.thenApply(Quote::parse))
            // Compose the resulting Future with another async task, applying the discount code
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .collect(Collectors.toList());

        return priceFutures.stream()
            // Wait for all the Futures in the stream to be completed and extract their respective results
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }
}
public class App
{
    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main( String[] args )
    {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
    }

    private static void execute(String msg, Supplier<List<String>> supplier) {
        long start = System.nanoTime();
        System.out.println(supplier.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
/*
output:
price=123.25651664705744
discountedPrice=110.934
price=169.4653393606115
discountedPrice=135.576
price=214.12914480588853
discountedPrice=192.717
price=184.74384995303313
discountedPrice=184.74
price=176.08324622661846
discountedPrice=167.276
[BestPrice price is 110.93, LetsSaveBig price is 135.58, MyFavoriteShop price is 192.72, BuyItAll price is 184.74, ShopEasy price is 167.28]
sequential done in 10065 msecs
price=204.43981201636132
price=130.62728214420736
price=199.86642336591416
price=204.7382895438923
price=220.54421320524511
discountedPrice=173.774
discountedPrice=174.02900000000002
discountedPrice=176.43200000000002
discountedPrice=117.567
discountedPrice=169.8895
[BestPrice price is 117.57, LetsSaveBig price is 174.03, MyFavoriteShop price is 173.77, BuyItAll price is 169.89, ShopEasy price is 176.43]
parallel done in 2013 msecs
price=161.14747297059597
price=155.9041805933185
price=200.89398407500244
price=227.53480147033423
price=207.62077668778028
discountedPrice=166.09599999999998
discountedPrice=204.77700000000002
discountedPrice=140.31
discountedPrice=128.92
discountedPrice=190.8455
[BestPrice price is 204.78, LetsSaveBig price is 190.85, MyFavoriteShop price is 128.92, BuyItAll price is 140.31, ShopEasy price is 166.1]
composed CompletableFuture done in 2017 msecs
 */
