package com.company.app;

import java.util.Scanner;

/*
wait and notify

https://www.youtube.com/watch?v=gx_YUORX5vk&list=PLBB24CFB073F1048E&index=8

wait() and notify() only can be called within synchronized code block

wait()
1, when wait() is called, the thread is paused and the lock (this) is released, so other thread can acquire the lock.
2, in order to let wait() to resume, notify() on this lock (this) need to be called

notify()
1, it will wake up the wait thread, and the wait thread code will be resumed only after notify synchronized code block
is finished.
 */
class Processor{
    public void produce(){
        synchronized (this){
            System.out.println("Producer thread running...");
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("Producer thread Resumed");
        }
    }
    public void consume(){
        Scanner scanner = new Scanner(System.in);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){ // it will gain the lock (this) after wait() is called in produce()
            System.out.println("Consumer waiting for return key.");
            scanner.nextLine();
            System.out.println("Consumer return key pressed.");
            notify();  // it won't the release the lock (this) right away; the wait thread will be resume after synchronized code block is finished.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Processor processor = new Processor();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                processor.produce();
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                processor.consume();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
Producer thread running...
Consumer waiting for return key.

Consumer return key pressed.
Producer thread Resumed
 */
