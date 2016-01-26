package com.company.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
ArrayBlockingQueue is a bounded, blocking queue that stores the elements internally in an array.
It stores the elements internally in FIFO (First In, First Out) order.
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        queue.put("1");
        queue.put("2");
        queue.put("3");

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }
}
/*
output:
1
2
3
 */
