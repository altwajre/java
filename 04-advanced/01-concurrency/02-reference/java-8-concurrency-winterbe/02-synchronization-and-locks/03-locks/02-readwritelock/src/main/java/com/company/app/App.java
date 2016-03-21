package com.company.app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

output:
pool-1-thread-1 start writing
attempt to shutdown executor
pool-1-thread-1 finished writing
  pool-1-thread-2 start reading
  pool-1-thread-3 start reading
bar
  pool-1-thread-3 finished reading
bar
  pool-1-thread-2 finished reading
shutdown finished

 */
public class App 
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Map<String, String> map  = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try{
                System.out.println(Thread.currentThread().getName() + " start writing");
                sleep(2);
                map.put("foo", "bar");
                System.out.println(Thread.currentThread().getName() + " finished writing");
            }
            finally {
                lock.writeLock().unlock();
            }
        });

        Runnable readTask = () -> {
            lock.readLock().lock();
            try{
                System.out.println("  "+Thread.currentThread().getName() + " start reading");
                sleep(1);
                System.out.println(map.get("foo"));
                System.out.println("  "+Thread.currentThread().getName() + " finished reading");
            }
            finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        stop(executor);
    }
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
    static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
