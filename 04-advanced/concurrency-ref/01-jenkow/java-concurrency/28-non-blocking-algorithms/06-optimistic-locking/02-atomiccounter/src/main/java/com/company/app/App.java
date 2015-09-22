package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/*
http://tutorials.jenkov.com/java-concurrency/non-blocking-algorithms.html

** non blocking algorithm

If need more than one thread to write to the same, shared variable, a volatile variable will not be sufficient.
You will need some kind of exclusive access to the variable. We can use one of Java's atomic variables AtomicLong.

output:
attempt to shutdown executor
4
shutdown finished

 */
public class App 
{
    static class AtomicCounter{
        private AtomicLong count = new AtomicLong(0);
        /*
        The secret is AtomicLong.compareAndSet() inside the while loop. The compareAndSet() method call is can atomic
        operation. It compares the internal value of the AtomicLong to an expected value, and if the two are equal, sets
        a new internal value for the AtomicLong. The compareAndSet() method is typically supported by compare-and-swap
        instructions directly in the CPU. Therefore no synchronization is necessary, and no thread suspension is
        necessary. This saves the thread suspension overhead.

        Imagine that the internal value of the AtomicLong is 20. Then two threads read that value, and both tries to
        call compareAndSet(20, 20 + 1). Since compareAndSet() is an atomic operation, the threads will execute this
        method sequentially (one at a time).
        The first thread will compare the expected value of 20 (the previous value of the counter) to the internal value
        of the AtomicLong. Since the two values are equal, the AtomicLong will update its internal value to 21 (20+1).
        The updated variable will be set to true and the while loop will stop.
        Now the second thread calls compareAndSet(20, 20+1). Since the internal value of the AtomicLong is no longer 20,
        this call will fail. The internal value of the AtomicLong will not be set to 21. The updated variable will be
        set to false, and the thread will spin one more time around the while loop. This time it will read the value 21
        and attempt to update it to 22. If not other thread has called inc() in the meantime, the second iteration will
        succeed in updated the AtomicLong to 22.
         */
        public void inc(){
            boolean updated = false;
            while(!updated){
                long prevCount = this.count.get();
                // boolean compareAndSet(long expect, long update) {}
                updated = this.count.compareAndSet(prevCount, prevCount + 1);
            }
        }
        public long getCount(){
            return this.count.get();
        }
    }
    public static void main( String[] args )
    {
        AtomicCounter atomicCounter = new AtomicCounter();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 4; i++){
            executor.submit(() -> {
                atomicCounter.inc();
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.submit(() -> {
            System.out.println(atomicCounter.getCount());
        });
        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.SECONDS);
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
}
