package com.company.app;

import java.util.LinkedList;
import java.util.Random;

/*
https://www.youtube.com/watch?v=wm1O8EE0X8k&index=9&list=PLBB24CFB073F1048E

put wait() in while loop, so we can check the condition again after waked by notify()

scenario:
1, produce() method adds items to list, will wait once reach to limit
2, consume() method will take item out of list, will wait when list is empty
 */
class Processor{
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();
    public void produce() throws InterruptedException {
        int value = 0;
        while (true){
            synchronized (lock){
                while(list.size() == LIMIT){ // use while loop, so we can check size again after resume by notify()
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }
    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true){
            synchronized (lock){
                while (list.size() == 0){
                    lock.wait();
                }
                System.out.print("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
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
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
List size is: 10; value is: 0
List size is: 10; value is: 1
List size is: 10; value is: 2
List size is: 10; value is: 3
List size is: 10; value is: 4
List size is: 10; value is: 5
List size is: 10; value is: 6
List size is: 10; value is: 7
List size is: 10; value is: 8
List size is: 10; value is: 9
List size is: 10; value is: 10
List size is: 10; value is: 11
List size is: 10; value is: 12
List size is: 10; value is: 13
 */
