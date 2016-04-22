# Race Conditions and Critical Sections

## Critical Sections

```
A race condition may arise when multiple threads access the same resources. For instance, the same memory (variables, 
arrays, or objects), systems (databases, web services etc.) or files. In fact, problems only arise if one or more of 
the threads write to these resources. It is safe to let multiple threads read the same resources, as long as the 
resources do not change.
class Counter {
    long count = 0;
    public long getCount() {
        return count;
    }
    public void add(long value){ // critical section leads to race conditions
        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        this.count = this.count + value;
    }
}
```

## Preventing Race Conditions

```
To prevent race conditions from occurring you must make sure that the critical section is executed as an atomic 
instruction. That means that once a single thread is executing it, no other threads can execute it until the first
thread has left the critical section.
class Counter {
    long count = 0;
    public long getCount() {
        return count;
    }
    public synchronized void add(long value){ // synchronized critical section
        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        this.count = this.count + value;
    }
}
```

