package com.company.app;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/*
http://tutorials.jenkov.com/java-util-concurrent/blockingdeque.html

The BlockingDeque class is a Deque which blocks threads trying to insert or remove elements from the deque, in case it
 is either not possible to insert or remove elements from the deque.

A deque is short for "Double Ended Queue". Thus, a deque is a queue which you can insert and take elements from both ends.
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