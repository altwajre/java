package com.company.app;

import java.util.LinkedList;
import java.util.List;

/*
http://tutorials.jenkov.com/java-concurrency/blocking-queues.html

Usage:
A thread produces objects, which another thread consumes the objects.

A thread try to dequeue an item from an empty queue is blocked until some other thread inserts an item into queue.
A thread try to enqueue an item in a full queue is blocked until some other thread makes space in the queue.

output:
  Thread-0: dequeue entered; size()=0
  Thread-0: dequeue wait(); size()=0
Thread-1: enqueue entered 1; size()=0
Thread-1: enqueue notifyAll() 1; size()=0
Thread-1: enqueue added 1; size()=1
Thread-1: enqueue entered 2; size()=1
Thread-1: enqueue added 2; size()=2
Thread-1: enqueue entered 3; size()=2
Thread-1: enqueue wait() 3; size()=2
  Thread-0: dequeue EXIT wait(); size()=2
  Thread-0: dequeue notifyAll(); size()=2
  Thread-0: dequeue removed 1; size()=1
1
Thread-1: enqueue EXIT wait() 3; size()=1
Thread-1: enqueue added 3; size()=2
  Thread-0: dequeue entered; size()=2
  Thread-0: dequeue notifyAll(); size()=2
  Thread-0: dequeue removed 2; size()=1
2
  Thread-0: dequeue entered; size()=1
  Thread-0: dequeue removed 3; size()=0
3

 */
public class App 
{
    static class BlockingQueue{
        private List queue = new LinkedList();
        private int limit = 10;
        public BlockingQueue(int limit){ this.limit = limit; }
        public int size(){ return queue.size(); }
        public synchronized void enqueue(Object item){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": enqueue entered " + item + "; size()=" + queue.size());
            while(queue.size() == limit){
                try {
                    System.out.println(threadName + ": enqueue wait() " + item + "; size()=" + queue.size());
                    wait();
                    System.out.println(threadName + ": enqueue EXIT wait() " + item + "; size()=" + queue.size());
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
            if(queue.size() == 0){  // wake all waiting threads
                System.out.println(threadName + ": enqueue notifyAll() " + item + "; size()=" + queue.size());
                notifyAll();
            }
            queue.add(item);
            System.out.println(threadName + ": enqueue added " + item + "; size()=" + queue.size());
        }
        public synchronized Object dequeue(){
            String threadName = Thread.currentThread().getName();
            System.out.println("  "+threadName + ": dequeue entered; size()=" + queue.size());
            while(queue.size() == 0){
                try {
                    System.out.println("  "+threadName + ": dequeue wait(); size()=" + queue.size());
                    wait();
                    System.out.println("  " + threadName + ": dequeue EXIT wait(); size()=" + queue.size());
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
            if(queue.size() == limit){  // wake all waiting threads
                System.out.println("  "+threadName + ": dequeue notifyAll(); size()=" + queue.size());
                notifyAll();
            }
            Object temp = queue.remove(0);
            System.out.println("  " + threadName + ": dequeue removed "+temp+"; size()=" + queue.size());
            return temp;
        }
    }
    public static void main( String[] args )
    {
        final BlockingQueue blockingQueue = new BlockingQueue(2);
        Runnable consumer = () -> {
            System.out.println(blockingQueue.dequeue());
            System.out.println(blockingQueue.dequeue());
            System.out.println(blockingQueue.dequeue());
        };
        new Thread(consumer).start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Runnable producer = () -> {
            blockingQueue.enqueue("1");
            blockingQueue.enqueue("2");
            blockingQueue.enqueue("3");
        };
        new Thread(producer).start();
    }
}
