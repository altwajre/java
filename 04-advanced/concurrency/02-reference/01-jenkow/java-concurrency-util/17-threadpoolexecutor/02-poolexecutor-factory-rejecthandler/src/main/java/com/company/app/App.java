package com.company.app;

import java.util.concurrent.*;

/*
http://www.javacodegeeks.com/2013/01/java-thread-pool-example-using-executors-and-threadpoolexecutor.html
 */
class WorkerThread implements Runnable{
    private String command;
    public WorkerThread(String s){ this.command = s; }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        processCommand();
        System.out.println(Thread.currentThread().getName()+" End.");
    }
    private void processCommand() {
        try { Thread.sleep(5000); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
    @Override
    public String toString(){ return this.command; }
}
class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " is rejected");
    }
}
class MyMonitorThread implements Runnable {
    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;
    public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        this.seconds = delay;
    }
    public void shutdown() { this.run = false; }
    @Override
    public void run() {
        while (true) {
            System.out.printf("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                    executor.getPoolSize(), executor.getCorePoolSize(), executor.getActiveCount(), executor.getCompletedTaskCount(),
                    executor.getTaskCount(), executor.isShutdown(), executor.isTerminated());
            try{ Thread.sleep(seconds*1000); }
            catch (InterruptedException e){ e.printStackTrace(); }
        }
    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2,4,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),threadFactory,rejectionHandler);
        MyMonitorThread monitor = new MyMonitorThread(executorPool,3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        for(int i=0; i<10; i++){
            executorPool.execute(new WorkerThread("cmd"+i));
        }
        Thread.sleep(30000);
        executorPool.shutdown();
        Thread.sleep(5000);
        monitor.shutdown();
    }
}
/*
output:
[monitor] [0/2] Active: 0, Completed: 0, Task: 0, isShutdown: false, isTerminated: falsepool-1-thread-4 Start. Command = cmd5
cmd6 is rejected
pool-1-thread-3 Start. Command = cmd4
pool-1-thread-2 Start. Command = cmd1
pool-1-thread-1 Start. Command = cmd0
cmd7 is rejected
cmd8 is rejected
cmd9 is rejected
[monitor] [4/2] Active: 4, Completed: 0, Task: 6, isShutdown: false, isTerminated: falsepool-1-thread-4 End.
pool-1-thread-2 End.
pool-1-thread-4 Start. Command = cmd2
pool-1-thread-3 End.
pool-1-thread-2 Start. Command = cmd3
pool-1-thread-1 End.
[monitor] [4/2] Active: 2, Completed: 4, Task: 6, isShutdown: false, isTerminated: false[monitor] [4/2] Active: 2, Completed: 4, Task: 6, isShutdown: false, isTerminated: falsepool-1-thread-4 End.
pool-1-thread-2 End.
[monitor] [4/2] Active: 0, Completed: 6, Task: 6, isShutdown: false, isTerminated: false[monitor] [2/2] Active: 0, Completed: 6, Task: 6, isShutdown: false, isTerminated: false[monitor] [2/2] Active: 0, Completed: 6, Task: 6, isShutdown: false, isTerminated: false[monitor] [2/2] Active: 0, Completed: 6, Task: 6, isShutdown: false, isTerminated: false[monitor] [2/2] Active: 0, Completed: 6, Task: 6, isShutdown: false, isTerminated: false[monitor] [2/2] Active: 0, Completed: 6, Task: 6, isShutdown: false, isTerminated: false[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true[monitor] [0/2] Active: 0, Completed: 6, Task: 6, isShutdown: true, isTerminated: true
 */
