package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
lock an object such as Chopstick

output:
chopstick.pickUp()=true
chopstick.pickUp()=false
chopstick.pickUp()=false
attempt to shutdown executor
chopstick.pickUp()=true
shutdown finished

 */
public class App 
{
    static class Chopstick{
        public ReentrantLock lock = new ReentrantLock();
        public boolean pickUp(){
            return lock.tryLock();
        }
        public void putDown(){
            lock.unlock();
        }
    }
    public static void main( String[] args )
    {
        Chopstick chopstick = new Chopstick();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++){
            executor.submit(() -> {
                System.out.println("chopstick.pickUp()="+chopstick.pickUp());
            });
        }
        executor.submit(()->{
            chopstick.putDown();
        });
        executor.submit(()->{
            System.out.println("chopstick.pickUp()=" + chopstick.pickUp());
        });

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
}
