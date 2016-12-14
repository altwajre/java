package com.company.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class Data{}

/*
Variable count is not final and is updated concurrently by more than one thread. So it is very possible that it will get
out of sync and read an invalid or corrupt count.
 */
class MessageCounter1{
    private int count;
    public void messageReceived(){
        count++;
    }
}

/*
The singleton-scoped instance of MessageCounter itself acts as the lock. Each message-receiving thread must wait for its
turn in order to acquire the lock, increment the count, and release it. This solution works for simple case.
But what happens when lots of threads going through the counter. Every one of them must wait to acquire and release the
lock before it can processed with handling the message. This can lead to a serious bottleneck.
 */
class MessageCounter2{
    private int count = 0;
    public synchronized void messageReceived(){
        count++;
    }
}

/*
Concurrent algorithms and data structures are designed with speed and scalability in mind. Multiple threads may liberally
hit the critical code without suffering the single-file throughput problems by taking advantage of some special atomic
operations provided by modern CPUs. Atomic operations execute in one go on the CPU and cannot be interrupted by another
thread being prioritized while they’re executing. Atomic operations are thus somewhat like a very small block of
synchronized code that performs just one instruction.
 */
class MessageCounter{
    final AtomicInteger count = new AtomicInteger(0);
    public void messageReceived(){
        count.incrementAndGet();
    }
}

/*
BAD: SimpleCache uses a synchronized wrapper around a simple hashtable. This wrapper has a single lock that is acquired on
every get() and every put() operation. This is done to safely publish values to all threads.
However, SimpleCache is extremely slow since every thread must wait for the hashmap to perform a lookup operation and
release the lock held by the current thread.put() operations can be even worse since they can involve resizing the
underlying array when it becomes full. In any high-traffic environment, this is unacceptably underperformant.
 */
class SimpleCache{
    final Map<String, Data> map = Collections.synchronizedMap(new HashMap<>());
    public void set(String key, Data val){
        map.put(key, val);
    }
    public Data get(String key){
        return map.get(key);
    }
}

/*
GOOD: This is a special hashtable implementation because it doesn't use locking to read from the map at all! It allows
multiple threads to make progress to the same values without requiring a coarse-grained lock as synchronized hashtables
do. Instead it locks only on insertion operations but using more fine-grained locks distributed across the hashtable.
 */
class ConcurrentCache{
    final Map<String, Data> map = new ConcurrentHashMap<>();
    public void set(String key, Data val){
        map.put(key, val);
    }
    public Data get(String key){
        return map.get(key);
    }
}

public class App {
    public static void main(String... args){
    }
}
