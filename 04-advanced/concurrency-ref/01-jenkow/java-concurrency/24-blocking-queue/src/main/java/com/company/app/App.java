package com.company.app;

import java.util.LinkedList;
import java.util.List;

/*
http://tutorials.jenkov.com/java-concurrency/blocking-queues.html

Usage:
A thread produces objects, which another thread consumes the objects.

A blocking queue is a queue that blocks when you try to dequeue from it and the queue is empty, or if you try to
enqueue items to it and the queue is already full. A thread trying to dequeue from an empty queue is blocked until
some other thread inserts an item into the queue. A thread trying to enqueue an item in a full queue is blocked until
some other thread makes space in the queue, either by dequeuing one or more items or clearing the queue completely.

wait when enqueue item to full queue
wait when dequeue item from empty queue

output:
Thread-0: Producer.run()
  Thread-1: Consumer.run()
  Thread-1: Consumer.run() queue.size()=0
Thread-0: Producer.run() queue.enqueue('1')
Thread-0: Producer.run() queue.enqueue('2')
Thread-0: Producer.run() queue.enqueue('3')
  Thread-1: Consumer.run() queue.size()=2
  Thread-1: Consumer.run() queue.dequeue()=1
  Thread-1: Consumer.run() queue.dequeue()=2
  Thread-1: Consumer.run() queue.dequeue()=3
Thread-0: Producer.run() queue.enqueue('4')
  Thread-1: Consumer.run() queue.dequeue()=4

 */
public class App 
{
    static class Producer implements Runnable{
        protected BlockingQueue queue = null;
        public Producer(BlockingQueue queue){
            this.queue = queue;
        }
        public void run() {
            String threadName = Thread.currentThread().getName();
            try {
                System.out.println(threadName + ": Producer.run()");
                Thread.sleep(10);
                System.out.println(threadName + ": Producer.run() queue.enqueue('1')");
                queue.enqueue("1");
                System.out.println(threadName + ": Producer.run() queue.enqueue('2')");
                queue.enqueue("2");
                System.out.println(threadName + ": Producer.run() queue.enqueue('3')");
                queue.enqueue("3");
                Thread.sleep(2000);
                System.out.println(threadName + ": Producer.run() queue.enqueue('4')");
                queue.enqueue("4");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    static class Consumer implements Runnable{
        protected BlockingQueue queue = null;
        public Consumer(BlockingQueue queue){
            this.queue = queue;
        }
        public void run() {
            String threadName = Thread.currentThread().getName();
            try {
                System.out.println("  "+threadName + ": Consumer.run()");
                System.out.println("  "+threadName+": Consumer.run() queue.size()=" + queue.size());
                Thread.sleep(50);
                System.out.println("  "+threadName + ": Consumer.run() queue.size()=" + queue.size());
                Thread.sleep(1000);
                System.out.println("  "+threadName + ": Consumer.run() queue.dequeue()=" + queue.dequeue());
                System.out.println("  "+threadName + ": Consumer.run() queue.dequeue()=" + queue.dequeue());
                System.out.println("  "+threadName + ": Consumer.run() queue.dequeue()=" + queue.dequeue());
                System.out.println("  "+threadName + ": Consumer.run() queue.dequeue()=" + queue.dequeue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    static class BlockingQueue{
        private List queue = new LinkedList();
        private int limit = 10;
        public BlockingQueue(int limit){
            this.limit = limit;
        }
        public int size(){
            return queue.size();
        }
        public synchronized void enqueue(Object item){
            while (this.queue.size() == this.limit){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(this.queue.size() == 0){  // notify items that wait for enqueue
                notifyAll();
            }
            this.queue.add(item);
        }
        public synchronized Object dequeue(){
            while(this.queue.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(this.queue.size() == this.limit){  // notify items that wait for dequeue
                notifyAll();
            }
            return this.queue.remove(0);
        }
    }
    public static void main( String[] args ) throws InterruptedException {
        BlockingQueue queue = new BlockingQueue(2);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
