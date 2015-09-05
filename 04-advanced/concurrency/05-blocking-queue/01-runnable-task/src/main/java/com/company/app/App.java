package com.company.app;

import java.util.LinkedList;
import java.util.List;

/*
http://tutorials.jenkov.com/java-concurrency/blocking-queues.html

Usage:
A thread produces objects, which another thread consumes the objects.

A thread try to dequeue an item from an empty queue is blocked until some other thread inserts an item into queue.
A thread try to enqueue an item in a full queue is blocked until some other thread makes space in the queue.

 */
public class App 
{
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
            while(queue.size() == limit){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(queue.size() == 0){  // wake all waiting threads
                notifyAll();
            }
            queue.add(item);
        }
        public synchronized Object dequeue(){
            while(queue.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(queue.size() == limit){  // wake all waiting threads
                notifyAll();
            }
            return queue.remove(0);
        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
