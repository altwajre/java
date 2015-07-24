package com.company.app;

import java.util.LinkedList;

/*
Q: An animal shelter holds only dogs and cats, and operates on a strictly "first in, first out" basis.

 */
public class App 
{
    static abstract class Animal{
        private int order;
        private String name;
        public Animal(String name){ this.name = name; }
        public void setOrder(int order){ this.order = order; }
        public int getOrder(){ return order; }
        public String getName() {
            return name;
        }
        public boolean isOlderThan(Animal a){ return this.order < a.getOrder(); }
    }
    static class Dog extends Animal{ public Dog(String n) { super(n); } }
    static class Cat extends Animal{ public Cat(String n) { super(n); } }
    static class AnimalQueue{
        LinkedList<Dog> dogs = new LinkedList<Dog>();
        LinkedList<Cat> cats = new LinkedList<Cat>();
        private int order = 0;  // acts as timestamp
        public LinkedList<Dog> getDogs() { return dogs; }
        public LinkedList<Cat> getCats() { return cats; }
        public void enqueue(Animal a){
            // Order is used as a sort of timestamp, so that we can compare the insertion order of a dog to a cat.
            a.setOrder(order);
            order++;
            if (a instanceof Dog) dogs.addLast((Dog) a);
            else if (a instanceof Cat) cats.addLast((Cat)a);
        }
        public Animal dequeueAny(){
            // Look at tops of dog and cat queues, and pop the stack with the oldest value.
            if(dogs.size() == 0){ return dequeueCats(); }
            else if(cats.size() == 0){ return dequeueDogs(); }
            Dog dog = dogs.peek();
            Cat cat = cats.peek();
            if(dog.isOlderThan(cat)){ return dequeueDogs(); }
            else{ return dequeueCats(); }
        }
        public Dog dequeueDogs(){
            return dogs.poll();  // .poll() retrieves and removes the head (first element) of this list
        }
        public Cat dequeueCats(){
            return cats.poll();  // .poll() retrieves and removes the head (first element) of this list
        }
    }
    public static void main( String[] args )
    {
        AnimalQueue queue = new AnimalQueue();
        addAnimals(queue);

        printAnimals(queue);
        System.out.println("dequeueAny");
        queue.dequeueAny();
        printAnimals(queue);
        System.out.println("dequeueDogs");
        queue.dequeueDogs();
        printAnimals(queue);
        System.out.println("dequeueCats");
        queue.dequeueCats();
        printAnimals(queue);
    }

    private static void printAnimals(AnimalQueue queue) {
        printList(queue.getCats());
        printList(queue.getDogs());
        System.out.println("");
    }

    private static void addAnimals(AnimalQueue queue) {
        queue.enqueue(new Dog("dog1"));
        queue.enqueue(new Cat("cat1"));
        queue.enqueue(new Dog("dog2"));
        queue.enqueue(new Dog("dog3"));
        queue.enqueue(new Cat("cat2"));
    }

    private static void printList(LinkedList<? extends Animal> list) {
        for(Animal a : list){
            System.out.print(a.getOrder() + " " + a.getName() + "; ");
        }
    }
}
