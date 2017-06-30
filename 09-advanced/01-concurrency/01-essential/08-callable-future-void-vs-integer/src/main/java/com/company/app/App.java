package com.company.app;

import java.util.Random;
import java.util.concurrent.*;

public class App
{
    public static void main( String[] args )
    {
        testCallableVoid();

        testCallableInteger();
    }

    private static void testCallableInteger() {

        System.out.println("#testCallableInteger");
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(2000);

                System.out.println("Starting ...");

                Thread.sleep(duration);

                System.out.println("Finished.");

                return duration;
            }
        });

        executor.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    /*
    output:
    #testCallableInteger
    Starting ...
    Finished.
    Result is: 285
     */

    private static void testCallableVoid() {

        System.out.println("#testCallableVoid");

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Void> future = executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(2000);

                System.out.println("Starting ...");

                Thread.sleep(duration);

                System.out.println("Finished.");

                return null;
            }
        });

        executor.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    /*
    output:
    #testCallableVoid
    Starting ...
    Finished.
    Result is: null
     */
}
