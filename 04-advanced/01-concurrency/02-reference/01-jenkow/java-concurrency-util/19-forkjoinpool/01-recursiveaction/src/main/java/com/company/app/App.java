package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/*
http://tutorials.jenkov.com/java-util-concurrent/java-fork-and-join-forkjoinpool.html
 */
class MyRecursiveAction extends RecursiveAction{
    private long workLoad = 0;
    public MyRecursiveAction(long workLoad){ this.workLoad = workLoad; }
    @Override
    protected void compute() {
        String threadName = Thread.currentThread().getName();
        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16){
            System.out.println(threadName+ " splitting workLoad: " + this.workLoad);
            List<MyRecursiveAction> subtasks = new ArrayList<>();
            subtasks.addAll(createSubtasks());
            for(RecursiveAction subtask : subtasks){
                subtask.fork();
            }
        }
        else{
            System.out.println(threadName + " doing the work: " + this.workLoad);
        }
    }
    private List<MyRecursiveAction> createSubtasks(){
        List<MyRecursiveAction> subtasks = new ArrayList<>();
        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad /2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad /2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }
}
public class App
{
    public static void main( String[] args )
    {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        forkJoinPool.invoke(myRecursiveAction);
    }
}
/*
output:
ForkJoinPool-1-worker-1 splitting workLoad: 24
ForkJoinPool-1-worker-1 doing the work: 12
ForkJoinPool-1-worker-2 doing the work: 12
 */
