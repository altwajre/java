package com.company.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html

A BlockingQueue is typically used to have on thread produce objects, which another thread consumes.
Here is a diagram that illustrates this principle:

The producing thread will keep producing new objects and insert them into the queue, until the queue reaches
some upper bound on what it can contain. It’s limit, in order words. If the blocking queue reaches its upper limit,
the producing thread is blocked while trying to insert the new object. It remains blocked until a consuming thread
takes an object out of the queue.

The consuming thread keeps taking objects out of the blocking queue, and processes them. If the consuming thread
tries to take an object out of an empty queue, the consuming thread is blocked until a producing thread puts
an object into the queue.
 */
class Producer implements Runnable{
    protected BlockingQueue queue = null;
    public Producer(BlockingQueue queue){
        this.queue = queue;
    }
    public void run() {
        try {
            System.out.println("Producer started queue.size()=" + queue.size());
            Thread.sleep(10);
            queue.put("1");
            System.out.println("Producer after put 1 queue.size()=" + queue.size());
            queue.put("2");
            System.out.println("Producer after put 2 queue.size()=" + queue.size());
            queue.put("3");
            System.out.println("Producer after put 3 queue.size()=" + queue.size());
            Thread.sleep(2000);
            queue.put("4");
            System.out.println("Producer after put 4 queue.size()=" + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Consumer implements Runnable{
    protected BlockingQueue queue = null;
    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }
    public void run() {
        try {
            System.out.println("  Consumer started queue.size()=" + queue.size());
            Thread.sleep(50);
            System.out.println("  Consumer after sleep(50) queue.size()=" + queue.size());
            System.out.println(queue.take());
            System.out.println("  Consumer after take 1 queue.size()=" + queue.size());
            System.out.println(queue.take());
            System.out.println("  Consumer after take 2 queue.size()=" + queue.size());
            System.out.println(queue.take());
            System.out.println("  Consumer after take 3 queue.size()=" + queue.size());
            System.out.println(queue.take());
            System.out.println("  Consumer after take 4 queue.size()=" + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class App {
    public static void main( String[] args ) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
/*
output:
Producer started queue.size()=0
  Consumer started queue.size()=0
Producer after put 1 queue.size()=1
Producer after put 2 queue.size()=2
  Consumer after sleep(50) queue.size()=2
1
  Consumer after take 1 queue.size()=1
Producer after put 3 queue.size()=2
2
  Consumer after take 2 queue.size()=1
3
  Consumer after take 3 queue.size()=0
Producer after put 4 queue.size()=1
4
  Consumer after take 4 queue.size()=0
 */
