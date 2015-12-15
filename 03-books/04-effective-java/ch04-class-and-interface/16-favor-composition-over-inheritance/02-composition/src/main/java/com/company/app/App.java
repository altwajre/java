package com.company.app;

import java.util.*;

// Reusable forwarding class
class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;
    public ForwardingSet(Set<E> s) { this.s = s; }
    public void clear() { s.clear(); }
    public boolean contains(Object o) { return s.contains(o); }
    public int size() { return s.size(); }
    public boolean isEmpty() { return s.isEmpty(); }
    public Iterator<E> iterator() { return s.iterator(); }
    public boolean add(E e) { return s.add(e); }
    public boolean remove(Object o) { return s.remove(o); }
    public boolean containsAll(Collection<?> c) { return s.containsAll(c); }
    public boolean addAll(Collection<? extends E> c) { return s.addAll(c); }
    public boolean retainAll(Collection<?> c) { return s.retainAll(c); }
    public boolean removeAll(Collection<?> c) { return s.removeAll(c); }
    public Object[] toArray() { return s.toArray(); }
    public <T> T[] toArray(T[] a) { return s.toArray(a); }
    @Override
    public boolean equals(Object o) { return s.equals(o); }
    @Override
    public int hashCode() { return s.hashCode(); }
    @Override
    public String toString() { return s.toString(); }
}
// Wrapper class - uses composition in place of inheritance
class InstrumentedSet<E> extends ForwardingSet<E>{
    private int addCount = 0;
    public InstrumentedSet(Set<E> s) { super(s); }
    @Override
    public boolean add(E e){
        addCount++;
        return super.add(e);
    }
    public boolean addAll(Collection<? extends E> c){
        addCount += c.size();
        return super.addAll(c);
    }
    public int getAddCount(){ return addCount; }
}
public class App
{
    public static void main( String[] args )
    {
        InstrumentedSet<String> s = new InstrumentedSet<String>(new HashSet<String>());
        s.addAll(Arrays.asList("Tom", "Dick", "Harry"));
        System.out.println(s.getAddCount());
    }
}
/*
output:
3

note:
The design of the InstrumentedSet class is enabled by the existence of the Set interface, which captures the
functionality of the HashSet class. Besides being robust, this design is extremely flexible. The InstrumentedSet class
imple- ments the Set interface and has a single constructor whose argument is also of type Set. In essence, the class
transforms one Set into another, adding the instru- mentation functionality. Unlike the inheritance-based approach,
which works only for a single concrete class and requires a separate constructor for each supported constructor in the
superclass, the wrapper class can be used to instrument any Set implementation and will work in conjunction with any
preexisting constructor:
Set<Date> s = new InstrumentedSet<Date>(new TreeSet<Date>(cmp));
Set<E> s2 = new InstrumentedSet<E>(new HashSet<E>(capacity));
 */
