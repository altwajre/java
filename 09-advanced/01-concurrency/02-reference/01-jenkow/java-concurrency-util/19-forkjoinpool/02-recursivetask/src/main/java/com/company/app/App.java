package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
http://tutorials.jenkov.com/java-util-concurrent/java-fork-and-join-forkjoinpool.html

 */
class MyRecursiveTask extends RecursiveTask<Long>{
    private long workLoad = 0;
    public MyRecursiveTask(long workLoad){
        this.workLoad = workLoad;
    }
    @Override
    protected Long compute() {
        String threadName = Thread.currentThread().getName();
        // if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16){
            System.out.println(threadName+ " splitting workLoad: " + this.workLoad);
            List<MyRecursiveTask> subtasks = new ArrayList<>();
            subtasks.addAll(createSubtasks());
            for(MyRecursiveTask subtask : subtasks){
                subtask.fork();
            }
            long result = 0;
            for(MyRecursiveTask subtask : subtasks){
                result += subtask.join();
            }
            return result;
        }
        else{
            System.out.println(threadName + " doing the work: " + this.workLoad);
            return workLoad * 3;
        }
    }
    private List<MyRecursiveTask> createSubtasks(){
        List<MyRecursiveTask> subtasks = new ArrayList<>();
        MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
        MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);
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
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("mergedResult = " + mergedResult);
    }
}
/*
output:
ForkJoinPool-1-worker-1 splitting workLoad: 128
ForkJoinPool-1-worker-3 splitting workLoad: 64
ForkJoinPool-1-worker-2 splitting workLoad: 64
ForkJoinPool-1-worker-0 splitting workLoad: 32
ForkJoinPool-1-worker-3 splitting workLoad: 32
ForkJoinPool-1-worker-0 doing the work: 16
ForkJoinPool-1-worker-1 splitting workLoad: 32
ForkJoinPool-1-worker-3 doing the work: 16
ForkJoinPool-1-worker-2 doing the work: 16
ForkJoinPool-1-worker-4 doing the work: 16
ForkJoinPool-1-worker-4 doing the work: 16
ForkJoinPool-1-worker-3 doing the work: 16
ForkJoinPool-1-worker-0 splitting workLoad: 32
ForkJoinPool-1-worker-4 doing the work: 16
ForkJoinPool-1-worker-2 doing the work: 16
mergedResult = 384
 */
