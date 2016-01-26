package com.company.app;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/*
http://tutorials.jenkov.com/java-util-concurrent/linkedblockingdeque.html

The LinkedBlockingDeque keeps the elements internally in a linked structure (linked nodes).
The word Deque comes from the term "Double Ended Queue". A Deque is thus a queue where you can insert and remove
elements from both ends of the queue.
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        BlockingDeque<String> deque = new LinkedBlockingDeque<>();
        deque.addFirst("1");
        deque.addFirst("2");

        deque.addLast("9");
        deque.addLast("8");

        System.out.println("deque.takeFirst()="+deque.takeFirst());

        System.out.println("deque.takeLast()="+deque.takeLast());

        System.out.println("deque.take()="+deque.take());
    }
}
/*
output:
deque.takeFirst()=2
deque.takeLast()=8
deque.take()=1
 */
