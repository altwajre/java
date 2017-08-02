package com.company.app;

import java.util.Random;
import java.util.concurrent.*;

/*
https://www.youtube.com/watch?v=lnbWFV4b7M4&list=PLBB24CFB073F1048E&index=13

Callable interface and Future
A callable thread can return a value to Future, and future.get() to get the returned value.

 */
public class App {
  public static void main(String[] args) {

    long startTime = System.nanoTime();

    ExecutorService executor = Executors.newCachedThreadPool();

    Future<Integer> future1 = executor.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int duration = 1800;

        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + ": starting ...");

        Thread.sleep(duration);

        System.out.println(threadName + ": finished.");

        return duration;
      }
    });

    Future<Integer> future2 = executor.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int duration = 1000;

        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + ": starting ...");

        Thread.sleep(duration);

        System.out.println(threadName + ": finished.");

        return duration;
      }
    });

    executor.shutdown();

    String mainThreadName = Thread.currentThread().getName();
    System.out.println(mainThreadName + ": after future before future.get()");

    try {
      // future.get() wait for result
      System.out.println(mainThreadName + "future1.get(): " + future1.get());
      System.out.println(mainThreadName + "future1.get(): " + future2.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    System.out.println(mainThreadName + ": after future.get()");

    long estimatedNanoTime = System.nanoTime() - startTime;
    long estimatedMilliTime = TimeUnit.MILLISECONDS.convert(estimatedNanoTime, TimeUnit.NANOSECONDS);
    System.out.println("Total time: " + estimatedMilliTime + " milliseconds that saves about 1000 milliseconds if using sync");

  }
}
/*
output:
Starting ...
Finished.
Result is: 775
 */
