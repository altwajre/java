package com.company.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(() -> doWork(), 0, 1, TimeUnit.SECONDS);
    }

    static void doWork(){
        System.out.println("doWork() is invoked");
    }
}
/*
output:
doWork() is invoked
doWork() is invoked
doWork() is invoked
doWork() is invoked

click "stop" button to stop
 */