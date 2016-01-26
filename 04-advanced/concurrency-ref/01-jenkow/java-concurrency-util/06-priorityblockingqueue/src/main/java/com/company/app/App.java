package com.company.app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/*
http://tutorials.jenkov.com/java-util-concurrent/priorityblockingqueue.html

The PriorityBlockingQueue is an unbounded concurrent queue. It uses the same ordering rules as the java.util.PriorityQueue
class. You cannot insert null into this queue.
All elements inserted into the PriorityBlockingQueue must implement the java.lang.Comparable interface. The elements
thus order themselves according to whatever priority you decide in your Comparable implementation.
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        BlockingQueue<String> queue = new PriorityBlockingQueue<>();

        // String implements java.lang.Comparable
        queue.put("z");
        queue.put("x");
        queue.put("y");
        queue.put("a");
        queue.put("c");

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }
}
/*
output:
a
c
x
y
z
 */