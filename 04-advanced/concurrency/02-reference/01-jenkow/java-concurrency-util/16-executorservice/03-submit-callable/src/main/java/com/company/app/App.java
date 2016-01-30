package com.company.app;

import java.util.concurrent.*;

/*
The submit(Callable) method is similar to the submit(Runnable) method except for the type of parameter it takes. The
Callable instance very similar to a Runnable except that its call() method can return a result. The Runnable.run()
method cannot return a result.

The Callable's result can be obtained via the Future object returned by the submit(Callable) method.
 */
public class App
{
    public static void main( String[] args )
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });
        try {
            System.out.println("future.get()="+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
/*
output:
Asynchronous Callable
future.get()=Callable Result
 */
