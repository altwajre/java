package com.company.app;

import java.util.concurrent.atomic.AtomicStampedReference;

/*
http://tutorials.jenkov.com/java-util-concurrent/atomicstampedreference.html

 */
public class App
{
    public static void main( String[] args )
    {
        testGetRefAndStamp();
        testGetRefAndStampAtomically();
        testSetRef();
        testCompareAndSet();
        testA_B_A();
    }

    /*
    The A-B-A problem is when a reference is changed from pointing to A, then to B and then back to A.
    When using compare-and-swap operations to change a reference atomically, and making sure that only one thread can
    change the reference from an old to a new, detecting the A-B-A situation is impossible.
    The A-B-A problem can occur in non-blocking algorithms. Non-blocking algorithms often use a reference to an ongoing
    modification to the guarded data structure, to signal other threads that a modification is currently ongoing. If
    thread_1 sees there is no ongoing modification (reference points to null), another thread may submit a modification
    (reference is now non-null), complete the modification and swap the reference back to null without thread_1
    detecting it. Exactly how the A-B-A problem occurs in non-blocking algorithm.
     */
    private static void testA_B_A() {
        String threadName = Thread.currentThread().getName();
        System.out.println("#testA_B_A");
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(null, 0);
        int[] stampHolder = new int[1];
        String ref = atomicStampedReference.get(stampHolder);
        if(ref == null){
            System.out.println(threadName + " prepares optimistic modification");
        }

        // if another thread changes the reference and stamp here, it can be detected
        new Thread("thread_2"){
            public void run(){
                System.out.println(Thread.currentThread().getName() + " is changing the reference...");
                String newRef = "changing the reference...";
                int newStamp = 1;
                atomicStampedReference.set(newRef, newStamp);
            }
        }.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] stampHolder2 = new int[1];
        String ref2 = atomicStampedReference.get(stampHolder2);
        if(ref == ref2 && stampHolder[0] == stampHolder2[0]){
            System.out.println(threadName+": no modification since optimistic modification was prepared");
        }
        else{
            System.out.println(threadName + ": reference was modified! retry from scratch");
        }
    }

    private static void testCompareAndSet() {
        System.out.println("#testCompareAndSet");
        String initialRef = "initial value referenced";
        int initialStamp = 0;
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(initialRef, initialStamp);
        String newRef = "new value referenced";
        int newStamp = initialStamp + 1;
        boolean exchanged = atomicStampedReference.compareAndSet(initialRef, newRef, initialStamp, newStamp);
        System.out.println("exchanged="+exchanged);
        exchanged = atomicStampedReference.compareAndSet(initialRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged="+exchanged);
        exchanged = atomicStampedReference.compareAndSet(newRef, "new string", initialStamp, newStamp + 1);
        System.out.println("exchanged="+exchanged);
        exchanged = atomicStampedReference.compareAndSet(newRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged="+exchanged);
        int[] stampHolder = new int[1];
        String reference = atomicStampedReference.get(stampHolder);
        System.out.println("reference="+reference);
        System.out.println("stamp="+stampHolder[0]);
    }

    private static void testSetRef() {
        System.out.println("#testSetRef");
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(null, 0);
        String newRef = "New object referenced";
        int newStamp = 1;
        atomicStampedReference.set(newRef, newStamp);
        int[] stampHolder = new int[1];
        String reference = atomicStampedReference.get(stampHolder);
        System.out.println("reference="+reference);
        System.out.println("stamp="+stampHolder[0]);
    }

    private static void testGetRefAndStampAtomically() {
        System.out.println("#testGetRefAndStampAtomically");
        String initialRef = "first text";
        int initialStamp = 0;
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(initialRef, initialStamp);
        int[] stampHolder = new int[1];
        String reference = atomicStampedReference.get(stampHolder);
        System.out.println("reference="+reference);
        System.out.println("stamp="+stampHolder[0]);
    }

    private static void testGetRefAndStamp() {
        System.out.println("#testGetRefAndStamp");
        String initialRef = "first text";
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(initialRef, 0);
        String reference = atomicStampedReference.getReference();
        System.out.println("reference="+reference);
        int stamp = atomicStampedReference.getStamp();
        System.out.println("stamp="+stamp);
    }
}
/*
output:
#testGetRefAndStamp
reference=first text
stamp=0
#testGetRefAndStampAtomically
reference=first text
stamp=0
#testSetRef
reference=New object referenced
stamp=1
#testCompareAndSet
exchanged=true
exchanged=false
exchanged=false
exchanged=true
reference=new string
stamp=2
#testA_B_A
main prepares optimistic modification
thread_2 is changing the reference...
main: reference was modified! retry from scratch
 */