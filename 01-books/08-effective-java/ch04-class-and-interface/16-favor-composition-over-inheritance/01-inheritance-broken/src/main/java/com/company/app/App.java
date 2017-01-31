package com.company.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

// Broken - Inappropriate use of inheritance!
class InstrumentedHashSet<E> extends HashSet<E>{
    // The number of attempted element insertions
    private int addCount = 0;
    public InstrumentedHashSet(){}
    public InstrumentedHashSet(int initCap, float loadFactor){
        super(initCap, loadFactor);
    }
    @Override
    public boolean add(E e){
        System.out.println("#add()");
        addCount++;
        return super.add(e);
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
        System.out.println("#addAll()");
        addCount += c.size();
        return super.addAll(c); // HashSet’s addAll method is implemented on top of its add method
    }
    /* super.addAll(c) as below
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
    }
    */
    public int getAddCount(){
        return addCount;
    }
}
public class App
{
    public static void main( String[] args )
    {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<String>();
        s.addAll(Arrays.asList("Tom", "Dick", "Harry"));
        System.out.println(s.getAddCount());
    }
}
/*
output: broken because expected 3, but it is 6
6

note: comment out the override addAll() will fix the issue
This “self-use” is an implementation detail, not guaranteed to hold in all imple- mentations of the Java platform and
subject to change from release to release. Therefore, the resulting InstrumentedHashSet class would be fragile.
 */
