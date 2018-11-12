package com.company.app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/*
http://tutorials.jenkov.com/java-util-concurrent/synchronousqueue.html

The SynchronousQueue is q queue that can only contain a single element internally. A thread inserting an element into
the queue is blocked until another thread takes that element from the queue.
 */
public class App
{
    public static void main( String[] args )
    {
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread("Producer"){
            public void run(){
                String event = "Four";
                try{
                    queue.put(event);
                    System.out.printf("[%s] published event: %s %n", Thread.currentThread().getName(), event);
                    Thread.sleep(1000);
                    event = "second";
                    queue.put(event);
                    System.out.printf("[%s] published event: %s %n", Thread.currentThread().getName(), event);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start(); // starting publisher thread

        new Thread("Consumer"){
            public void run(){
                try {
                    System.out.printf("[%s] consumed event: %s %n", Thread.currentThread().getName(), queue.take());
                    System.out.println("Before second take()");
                    System.out.printf("[%s] consumed event: %s %n", Thread.currentThread().getName(), queue.take());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start(); // starting consumer thread
    }
}
/*
output:
[Producer] published event: Four
[Consumer] consumed event: Four
Before second take()
[Producer] published event: second
[Consumer] consumed event: second
 */
