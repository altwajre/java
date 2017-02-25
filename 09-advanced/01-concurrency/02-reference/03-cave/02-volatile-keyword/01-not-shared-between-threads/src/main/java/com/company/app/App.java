package com.company.app;

import java.util.Scanner;

/*
Not Shared Between Threads

https://www.youtube.com/watch?v=_aNO6x8HXZ0&index=2&list=PLBB24CFB073F1048E

When the state variable running is NOT volatile, it may cache the value locally because it thought it's not shared
between threads.
The process.shutdown() in the main thread won't work if state variable running is cached locally because it will be
always true.
 */
class Processor extends Thread{
    private boolean running = true; // NOT volatile
    @Override
    public void run(){
        while (running){
            System.out.println(Thread.currentThread().getName() + " says hello");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    public void shutdown(){
        running = false;
        System.out.println(Thread.currentThread().getName() + " shutdown");
    }
}
public class App
{
    public static void main( String[] args )
    {
        Processor processor = new Processor();
        processor.start();

        System.out.println("Press return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }
}
/*
output:
Press return to stop...
Thread-0 says hello
Thread-0 says hello
Thread-0 says hello

main shutdown
 */