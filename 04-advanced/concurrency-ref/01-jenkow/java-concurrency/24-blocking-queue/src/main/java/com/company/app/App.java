package com.company.app;

import java.util.LinkedList;
import java.util.List;

/*
 */
public class App 
{
    static class BlockingQueue{
        private List queue = new LinkedList();
        private int limit = 10;
        public BlockingQueue(int limit){
            this.limit = limit;
        }
        public void enqueue(Object item){
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
        public Object dequeue(){
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
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
