package com.company.app;

/*
https://www.youtube.com/watch?v=igc5_JXHZDY&list=PLeLNWvESQ0GaUPY9Qh4uCiYR7haNFZgle

 */
public class App
{
    static class IdleThread extends Thread{
        private Object task = null;
        public IdleThread(){}
        public void run(){
            // wait for something to do ...
            waitForTask();
        }
        private synchronized void waitForTask(){
            while(task == null){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public Object getTask(){
            return task;
        }
        public void setTask(Object task){
            this.task = task;
        }
    }
    static final int MAX_NB_THREADS = 5000;

    public static void main( String[] args )
    {
        int threadCount = 0;
        try{
            for(int i = 0; i < MAX_NB_THREADS; i++){
                IdleThread thread = new IdleThread();
                thread.setDaemon(true);
                thread.start();
                threadCount++;
            }
        }
        catch (Throwable e){
            System.out.println("ERROR while creating new Java Thread: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            System.out.println("Java Thread Created: " + threadCount);
            System.out.println("Java Heap Capacity " + (Runtime.getRuntime().maxMemory()/1024) + " MB");
        }
        System.out.println( "done!" );
    }
}
