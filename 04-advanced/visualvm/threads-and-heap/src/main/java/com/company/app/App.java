package com.company.app;

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
        int nbThreadCreated = 0;
        for(int i = 0; i < MAX_NB_THREADS; i++){
            IdleThread thread = new IdleThread();
            thread.setDaemon(true);

        }
        System.out.println( "Hello World!" );
    }
}
