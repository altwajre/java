package com.company.app;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

import static com.company.app.Util.delay;

class Util {
    public static void delay(int timeout) {
        int delay = timeout;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Shop {
    private final String name;

    public Shop(String name) {
        this.name = name;
    }

    public double calculatePrice(String product) {
        delay(1000);
        return 28.8;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsyncFutureSupplyAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
}
class ShopHasError {
    private final String name;

    public ShopHasError(String name) {
        this.name = name;
    }

    public double calculatePrice(String product) throws IOException {
        // Checked exception occurs
        // https://coderanch.com/t/540082/certification/checked-unchecked-exception-list
        throw new IOException("Error: IOException occurs");
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = 0;
            try {
                price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (IOException e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }
}
public class App
{
    public static void main( String[] args )
    {

        simpleSuccessCase();

        propagateErrorInsideCompletableFuture();

        futureSupplyAsync();

        getMethodHasTimeout();

    }

    private static void futureSupplyAsync() {
        System.out.println("#futureSupplyAsync");
        Shop shop = new Shop("CompletableFuture.supplyAsync()");
        Future<Double> futurePrice = shop.getPriceAsyncFutureSupplyAsync("my favorite product");
        try {
            Double price = futurePrice.get();
            System.out.println("Price is " + price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    /*
    output:
    #futureSupplyAsync
    Price is 28.8
     */

    private static void propagateErrorInsideCompletableFuture() {
        System.out.println("#propagateErrorInsideCompletableFuture");
        ShopHasError shop = new ShopHasError("shop has error");
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        try {
            Double price = futurePrice.get();
            System.out.println("Price is " + price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    /*
    output:
    #propagateErrorInsideCompletableFuture
    java.util.concurrent.ExecutionException: java.io.IOException: Error: IOException occurs
      at java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:357)
      at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1887)
      at com.company.app.App.main(App.java:87)
    Caused by: java.io.IOException: Error: IOException occurs
      at com.company.app.ShopHasError.calculatePrice(App.java:58)
      at com.company.app.ShopHasError.lambda$getPriceAsync$1(App.java:66)
      at com.company.app.ShopHasError$$Lambda$1/668386784.run(Unknown Source)
      at java.lang.Thread.run(Thread.java:745)
     */

    private static void getMethodHasTimeout() {
        System.out.println("#getMethodHasTimeout");
        Shop shop = new Shop("get method has timeout");
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        try {
            Double price = futurePrice.get(128, TimeUnit.MILLISECONDS);
            System.out.println("Price is " + price);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
    /*
    output:
    #getMethodHasTimeout
    java.util.concurrent.TimeoutException
      at java.util.concurrent.CompletableFuture.timedGet(CompletableFuture.java:1763)
      at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1907)
      at com.company.app.App.main(App.java:82)
    Exception in thread "main" java.lang.RuntimeException: java.util.concurrent.TimeoutException
      at com.company.app.App.main(App.java:89)
    Caused by: java.util.concurrent.TimeoutException
      at java.util.concurrent.CompletableFuture.timedGet(CompletableFuture.java:1763)
      at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1907)
      at com.company.app.App.main(App.java:82)
     */

    private static void simpleSuccessCase() {
        System.out.println("#simpleSuccessCase");
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after "+invocationTime + " msecs");

        // Do some more tasks, like querying other shops
        doSomethingElse();

        // while the price of the product is being calculated
        try {
            // get() remains blocked until that value is available
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
    /*
    output:
    #simpleSuccessCase
    Invocation returned after 197 msecs
    Doing something else...
    Price is 28.8
    Price returned after 1210 msecs
     */

    private static void doSomethingElse() {
        System.out.println("Doing something else...");
    }
}
