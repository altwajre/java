package com.company.app;

import java.util.function.Supplier;

class Heavy{
    public Heavy(){
        System.out.println("Heavy created");
    }
    public String toString(){
        return "quite heavy";
    }
}
class HolderNaive{
    private Heavy heavy;
    public HolderNaive(){
        System.out.println("Holder created");
    }
    public Heavy getHeavy(){
        if(heavy == null){
            heavy = new Heavy();
        }
        return heavy;
    }
}
class HolderThreadSafe{
    private Heavy heavy;
    public HolderThreadSafe(){
        System.out.println("Holder created");
    }
    public synchronized Heavy getHeavy(){
        if(heavy == null){
            heavy = new Heavy();
        }
        return heavy;
    }
}
// lazy initialization, avoid null checks, ensure thread safety; visual proxy pattern
class Holder{
    private Supplier<Heavy> heavy = () -> createAndCacheHeavy();
    public Holder(){
        System.out.println("Holder created");
    }
    public Heavy getHeavy(){
        return heavy.get();
    }
    /*
    synchronized only blocks the first time when multiple threads try to enter the createAndCacheHeavy()
    and only one thread can enter to create a HeavyFactory and caches it
    all other threads will get then same heavy instance from the cached HeavyFactory instance heavyFactory.get()
     */
    private synchronized Heavy createAndCacheHeavy(){
        class HeavyFactory implements Supplier<Heavy>{
            private final Heavy heavyInstance = new Heavy();
            @Override
            public Heavy get() {
                return heavyInstance;
            }
        }
        // it will create the a HeavyFactory instance the first time and cache it
        if(!HeavyFactory.class.isInstance(heavy)){
            heavy = new HeavyFactory();
        }
        return heavy.get();
    }
}
public class App
{
    private static void functionalVersion() {
        final Holder holder = new Holder();
        System.out.println("deferring heavy creation...");
        Heavy heavy1 = holder.getHeavy();
        System.out.println("heavy1: "+heavy1);
        Heavy heavy2 = holder.getHeavy();
        System.out.println("heavy2: "+heavy2);
        System.out.printf("heavy1 and heavy2 are same object ? %s\n", heavy1 == heavy2);
    }

    public static void main( String[] args )
    {
        System.out.println("#notThreadSafety");
        notThreadSafety();

        System.out.println("#threadSafety");
        threadSafety();

        System.out.println("#functionalVersion");
        functionalVersion();
    }

    /*
    Issue: endure the synchronization overhead
    The possibility of the race condition is so short lived it can happen only when the heavy reference is first being
    assigned, and the synchronization approach is a rather heavy-handed solution. We need thread safety until the
    reference is first created, and free unhindered access to the reference after that.
     */
    private static void threadSafety() {
        final HolderThreadSafe holder = new HolderThreadSafe();
        System.out.println("deferring heavy creation...");
        Heavy heavy1 = holder.getHeavy();
        System.out.println("heavy1: "+heavy1);
        Heavy heavy2 = holder.getHeavy();
        System.out.println("heavy2: "+heavy2);
        System.out.printf("heavy1 and heavy2 are same object ? %s\n", heavy1 == heavy2);
    }

    /*
    If two or more threads call the getHeavy method at the same time, then we could have multiple Heavy instances,
    potentially one per thread. This side effect is undesirable.
     */
    private static void notThreadSafety() {
        final HolderNaive holder = new HolderNaive();
        System.out.println("deferring heavy creation...");
        Heavy heavy1 = holder.getHeavy();
        System.out.println("heavy1: "+heavy1);
        Heavy heavy2 = holder.getHeavy();
        System.out.println("heavy2: "+heavy2);
        System.out.printf("heavy1 and heavy2 are same object ? %s\n", heavy1 == heavy2);
    }
}
/*
output:
#notThreadSafety
Holder created
deferring heavy creation...
Heavy created
heavy1: quite heavy
heavy2: quite heavy
heavy1 and heavy2 are same object ? true
#threadSafety
Holder created
deferring heavy creation...
Heavy created
heavy1: quite heavy
heavy2: quite heavy
heavy1 and heavy2 are same object ? true
#functionalVersion
Holder created
deferring heavy creation...
Heavy created
heavy1: quite heavy
heavy2: quite heavy
heavy1 and heavy2 are same object ? true
 */
