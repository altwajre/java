package com.company.app;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class App
{
    static class ReverseIterable<T> implements Iterable<T>{
        private List<T> list;
        public ReverseIterable(T[] t){
            list = Arrays.asList(t);
            Collections.reverse(list);
        }
        public Iterator<T> iterator() {
            return list.iterator();
        }
    }
    public static void main( String[] args )
    {
        Integer[] ints = {1, 2, 3};
        ReverseIterable<Integer> myList = new ReverseIterable<Integer>(ints);
        for(Integer i : myList){
            System.out.println(i);
        }
    }
}
