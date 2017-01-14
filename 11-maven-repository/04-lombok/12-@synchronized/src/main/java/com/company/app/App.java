package com.company.app;

import lombok.Synchronized;

class SynchronizedExample{
    private final Object readLock = new Object();

    @Synchronized
    public static void hello(){
        System.out.println("world");
    }

    @Synchronized
    public int answerToLife(){
        return 42;
    }

    @Synchronized("readLock")
    public void foo(){
        System.out.println("bar");
    }
}
public class App {
    public static void main(String... args){
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        synchronizedExample.hello();
    }
}
/*
https://projectlombok.org/features/Synchronized.html
@Synchronized is a safer variant of the synchronized method modifier. Like synchronized, the annotation can be used on
static and instance methods only. It operates similarly to the synchronized keyword, but it locks on different objects.
The keyword locks on this, but the annotation locks on a field named $lock, which is private.
If the field does not exist, it is created for you. If you annotate a static method, the annotation locks on a static
field named $LOCK instead.

output:
world
 */