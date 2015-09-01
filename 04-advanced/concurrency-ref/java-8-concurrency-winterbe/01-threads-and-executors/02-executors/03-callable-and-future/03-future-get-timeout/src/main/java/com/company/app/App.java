package com.company.app;

import java.util.concurrent.*;

/*
future.get() will block and wait until the underlying callable has been terminated.
If a callable runs forever, your application will be unresponsive. You can passing a timeout to address the issue.

output:
Exception in thread "main" java.util.concurrent.TimeoutException
	at java.util.concurrent.FutureTask.get(FutureTask.java:205)
	at com.company.app.App.main(App.java:17)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)

 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<?> future = executor.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 123;
        });
        future.get(1, TimeUnit.SECONDS);  // NOTE: passing timeout to prevent wait for too long
    }
}
