package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import java.util.List;

interface SortStrategy {
    void sort(List<Integer> list);
}
class QuickSort implements SortStrategy{
    public void sort(List<Integer> list) {
        System.out.println("QuickSort.sort()");
    }
}
class MergeSort implements SortStrategy{
    public void sort(List<Integer> list) {
        System.out.println("MergeSort.sort()");
    }
}
class SortModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(SortStrategy.class).to(QuickSort.class); // Service binding
    }
}
public class App {
    public static void main(String[] args){
        SortStrategy sortStrategy = Guice.createInjector(new SortModule())
                .getInstance(SortStrategy.class);
        sortStrategy.sort(null);
    }
}
/*
Configuration:
Guice provide the notion of explicit configuration, a central directory of all the services and dependencies.
Services are registered by using bind(), and making a sequence of calls that configure Guice accordingly.

output:
QuickSort.sort()
 */