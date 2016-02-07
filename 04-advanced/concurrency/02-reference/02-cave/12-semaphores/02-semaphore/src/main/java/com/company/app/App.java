package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
With Semaphore

It can limit the number of connections at Connection.connect().

For example below, it limit the number of connections to 10 which means there are 10 permits available
semaphore.acquire()
1, each thread calls semaphore.acquire() will take a permit
2, the thread that can try to acquire a permit will wait after after all permits are taken
semaphore.release()
1, each thread calls semaphore.release() will release a permit, so the waiting thread can get a permit
result: only up-to 10 connections can be opened
 */
class Connection{
    private static Connection instance = new Connection();
    private Semaphore semaphore = new Semaphore(10);  // 10 connections at a time
    private int connections = 0;
    private Connection(){
    }
    public static Connection getInstance(){
        return instance;
    }
    public void connect(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            doConnect();
        }
        finally {
            semaphore.release();
        }
    }
    public void doConnect(){
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
pool-1-thread-10; current connections: 2
pool-1-thread-2; current connections: 3
pool-1-thread-5; current connections: 4
pool-1-thread-4; current connections: 5
pool-1-thread-9; current connections: 6
pool-1-thread-3; current connections: 7
pool-1-thread-6; current connections: 8
pool-1-thread-7; current connections: 9
pool-1-thread-8; current connections: 10
...
pool-1-thread-191; current connections: 1
pool-1-thread-192; current connections: 2
pool-1-thread-193; current connections: 3
pool-1-thread-194; current connections: 4
pool-1-thread-195; current connections: 5
pool-1-thread-196; current connections: 6
pool-1-thread-197; current connections: 7
pool-1-thread-198; current connections: 8
pool-1-thread-199; current connections: 9
pool-1-thread-200; current connections: 10
 */
