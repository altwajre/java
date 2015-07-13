package com.company.app;

import java.util.Stack;

/*
Q: design a stack has a function min which returns the minimum element.

A: push node-with-min in stack to track min at each state
|5| (5) node state min = 5
--------------------------
|6| (6) node state min = 5
|5|
--------------------------
|3| (3) node state min = 3
|6|
|5|
--------------------------
|7| (7) node state min = 3
|3|
|6|
|5|
--------------------------
 */
public class App 
{
    static class NodeWithMin{
        public int value;
        public int min;
        public NodeWithMin(int value, int min){
            this.value = value;
            this.min = min;
        }
    }
    static class StackWithMin extends Stack<NodeWithMin>{
        public void push(int value){
            int newMin = Math.min(value, min());
            super.push(new NodeWithMin(value, newMin));
        }
        public int min(){
            if(this.isEmpty()){
                return Integer.MAX_VALUE;  // Error value
            }
            else{
                return peek().min;
            }
        }
    }
    public static void main( String[] args )
    {
        int[] numbers = {5,6,3,7};
        StackWithMin stack = new StackWithMin();
        for(int n : numbers){
            stack.push(n);
            System.out.print(stack.min() + " ");
        }
        stack.pop();
        System.out.print(stack.min() + " ");
        stack.pop();
        System.out.print(stack.min() + " ");
        System.out.println("");
    }
}
