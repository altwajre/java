package com.company.app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
The LinkedBlockingQueue keeps the elements internally in a linked structure (linked nodes).
It stores the element internally in FIFO (First In, First Out) order.
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(3);
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
