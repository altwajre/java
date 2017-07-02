package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/*
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

invokeAny() will return the result of the first callable terminated.

output:
fastest task

 */
public class App 
{
    static Callable<String> callable(final String result, final long sleepSeconds){
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
    public static void main( String[] args ) throws Exception {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(
                callable("slow task", 2),
                callable("fastest task", 1),
                callable("slowest task", 3)
        );

        String result = executor.invokeAny(callables);
        System.out.println(result);
    }
}
