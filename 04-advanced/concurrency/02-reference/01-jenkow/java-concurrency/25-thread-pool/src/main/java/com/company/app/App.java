package com.company.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/thread-pools.html

output:
  Thread-0: dequeue entered; size()=0
  Thread-0: dequeue wait(); size()=0
  Thread-2: dequeue entered; size()=0
  Thread-2: dequeue wait(); size()=0
  Thread-1: dequeue entered; size()=0
  Thread-1: dequeue wait(); size()=0
main: enqueue entered 1922154895; size()=0
main: enqueue notifyAll() 1922154895; size()=0
main: enqueue added 1922154895; size()=1
main: enqueue entered 1922154895; size()=1
main: enqueue added 1922154895; size()=2
main: enqueue entered 1922154895; size()=2
main: enqueue wait() 1922154895; size()=2
  Thread-1: dequeue EXIT wait(); size()=2
  Thread-1: dequeue notifyAll(); size()=2
  Thread-1: dequeue removed 1922154895; size()=1
  Thread-2: dequeue EXIT wait(); size()=1
    #Thread-1 finished task_1
  Thread-2: dequeue removed 1922154895; size()=0
    #Thread-2 finished task_2
  Thread-0: dequeue EXIT wait(); size()=0
  Thread-0: dequeue wait(); size()=0
  Thread-2: dequeue entered; size()=0
  Thread-2: dequeue wait(); size()=0
  Thread-1: dequeue entered; size()=0
  Thread-1: dequeue wait(); size()=0
main: enqueue EXIT wait() 1922154895; size()=0
main: enqueue notifyAll() 1922154895; size()=0
main: enqueue added 1922154895; size()=1
main: enqueue entered 1922154895; size()=1
main: enqueue added 1922154895; size()=2
main: enqueue entered 1922154895; size()=2
main: enqueue wait() 1922154895; size()=2
  Thread-1: dequeue EXIT wait(); size()=2
  Thread-1: dequeue notifyAll(); size()=2
  Thread-1: dequeue removed 1922154895; size()=1
    #Thread-1 finished task_3
  Thread-2: dequeue EXIT wait(); size()=1
  Thread-2: dequeue removed 1922154895; size()=0
    #Thread-2 finished task_4
  Thread-0: dequeue EXIT wait(); size()=0
  Thread-0: dequeue wait(); size()=0
  Thread-2: dequeue entered; size()=0
  Thread-2: dequeue wait(); size()=0
  Thread-1: dequeue entered; size()=0
  Thread-1: dequeue wait(); size()=0
main: enqueue EXIT wait() 1922154895; size()=0
main: enqueue notifyAll() 1922154895; size()=0
main: enqueue added 1922154895; size()=1
  Thread-1: dequeue EXIT wait(); size()=1
  Thread-1: dequeue removed 1922154895; size()=0
    #Thread-1 finished task_5
  Thread-1: dequeue entered; size()=0
  Thread-1: dequeue wait(); size()=0
  Thread-2: dequeue EXIT wait(); size()=0
  Thread-2: dequeue wait(); size()=0
  Thread-0: dequeue EXIT wait(); size()=0
  Thread-0: dequeue wait(); size()=0

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
            System.out.println(threadName + ": enqueue entered " + item.hashCode() + "; size()=" + queue.size());
            while(queue.size() == limit){
                try {
                    System.out.println(threadName + ": enqueue wait() " + item.hashCode() + "; size()=" + queue.size());
                    wait();
                    System.out.println(threadName + ": enqueue EXIT wait() " + item.hashCode() + "; size()=" + queue.size());
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
            if(queue.size() == 0){  // wake all waiting threads
                System.out.println(threadName + ": enqueue notifyAll() " + item.hashCode() + "; size()=" + queue.size());
                notifyAll();
            }
            queue.add(item);
            System.out.println(threadName + ": enqueue added " + item.hashCode() + "; size()=" + queue.size());
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
            System.out.println("  " + threadName + ": dequeue removed "+temp.hashCode()+"; size()=" + queue.size());
            return temp;
        }
    }
    static class PoolThread extends Thread{
        private BlockingQueue taskQueue = null;
        private boolean isStopped = false;
        public PoolThread(BlockingQueue queue){ taskQueue = queue; }
        public void run(){
            while(!isStopped){
                try{
                    Runnable runnable = (Runnable) taskQueue.dequeue(); // each thread is waiting for task to run
                    runnable.run();
                } catch (Exception e){ e.printStackTrace(); }
            }
        }
        public synchronized void doStop(){
            isStopped = true;
            this.interrupt();  // break pool thread out of dequeue() call.
        }
        public synchronized boolean isStopped(){ return isStopped; }
    }
    static class ThreadPool{
        private BlockingQueue taskQueue = null;
        private List<PoolThread> threads = new ArrayList<PoolThread>();
        private boolean isStopped = false;
        public ThreadPool(int noOfThreads, int maxNoOfTasks){
            taskQueue = new BlockingQueue(maxNoOfTasks);
            for(int i = 0; i < noOfThreads; i++){ threads.add(new PoolThread(taskQueue)); }
            for(PoolThread thread : threads){ thread.start(); }
        }
        public synchronized void execute(Runnable task) throws Exception{
            if(this.isStopped){ throw new IllegalStateException("ThreadPool is stopped"); }
            this.taskQueue.enqueue(task);
        }
        public synchronized void stop(){
            this.isStopped = true;
            for(PoolThread thread : threads){ thread.stop(); }
        }
    }
    static int taskCount = 1;
    public static void main( String[] args ) throws Exception {
        int noIfThreads = 3;
        int maxNoOfTasks = 2;
        ThreadPool threadPool = new ThreadPool(noIfThreads, maxNoOfTasks);
        int count = 1;
        Runnable task = () -> {
            System.out.println("    #"+Thread.currentThread().getName() + " finished task_" + taskCount++);
        };
        for(int i = 0; i < 5; i++){ threadPool.execute(task); }
        try{ TimeUnit.SECONDS.sleep(7);
        } finally { threadPool.stop(); }
    }
}
