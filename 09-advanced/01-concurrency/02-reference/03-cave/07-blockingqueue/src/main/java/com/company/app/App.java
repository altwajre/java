package com.company.app;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
https://www.youtube.com/watch?v=Vrt5LqpH2D0&index=7&list=PLBB24CFB073F1048E

producer thread puts item into queue, and consumer thread takes item out of queue.
 */
public class App
{
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    public static void main( String[] args )
    {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                producer();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                consumer();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
    private static void producer(){
        Random random = new Random();
        while(true){
            try {
                queue.put(random.nextInt(100));
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    private static void consumer(){
        Random random = new Random();
        while (true){
            try {
                Thread.sleep(random.nextInt(1000));
                Integer value = queue.take();
                System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
/*
output:
Taken value: 73; Queue size is: 10
Taken value: 79; Queue size is: 9
Taken value: 90; Queue size is: 9
Taken value: 76; Queue size is: 9
Taken value: 75; Queue size is: 9
Taken value: 21; Queue size is: 10
Taken value: 22; Queue size is: 9
Taken value: 51; Queue size is: 9
Taken value: 74; Queue size is: 10
Taken value: 16; Queue size is: 9
Taken value: 10; Queue size is: 10
Taken value: 85; Queue size is: 9
Taken value: 66; Queue size is: 9
 */