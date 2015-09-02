package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/*
http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

output:
pool-1-thread-1
attempt to shutdown executor
pool-1-thread-3
pool-1-thread-2
pool-1-thread-1
pool-1-thread-3
pool-1-thread-2
pool-1-thread-1
pool-1-thread-3
pool-1-thread-2
pool-1-thread-1
pool-1-thread-3
pool-1-thread-2
pool-1-thread-1
pool-1-thread-3
pool-1-thread-2
shutdown finished
15

 */
public class App 
{
    static int count = 0;
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e){
            System.err.println("tasks interrupted");
        }
        finally {
            if(!executor.isTerminated()){
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
    public static void main( String[] args )
    {
        ReentrantLock lock = new ReentrantLock();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 15)
                .forEach(i -> executor.submit(() -> {
                    System.out.println(Thread.currentThread().getName());
                    lock.lock();
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                        count = count + 1;  // critical section
                    }
                    catch (InterruptedException e) { }
                    finally {
                        lock.unlock();
                    }
                }));

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        stop(executor);
        System.out.println(count);
    }
}
