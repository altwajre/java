package com.company.app;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicStampedReference;

/*
http://tutorials.jenkov.com/java-concurrency/non-blocking-algorithms.html

 */
public class App 
{
    static class NonblockingTempate{
        public static class IntendedModification{
            boolean initialValue = false;
            public AtomicBoolean completed = new AtomicBoolean(initialValue);
        }
        // AtomicStampedReference(V initialRef, int initialStamp) {...}
        private AtomicStampedReference<IntendedModification> ongoingMod = new AtomicStampedReference<IntendedModification>(null, 0);
        // declare the state of the data structure here.
        public void modify(){
            while(!attemptModifyASR());
        }
        public boolean attemptModifyASR(){
            boolean modified = false;
            IntendedModification currentlyOngoingMod = ongoingMod.getReference();
            int stamp = ongoingMod.getStamp();
            if(currentlyOngoingMod == null){
                // copy data structure state - for use
                // in intended modification
                // prepare intended modification
                IntendedModification newMod = new IntendedModification();
                // compareAndSet(V expectedReference, V newReference, int expectedStamp, int newStamp) {}
                boolean modSubmitted = ongoingMod.compareAndSet(null, newMod, stamp, stamp + 1);
                if(modSubmitted){
                    // complete modification via a series of compare-and-swap operations.
                    // note: other threads may assist in completing the compare-and-swap
                    // operations, so some CAS may fail
                    modified = true;
                }
            }
            else{
                // attempt to complete ongoing modification, so the data structure is fired up
                // to allow access from this thread.
                modified = false;
            }
            return modified;
        }
    }
    public static void main( String[] args )
    {
    }
}
