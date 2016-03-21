## Java Fork and Join using ForkJoinPool

```
http://tutorials.jenkov.com/java-util-concurrent/java-fork-and-join-forkjoinpool.html
The ForkJoinPool makes it easy for tasks to split their work up into smaller tasks which are then submitted to the
ForkJoinPool too. Tasks can keep splitting their work into smaller subtasks for as long as it makes to split up the task.
```

### Fork

```
A task that uses the fork and join principle can fork (split) itself into smaller subtasks which are be executed
concurrently. 
By splitting itself up into subtasks, each subtask can be executed in parallel by different CPUs, or different threads
on the same CPU.
```

### Join

```
When a task has split itself up into subtasks, the task waits until the subtasks have finishd executing.
Once the subtasks have finished executing, the task may join (merge) all the results into one reusult.
```