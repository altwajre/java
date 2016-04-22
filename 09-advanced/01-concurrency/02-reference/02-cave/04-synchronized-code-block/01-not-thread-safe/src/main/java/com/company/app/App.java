package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
NOT Thread Safe

https://www.youtube.com/watch?v=8sgDgXUUJ68&list=PLBB24CFB073F1048E&index=4

Problems:
1, list sizes are not 2000 due to did not use lock to ensure thread-safe
2, sometime exception occurs due to threads interleave
 */
class Worker{
    private Random random = new Random();
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();
    public void stageOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }
    public void stageTwo(){
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
PROBLEM: list sizes are not 2000 due to did not lock to ensure thread-safe
Starting...
Time taken: 2553
list1.size()=1994; list2.size()=1996

or

NOTE: sometime exception occurs due to threads interleave
Starting...
Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 823
	at java.util.ArrayList.add(ArrayList.java:459)
	at com.company.app.Worker.stageOne(App.java:17)
	at com.company.app.Worker.process(App.java:29)
	at com.company.app.App$1.run(App.java:46)
	at java.lang.Thread.run(Thread.java:745)
Time taken: 2528
list1.size()=1407; list2.size()=1411
 */
