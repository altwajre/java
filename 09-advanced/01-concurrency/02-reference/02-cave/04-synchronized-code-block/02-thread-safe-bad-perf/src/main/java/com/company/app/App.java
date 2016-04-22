package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Thread Safe, but BAD Performance

https://www.youtube.com/watch?v=8sgDgXUUJ68&list=PLBB24CFB073F1048E&index=4

Problems:
It takes twice much of time to run because only thread can get worker instance lock which means only one of methods can
 be accessed a thread even there are multiple independent thread-safe methods in worker instance.
 */
class Worker{
    private Random random = new Random();
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();
    public synchronized void stageOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }
    public synchronized void stageTwo(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }
    public void process(){
        for(int i = 0; i < 1000; i++){
            stageOne();
            stageTwo();
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Worker worker = new Worker();

        System.out.println("Starting...");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                worker.process();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                worker.process();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("list1.size()=" + worker.list1.size() + "; list2.size()=" + worker.list2.size());
    }
}
/*
output:
Starting...
Time taken: 5254   <-- Problem: it takes twice much of time. it should only take a bit more than 2 seconds
list1.size()=2000; list2.size()=2000
 */
