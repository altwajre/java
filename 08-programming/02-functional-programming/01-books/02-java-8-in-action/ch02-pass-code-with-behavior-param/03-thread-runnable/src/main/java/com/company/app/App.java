package com.company.app;

/*
public interface Runnable{
  public void run();
}
 */
public class App
{
    public static void main( String[] args )
    {
        // anonymous class
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("anonymous class");
            }
        });
        thread1.start();

        // lambda expression
        Thread thread2 = new Thread(() -> System.out.println("lambda expression"));
        thread2.start();
    }
}
/*
output:
anonymous class
lambda expression
 */