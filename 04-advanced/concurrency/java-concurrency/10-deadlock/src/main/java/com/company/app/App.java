package com.company.app;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;

/*
http://tutorials.jenkov.com/java-concurrency/deadlock.html

deadlock occurs when run the program

Thread 1: parent.addChild(child); //locks parent
          --> child.setParentOnly(parent);

Thread 2: child.setParent(parent); //locks child
          --> parent.addChildOnly()
Thread_1 calls synchronized parent.addChild(child) will block other threads from access this method
which locks the parent object for access from other threads
Thread_2 calls synchronized child.setParent(parent) will block other threads from access this method
which locks the child object for access from other threads

 */
public class App 
{
    static class MonitorObject{}
    static class SharedSignal { // wait and notify
        public boolean isReady = false;
        MonitorObject monitorObject = new MonitorObject();
        public void doWait(){
            synchronized (monitorObject){
                try { monitorObject.wait(); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
        public void doNotify(){
            synchronized (monitorObject){
                isReady = true;
                monitorObject.notify();
            }
        }
    }
    static class TreeNode{
        public TreeNode(String data){
            this.data = data;
        }
        public String data;
        TreeNode parent = null;
        List children = new ArrayList<TreeNode>();
        public synchronized void addChild(TreeNode child){
            if(!this.children.contains(child)){
                this.children.add(child);
                // NOTE: add sleep to give time to other thread to lock child object
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                child.setParentOnly(this);
            }
        }
        public synchronized void addChildOnly(TreeNode child){
            if(!this.children.contains(child)){
                this.children.add(child);
            }
        }
        public synchronized void setParent(TreeNode parent){
            this.parent = parent;
            // NOTE: add sleep to give time to other thread to lock parent object
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            parent.addChildOnly(this);
        }
        public synchronized void setParentOnly(TreeNode parent){
            this.parent = parent;
        }
    }

    public static void main( String[] args )
    {
        final SharedSignal sharedSignal = new SharedSignal();
        final TreeNode parent = new TreeNode("parent");
        final TreeNode child = new TreeNode("child");

        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                parent.addChild(child);
                sharedSignal.doNotify();
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                child.setParent(parent);
                sharedSignal.doWait();
                System.out.println("NO deadlock");
            }
        }.start();
    }
}
