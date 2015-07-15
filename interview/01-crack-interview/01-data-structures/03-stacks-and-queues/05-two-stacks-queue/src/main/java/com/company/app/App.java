package com.company.app;

import java.util.Stack;

/*
Q: implements a queue using two stacks

 */
public class App 
{
    static class MyQueue<T>{
        Stack<T> stackNewest, stackOldest;

        public Stack<T> getStackNewest() {
            return stackNewest;
        }

        public Stack<T> getStackOldest() {
            return stackOldest;
        }

        public MyQueue(){
            stackNewest = new Stack<T>();
            stackOldest = new Stack<T>();
        }
        public int size(){
            return stackNewest.size() + stackNewest.size();
        }
        public void add(T value){
            // Push onto stackNewest, which always has the newest elements on top
            stackNewest.push(value);
        }
        /*
        Move elements from stackNewest into stackOldest.
        This is usually done so that we can do operations on stackOldest.
         */
        private void shiftStacks(){
            if(stackOldest.isEmpty()){
                while (!stackNewest.isEmpty()){
                    stackOldest.push(stackNewest.pop());
                }
            }
        }
        public T peek(){
            shiftStacks();  // Ensure stackOldest has the current elements
            return stackOldest.peek();  // retrieve the oldest item.
        }
        public T remove(){
            shiftStacks();  // Ensure stackOldest has the current elements
            return stackOldest.pop();  // pop the oldest item.
        }
    }
    public static void main( String[] args )
    {
        int[] numbers = {1,2,3};
        MyQueue<Integer> queue = new MyQueue<Integer>();
        Stack<Integer> stackNewest = queue.getStackNewest();
        Stack<Integer> stackOldest = queue.getStackOldest();
        for(int n : numbers){
            queue.add(n);
        }
        printStacks(stackNewest, stackOldest);
        System.out.println("#remove " + queue.remove());
        printStacks(stackNewest, stackOldest);
        System.out.println("#add 11");
        queue.add(11);
        printStacks(stackNewest, stackOldest);
        System.out.println("#remove " + queue.remove());
        printStacks(stackNewest, stackOldest);
        System.out.println("#remove " + queue.remove());
        printStacks(stackNewest, stackOldest);
        System.out.println("#remove " + queue.remove());
        printStacks(stackNewest, stackOldest);
    }

    private static void printStacks(Stack<Integer> stackNewest, Stack<Integer> stackOldest) {
        printStack(stackNewest, "stackNewest");
        printStack(stackOldest, "stackOldest");
    }

    private static void printStack(Stack<Integer> stack, String name) {
        System.out.println("--Print " + name + " disks--");
        if(stack.isEmpty()){
            System.out.println("Empty");
            return;
        }
        for(int i = stack.size() - 1; i >= 0; i--){
            System.out.println(stack.get(i));
        }
    }
}
