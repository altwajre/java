package com.company.app;

import java.util.concurrent.*;

/*
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

1, callable returns a value instead of void like runnable
2, future will retrieve the return value from callable
  - isDone() check if the future has already finished the execution
  - get() blocks the current thread and waits until the callable completes before returning the actual result
3, future is tightly coupled to the underlying executor service. the non-terminated future will
   throw exception executor is shutdown

output:
java.util.concurrent.ExecutionException: java.lang.IllegalStateException: task interrupted
future done? false
	at java.util.concurrent.FutureTask.report(FutureTask.java:122)
future done? true
result: -1
	at java.util.concurrent.FutureTask.get(FutureTask.java:192)
	at com.company.app.App.main(App.java:44)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)
Caused by: java.lang.IllegalStateException: task interrupted
	at com.company.app.App.lambda$main$0(App.java:32)
	at com.company.app.App$$Lambda$1/189568618.call(Unknown Source)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
Caused by: java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at java.lang.Thread.sleep(Thread.java:340)
	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
	at com.company.app.App.lambda$main$0(App.java:28)
	... 5 more

 */
public class App
{
    public static void main( String[] args )
    {
        Callable<Integer> task = () -> {
            try{
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e){
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);
        System.out.println("future done? " + future.isDone());

        executor.shutdownNow();  // NOTE: showdown before future.get() causes exception

        Integer result = -1;
        try {
            result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);

    }
}
