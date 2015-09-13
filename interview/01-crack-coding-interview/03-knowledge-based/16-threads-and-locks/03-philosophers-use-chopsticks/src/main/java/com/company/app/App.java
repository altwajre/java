package com.company.app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

Question:
In the famous dining philosophers problem, a bunch of philosophers are sitting around a circular table with one
chopstick between each of them. A philosopher needs both chopsticks to eat, and always picks up the left chopstick
before the right one. A deadlock could potentially occur if all the philosophers reached for the left chopstick at the
same time. Using threads and locks, implement a simulation of the dining philosophers problem that prevents deadlocks.

Answer:
1, lock chopstick
2, philosopher extends Thread to use chopstick

output:
Person 0: start eating
Person 2: start eating
Person 1: start eating
Person 1: gave up on eating
Person 1: start eating
Person 0: gave up on eating
Person 0: start eating
Person 2: eating
Person 0: gave up on eating
Person 0: start eating
    Person 2: DONE eating
Person 2: start eating
Person 1: gave up on eating
Person 1: start eating
Person 2: gave up on eating
Person 2: start eating
Person 0: gave up on eating
Person 0: start eating
Person 1: gave up on eating
Person 1: start eating
Person 0: gave up on eating
Person 0: start eating
Person 1: gave up on eating
Person 1: start eating
Person 2: eating
Person 0: gave up on eating
Person 0: start eating
    Person 2: DONE eating
Person 2: start eating
Person 2: gave up on eating
Person 2: start eating
Person 0: gave up on eating
Person 0: start eating
Person 2: gave up on eating
Person 2: start eating
Person 1: eating
Person 0: gave up on eating
Person 0: start eating
Person 2: gave up on eating
Person 2: start eating
    Person 1: DONE eating
Person 1: start eating
Person 1: eating
Person 2: gave up on eating
Person 2: start eating
Person 0: gave up on eating
Person 0: start eating
    Person 1: DONE eating
Person 1: start eating
Person 2: gave up on eating
Person 2: start eating
Person 1: gave up on eating
Person 1: start eating
Person 1: gave up on eating
Person 1: start eating
Person 1: gave up on eating
Person 1: start eating
Person 0: eating
Person 1: gave up on eating
    Person 0: DONE eating
Person 0: start eating
Person 2: gave up on eating
Person 2: start eating
Person 0: eating
    Person 0: DONE eating
Person 2: eating
    Person 2: DONE eating

 */
public class App 
{
    static class AssortedMethods{
        public static int randomInt(int n){
            double random = Math.random();
            return (int) (random * n);
        }
        public static int randomIntInRange(int min, int max){
            int n = max + 1 - min;
//            System.out.println(n);
            return randomInt(n) + min;
        }
    }
    static class Chopstick{
        private Lock lock;
        public Chopstick(){
            lock = new ReentrantLock();
        }
        public boolean pickUp(){
            return lock.tryLock();
        }
        public void putDown(){
            lock.unlock();
        }
    }
    static class Person extends Thread{
        private final int maxPause = 100;
        private int bites = 10; // number of times try to eat
        private Chopstick left;
        private Chopstick right;
        private int index;
        public Person(int i, Chopstick left, Chopstick right){
            index = i;
            this.left = left;
            this.right = right;
        }
        public void eat(){
            System.out.println("Person " + index + ": start eating");
            if(pickUp()){
                chew();
                putDown();
                System.out.println("    Person " + index + ": DONE eating");
            }
            else{
                System.out.println("Person " + index + ": gave up on eating");
            }
        }
        public boolean pickUp(){
            pause();
            if(!left.pickUp()){
                return false;
            }
            pause();
            if(!right.pickUp()){
                left.putDown();
                return false;
            }
            pause();
            return true;
        }
        public void chew(){
            System.out.println("Person " + index + ": eating");
            pause();
        }
        public void pause(){
            int pause = AssortedMethods.randomIntInRange(0, maxPause);
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void putDown(){
            left.putDown();
            right.putDown();
        }
        public void run(){
            for(int i = 0; i < bites; i++){
                eat();
            }
        }
    }
    static int size = 3;
    static int leftOf(int i){
        return i;
    }
    static int rightOf(int i){
        return (i + 1) % size;
    }
    public static void main( String[] args )
    {
        Chopstick[] chopsticks = new Chopstick[size + 1];
        for(int i = 0; i < size + 1; i++){
            chopsticks[i] = new Chopstick();
        }

        Person[] people = new Person[size];
        for(int i = 0; i < size; i++){
            Chopstick left = chopsticks[leftOf(i)];
            Chopstick right = chopsticks[rightOf(i)];
            people[i] = new Person(i, left, right);
        }

        for(int i = 0; i < size; i++){
            people[i].start();
        }
    }
}
