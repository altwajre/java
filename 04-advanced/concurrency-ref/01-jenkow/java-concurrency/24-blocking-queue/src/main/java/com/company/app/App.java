package com.company.app;

import java.util.LinkedList;
import java.util.List;

/*
http://tutorials.jenkov.com/java-concurrency/blocking-queues.html

A blocking queue is a queue that blocks when you try to dequeue from it and the queue is empty, or if you try to
enqueue items to it and the queue is already full. A thread trying to dequeue from an empty queue is blocked until
some other thread inserts an item into the queue. A thread trying to enqueue an item in a full queue is blocked until
some other thread makes space in the queue, either by dequeuing one or more items or clearing the queue completely.

output:
queue.size()=0
queue.size()=2
1
2
3
4

 */
public class App 
{
    static class Producer implements Runnable{
        protected BlockingQueue queue = null;
        public Producer(BlockingQueue queue){
            this.queue = queue;
        }
        public void run() {
            try {
                Thread.sleep(10);
                queue.enqueue("1");
                queue.enqueue("2");
                queue.enqueue("3");
                Thread.sleep(2000);
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
            try {
                System.out.println("queue.size()=" + queue.size());
                Thread.sleep(50);
                System.out.println("queue.size()=" + queue.size());
                Thread.sleep(1000);
                System.out.println(queue.dequeue());
                System.out.println(queue.dequeue());
                System.out.println(queue.dequeue());
                System.out.println(queue.dequeue());
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
                } catch (InterruptedException e) { }
            }
            if(this.queue.size() == 0){
                notifyAll();
            }
            this.queue.add(item);
        }
        public synchronized Object dequeue(){
            while(this.queue.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) { }
            }
            if(this.queue.size() == this.limit){
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
