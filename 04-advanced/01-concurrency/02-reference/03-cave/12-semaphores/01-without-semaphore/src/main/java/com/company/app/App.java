package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
Without Semaphore

It has no control to Connection.connect(), so there are 200 different threads can open 200 connections.
 */
class Connection{
    private static Connection instance = new Connection();
    private int connections = 0;
    private Connection(){
    }
    public static Connection getInstance(){
        return instance;
    }
    public void connect(){
        synchronized (this){
            connections++;
            System.out.println(Thread.currentThread().getName() + "; current connections: " + connections);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            connections--;
        }
    }
}
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < 200; i++){
            executor.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.MINUTES);
    }
}
/*
output:
pool-1-thread-1; current connections: 1
pool-1-thread-11; current connections: 2
pool-1-thread-10; current connections: 3
...
pool-1-thread-198; current connections: 198
pool-1-thread-199; current connections: 199
pool-1-thread-200; current connections: 200
 */
