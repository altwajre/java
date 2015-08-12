package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/thread-safety.html

    output:
    Thread_1: Tom
    Thread_2: Tom
    Thread_2: Other name
    Thread_1: Other name
 */
public class App
{
    static class LocalObject{
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public LocalObject(String name){this.name = name;}
    }
    static void foo(){
        LocalObject localObject = new LocalObject("Tom");
        System.out.println(Thread.currentThread().getName() + ": " + localObject.getName());
        otherMethod(localObject);
        System.out.println(Thread.currentThread().getName() + ": " + localObject.getName());
    }
    static void otherMethod(LocalObject localObject){
        localObject.setName("Other name");
    }
    public static void main( String[] args )
    {
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                foo();
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                foo();
            }
        }.start();
    }
}
