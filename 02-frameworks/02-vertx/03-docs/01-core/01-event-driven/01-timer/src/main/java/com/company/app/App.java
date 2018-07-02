package com.company.app;

import io.vertx.core.Vertx;

public class App
{
    public static void main( String[] args )
    {

        Vertx vertx = Vertx
            .vertx();

        long periodicId = vertx.setPeriodic(500, id -> {
            // This handler will get called every second
            System.out.println(Thread.currentThread().getName() + ": Periodic Timer fired");
        });

        System.out.println("periodicId="+periodicId);

        long timerId = vertx.setTimer(1800, timerHandler -> {
            // This handler will get called once after a specified delay
            System.out.println(Thread.currentThread().getName() + ": One-time Timer fired");

            // cancel timer
            vertx.cancelTimer(periodicId);

            vertx.close();
        });

        System.out.println("timerId="+timerId);

        System.out.println(Thread.currentThread().getName() + ": thread END");

    }
}
/*
output:
periodicId=0
timerId=1
main: thread END
vert.x-eventloop-thread-0: Periodic Timer fired
vert.x-eventloop-thread-0: Periodic Timer fired
vert.x-eventloop-thread-0: Periodic Timer fired
vert.x-eventloop-thread-1: One-time Timers fired
 */
