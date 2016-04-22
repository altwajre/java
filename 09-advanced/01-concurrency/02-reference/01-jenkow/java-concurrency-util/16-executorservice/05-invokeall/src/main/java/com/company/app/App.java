package com.company.app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/*
The invokeAll() method invokes all of the Callable objects you pass to it in the collection passed as parameter. The
invokeAll() returns a list of Future objects via which you can obtain the results of the executions of each Callable.
 */
public class App
{
    public static void main( String[] args )
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Set<Callable<String>> callables = new HashSet<>();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 3";
            }
        });
        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            for(Future<String> future : futures){
                System.out.println("future.get = " + future.get());
            }
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
future.get = Task 3
future.get = Task 1
future.get = Task 2
 */
