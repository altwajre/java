package com.company.app;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.company.app.Util.delay;

import com.company.app.ExchangeService.Money;

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
  @Getter
  private final String name;

  public Shop(String name) {
    this.name = name;
  }

  public Integer calculatePrice(String product) {
    delay(1000);
    Integer price = new Random().nextInt(100);
    System.out.println(price);
    return price;
  }

  public double getPrice(String product) {
    return calculatePrice(product);
  }

  public Future<Integer> getPriceAsync(String product) {
    return CompletableFuture.supplyAsync(() -> calculatePrice(product));
  }
}

class ExchangeService {
  public enum Money {
    USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);

    private final double rate;

    Money(double rate) {
      this.rate = rate;
    }
  }

  public static double getRate(Money source, Money destination) {
    return getRateWithDelay(source, destination);
  }

  private static double getRateWithDelay(Money source, Money destination) {
    delay(1000);
    return destination.rate / source.rate;
  }
}

class BestPriceFinder {
  private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
      new Shop("LetsSaveBig"),
      new Shop("MyFavoriteShop"),
      new Shop("BuyItAll"));

  private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
    @Override
    public Thread newThread(Runnable r) {
      Thread thread = new Thread(r);
      thread.setDaemon(true); // the worker thread terminates when the main thread terminates
      return thread;
    }
  });

  public List<String> findPricesSequential(String product) {
    return shops.stream()
        .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
        .collect(Collectors.toList());
  }

  public List<String> findPricesParallel(String product) {
    return shops.parallelStream()
        .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
        .collect(Collectors.toList());
  }

  public List<String> findPricesFuture(String product) {

    // Calculate each price async with a CompletableFuture
    List<CompletableFuture<String>> priceFutures = shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor))
        .collect(Collectors.toList());

    // Wait for the completion of all async operations
    List<String> prices = priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toList());

    return prices;
  }

  // Listing 11.17. Combining two independent CompletableFutures
  public List<String> findPricesInUSD(String product) {

    List<CompletableFuture<String>> priceFutures = shops.stream()
        // Create a first task querying the shop to obtain the price of a product
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product))
            // Combine the price and exchange rate by multiplying them
            .thenCombine(
                // Create a second independent task to retrieve the conversion rate between USD and EUR
                CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)),
                (price, rate) -> price * rate)
            .thenApply(price -> shop.getName() + " price is " + price))
        .collect(Collectors.toList());

    // Wait for the completion of all async operations
    List<String> prices = priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toList());

    return prices;
  }

}

public class App {
  static BestPriceFinder finder = new BestPriceFinder();

  public static void main(String[] args) {
    execute("sequential", () -> finder.findPricesSequential("myPhone27S"));
    execute("parallel", () -> finder.findPricesParallel("myPhone27S"));
    execute("composed CompletableFuture", () -> finder.findPricesFuture("myPhone27S"));
    execute("combined USD CompletableFuture", () -> finder.findPricesInUSD("myPhone27S"));
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
72
11
71
44
[BestPrice price is 72.0, LetsSaveBig price is 11.0, MyFavoriteShop price is 71.0, BuyItAll price is 44.0]
sequential done in 4024 msecs
97
79
91
20
[BestPrice price is 91.0, LetsSaveBig price is 79.0, MyFavoriteShop price is 20.0, BuyItAll price is 97.0]
parallel done in 1008 msecs
18
19
1
32
[BestPrice price is 1.0, LetsSaveBig price is 19.0, MyFavoriteShop price is 32.0, BuyItAll price is 18.0]
composed CompletableFuture done in 1008 msecs
34
39
38
99
[BestPrice price is 25.11319402896881, LetsSaveBig price is 28.06768744414161, MyFavoriteShop price is 73.12371202552683, BuyItAll price is 28.806310797934813]
combined USD CompletableFuture done in 2010 msecs
 */
