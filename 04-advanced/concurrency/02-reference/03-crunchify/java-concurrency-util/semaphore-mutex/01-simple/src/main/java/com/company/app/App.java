package com.company.app;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/*
http://crunchify.com/what-is-java-semaphore-and-mutex-java-concurrency-multithread-explained-with-example/

Mutex (Just 1 thread):
Mutexes are typically used to serialise access to a section of re-entrant code that cannot be executed concurrently by
more than one thread. A mutex object only allows one thread into a controlled section, forcing other threads which
attempt to gain access to that section to wait until the first thread has exited from that section.

Semaphore (N specified threads):
A semaphore restricts the number of simultaneous users of a shared resource up to a maximum number. Threads can request
access to the resource (decrementing the semaphore), and can signal that they have finished using the resource
(incrementing the semaphore).

When the Producer adds threadName to list linkedList object it can signal the semaphore. The Consumer can then be trying
to acquire the semaphore so they will be waiting until the Producer has signalled a threadID has been added. Upon
signalling a added data, one of the consumers will be woken and it will know it can read a list Object. It can read a
list, then go back to trying to acquire on the semaphore. If in that time the producer has written another packet it has
signalled again and either of the consuemers will then go on to read another packet and so on...
 */
public class App
{
    static LinkedList<String> list = new LinkedList<>();
    /*
    Semaphore maintains a set of permits.
    Each acquire blocks if necessary until a permit is available, and then takes it.
    Each release adds a permit, potentially releasing a blocking acquirer.
     */
    static Semaphore semaphore = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);
    // produce new Integer every time
    static class Producer extends Thread{
        public void run(){
            int counter = 1;
            try{
                while(true){
                    String threadName = Thread.currentThread().getName() + counter++;
                    mutex.acquire();
                    list.add(threadName);
                    System.out.println("Producer is producing new value: " + threadName);
                    mutex.release();
                    semaphore.release(); // release lock
                    Thread.sleep(500);
                }
            }
            catch (Exception e){ e.printStackTrace(); }
        }
    }
    // consume Integer every time
    static class Consumer extends Thread{
        String consumerName;
        public Consumer(String name){
            this.consumerName = name;
        }
        public void run(){
            try{
                while(true){
                    /*
                    acquire lock. Acquires the given number of permits from this semaphore, blocking until all are available
                    process stops here until producer releases the lock
                     */
                    semaphore.acquire();
                    // Acquires a permit from this semaphore, blocking until one is available
                    mutex.acquire();
                    String result = "";
                    for(String value : list){
                        result = value + ",";
                    }
                    System.out.println(consumerName + " consumes value: " + result + "list.size(): " + list.size() + "\n");
                    mutex.release();
                }
            }
            catch (Exception e){ e.printStackTrace(); }
        }
    }
    public static void main( String[] args )
    {
        new Producer().start();
        new Consumer("Crunchify").start();
        new Consumer("Google").start();
        new Consumer("Yahoo").start();
    }
}
/*
output:
Producer is producing new value: Thread-01
Google consumes value: Thread-01,list.size(): 1

Producer is producing new value: Thread-02
Crunchify consumes value: Thread-02,list.size(): 2

Producer is producing new value: Thread-03
Google consumes value: Thread-03,list.size(): 3

Producer is producing new value: Thread-04
Yahoo consumes value: Thread-04,list.size(): 4

Producer is producing new value: Thread-05
Crunchify consumes value: Thread-05,list.size(): 5

Producer is producing new value: Thread-06
Google consumes value: Thread-06,list.size(): 6

Producer is producing new value: Thread-07
Yahoo consumes value: Thread-07,list.size(): 7

Producer is producing new value: Thread-08
Crunchify consumes value: Thread-08,list.size(): 8
 */