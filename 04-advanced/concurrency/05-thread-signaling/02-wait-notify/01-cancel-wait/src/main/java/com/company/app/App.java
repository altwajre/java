package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
Exception occurs when try stop a waiting thread

output:
attempt to shutdown executor
pool-1-thread-1; wait()
shutdown finished
cancel non-finished tasks
java.lang.InterruptedException
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at com.company.app.App$Signal.doWait(App.java:17)
	at com.company.app.App.lambda$main$0(App.java:28)
	at com.company.app.App$$Lambda$1/495053715.run(Unknown Source)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)

 */
public class App 
{
    static class Signal{
        public synchronized void doWait(){
            try {
                System.out.println(Thread.currentThread().getName() + "; wait()");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main( String[] args )
    {
        Signal signal = new Signal();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
           signal.doWait();
        });
        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
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
