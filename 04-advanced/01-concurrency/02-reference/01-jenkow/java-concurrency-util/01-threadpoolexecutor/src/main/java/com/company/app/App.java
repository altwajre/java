package com.company.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-util-concurrent/threadpoolexecutor.html

The thread pool contained inside the ThreadPoolExecutor can contain a varying amount of threads. The number of threads
in the pool is determined by these variables:
- corePoolSize
- maximumPoolSize

If less than corePoolSize, threads are created in the thread pool when a task is delegated to the thread pool, then a
new thread is created, even if idle threads exist in the pool.
If the internal queue of tasks is full, and corePoolSize threads or more are running, but less than maximumPoolSize
threads are running, then a new thread is created to execute the task.
 */
class Task implements Runnable{
    private String name;
    public Task(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void run() {
        try {
            System.out.print(name + " is doing something... ");
            Thread.sleep(1000);
            System.out.println("Done");
        }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
}
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2,4,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2));
        for(int i = 0; i < 6; i++){
            executorPool.execute(new Task("task_"+i));
        }
        Thread.sleep(5000);
        executorPool.shutdown();
    }
}
/*
output:
task_0 is doing something... task_4 is doing something... task_1 is doing something... task_5 is doing something... Done
Done
Done
task_2 is doing something... task_3 is doing something... Done
Done
Done
 */
