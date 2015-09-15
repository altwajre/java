package com.company.app;

import java.util.concurrent.TimeUnit;

/*

timeout

output:
false
finished

 */
public class App 
{
    static int count = 1;
    static int upperBound = 1100;  // timeout - fail to get data
//    static int upperBound = 500;  // no timeout - successfully got data
    static boolean tryGetData(long timeout, TimeUnit unit) throws InterruptedException {
        long nanosTimeout = unit.toNanos(timeout);
        if(nanosTimeout <= 0L) return false;
        long deadline = System.nanoTime() + nanosTimeout;
        while(true){
            if(count == upperBound) return true;
            Thread.sleep(1);
            nanosTimeout = deadline - System.nanoTime();
            count++;
            if(nanosTimeout <= 0L) return false;
        }
    }
    public static void main( String[] args ) throws InterruptedException {
        System.out.println("tryGetData()=" + tryGetData(1, TimeUnit.SECONDS));
        Thread.sleep(3);
        System.out.println("finished work");
    }
}
