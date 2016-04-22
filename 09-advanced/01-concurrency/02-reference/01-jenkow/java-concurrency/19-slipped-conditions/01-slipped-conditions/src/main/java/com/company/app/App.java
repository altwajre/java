package com.company.app;

import java.util.HashSet;
import java.util.Set;

/*
http://tutorials.jenkov.com/java-concurrency/slipped-conditions.html

Slipped Conditions
The condition is not tested and changed within the same synchronized code block. One Thread can change the condition
outside the synchronized code block will cause slipped conditions.

=================================
For example below, thread_1 has slipped from the time the condition was checked until the thread change it for
subsequent threads, because before Thread_1 change the condition, Thread_2 is already checked the same condition.

output:
[Thread_2 isLocked=false, Thread_1 isLocked=false]

 */
public class App 
{
    static class Lock{
        boolean isLocked = false;
        Set<String> isLockedList = new HashSet<String>();
        public void lock() throws InterruptedException {
            synchronized (this){
                isLockedList.add(Thread.currentThread().getName() + " isLocked=" + isLocked);
                while(isLocked){
                    wait();
                }
            }
            Thread.sleep(100); // wait a bit to, so the slipped conditions occur
            synchronized (this){
                isLocked = true;
            }
        }
        public void unlock(){
            notify();
            isLocked = false;
        }
    }
    public static void main( String[] args )
    {
        final Lock lock = new Lock();
        for(int i = 1; i <= 2; i++ ){
            Thread thread = new Thread("Thread_" + i){
                public void run(){
                    try {
                        lock.lock();
                    } catch (InterruptedException e) { }
                }
            };
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println(lock.isLockedList);
    }
}
