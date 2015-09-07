package com.company.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/thread-pools.html

output:
    Thread-0: PoolThread.run()
    Thread-2: PoolThread.run()
    Thread-1: PoolThread.run()
done
  Thread-0: dequeue() wait(); size=0
  Thread-1: dequeue() wait(); size=0
  Thread-2: dequeue() wait(); size=0
  main: enqueue() notifyAll(); size=0
  main: enqueue() queue.add(); size=0
  main: enqueue() queue.add(); size=1
  main: enqueue() wait(); size=2
  Thread-2: dequeue() EXIT wait(); size=2
  Thread-2: dequeue() notifyAll(); size=2
  Thread-2: dequeue() queue.remove(); size=2
  Thread-1: dequeue() EXIT wait(); size=1
Thread-2: before sleep
  Thread-1: dequeue() queue.remove(); size=1
Thread-1: before sleep
  Thread-0: dequeue() EXIT wait(); size=0
  Thread-0: dequeue() wait(); size=0
  main: enqueue() EXIT wait(); size=0
  main: enqueue() notifyAll(); size=0
  main: enqueue() queue.add(); size=0
  main: enqueue() queue.add(); size=1
  main: enqueue() wait(); size=2
  Thread-0: dequeue() EXIT wait(); size=2
  Thread-0: dequeue() notifyAll(); size=2
  Thread-0: dequeue() queue.remove(); size=2
Thread-0: before sleep
  main: enqueue() EXIT wait(); size=1
  main: enqueue() queue.add(); size=1
Thread-0: after sleep
Thread-1: after sleep
Thread-2: after sleep
  Thread-0: dequeue() notifyAll(); size=2
  Thread-0: dequeue() queue.remove(); size=2
Thread-0: before sleep
  Thread-2: dequeue() queue.remove(); size=1
Thread-2: before sleep
  Thread-1: dequeue() wait(); size=0
Thread-2: after sleep
  Thread-2: dequeue() wait(); size=0
Thread-0: after sleep
  Thread-0: dequeue() wait(); size=0

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
            String threadName = Thread.currentThread().getName();
            while(this.queue.size() == this.limit){
                try {
                    System.out.println("  "+threadName + ": enqueue() wait(); size=" + queue.size());
                    wait();
                    System.out.println("  " + threadName + ": enqueue() EXIT wait(); size=" + queue.size());
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
            if(this.queue.size() == 0){  // notify items that wait for enqueue
                System.out.println("  "+threadName + ": enqueue() notifyAll(); size=" + queue.size());
                notifyAll();
            }
            System.out.println("  "+threadName + ": enqueue() queue.add(); size=" + queue.size());
            this.queue.add(item);
        }
        public synchronized Object dequeue(){
            String threadName = Thread.currentThread().getName();
            while(this.queue.size() == 0){
                try {
                    System.out.println("  "+threadName + ": dequeue() wait(); size=" + queue.size());
                    wait();
                    System.out.println("  " + threadName + ": dequeue() EXIT wait(); size=" + queue.size());
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
            if(this.queue.size() == this.limit){  // notify items that wait for dequeue
                System.out.println("  "+threadName + ": dequeue() notifyAll(); size=" + queue.size());
                notifyAll();
            }
            System.out.println("  "+threadName + ": dequeue() queue.remove(); size=" + queue.size());
            return this.queue.remove(0);
        }
    }
    static class PoolThread extends Thread{
        private BlockingQueue taskQueue = null;
        private boolean isStopped = false;
        public PoolThread(BlockingQueue queue){ taskQueue = queue; }
        public void run(){
            System.out.println("    "+Thread.currentThread().getName() + ": PoolThread.run()");
            while(!isStopped){
                try{
                    Runnable runnable = (Runnable) taskQueue.dequeue();
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
            System.out.println("done");
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
    public static void main( String[] args ) throws Exception {
        int noIfThreads = 3;
        int maxNoOfTasks = 2;
        ThreadPool threadPool = new ThreadPool(noIfThreads, maxNoOfTasks);
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": before sleep");
            try { TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(threadName + ": after sleep");
        };
        for(int i = 0; i < 5; i++){ threadPool.execute(task); }
        try{ TimeUnit.SECONDS.sleep(7);
        } finally { threadPool.stop(); }
    }
}
