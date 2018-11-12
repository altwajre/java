package com.company.app;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
The invokeAny() method takes a collection of Callable objects, or subinterfaces of Callable, invoking this method does
not return a Future, but returns the result of one of the Callable objects. You have to guarantee about which of the
Callable's results you get. Just one of ones that finish.
If one of the tasks complete (or throws an exception), the rest of the Callable's are cancelled.
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
            String result = executorService.invokeAny(callables);
            System.out.println("result="+result);
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
result=Task 2
 */
