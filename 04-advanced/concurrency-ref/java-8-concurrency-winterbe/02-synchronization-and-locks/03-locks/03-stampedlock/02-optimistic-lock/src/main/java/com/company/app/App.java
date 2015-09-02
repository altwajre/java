package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/*
http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

An optimistic read lock is acquired by calling tryOptimisticRead() which always returns a stamp without blocking
the current thread, no matter if the lock is actually available. If there’s already a write lock active the returned
stamp equals zero. You can always check if a stamp is valid by calling lock.validate(stamp).

The optimistic block is valid right after acquiring the lock. In contrast to normal read locks an optimistic lock
doesn’t prevent other threads to obtain a write lock instantaneously. AFter sending the first thread to sleep for
one second the second thread obtains a write lock without waiting for the optimistic read block to be released.
From this point the optimistic read lock is no longer valid. Even when the write lock is released the optimistic
read locks stays invalid.

output:
Optimistic Lock Valid: true
attempt to shutdown executor
Write Lock acquired
Optimistic Lock Valid: false
Write done
Optimistic Lock Valid: false
shutdown finished

 */
public class App 
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try{
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                sleep(1);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                sleep(2);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            }
            finally {
                lock.unlock(stamp);
            }
        });

        executor.submit(() -> {
           long stamp = lock.writeLock();
            try{
                System.out.println("Write Lock acquired");
                sleep(2);
            }
            finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(8, TimeUnit.SECONDS);
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
