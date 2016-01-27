package com.company.app;

import java.util.concurrent.atomic.AtomicReference;

/*
The AtomicReference class provides an object reference variable which can be read and written atomically. By atomic is
meant that multiple threads attempting to change the same AtomicReference (e.g. with a compare-and-swap operation) will
not make the AtomicReference end up in an inconsistent state. AtomicReference even has an advanced compareAndSet()
method which lets you compare the reference to an expected value (reference) and if they are equal, set a new reference
inside the AtomicReference object.
 */
public class App
{
    public static void main( String[] args )
    {
        testGetSet();
        testCompareAndSet();
    }

    private static void testCompareAndSet() {
        System.out.println("#testCompareAndSet");
        String initialReference = "initial value";
        AtomicReference<String> atomicStringReference = new AtomicReference<>(initialReference);
        String newReference = "new value";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);
    }

    private static void testGetSet() {
        System.out.println("#testGetSet");
        String initialReference = "initial value";
        AtomicReference<String> atomicStringReference = new AtomicReference<>(initialReference);
        System.out.println(atomicStringReference.get());
        atomicStringReference.set("new value");
        System.out.println(atomicStringReference.get());
    }
}
/*
output:
#testGetSet
initial value
new value
#testCompareAndSet
exchanged: true
exchanged: false
 */