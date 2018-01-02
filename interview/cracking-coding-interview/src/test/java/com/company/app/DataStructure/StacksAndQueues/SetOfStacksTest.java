package com.company.app.DataStructure.StacksAndQueues;

import org.junit.Test;

import java.util.ArrayList;

/*
Q: start a new stack when the previous stack exceeds some threshold.
SetOfStacks should be composed of several stacks and should create a new stack once the previous one exceeds capacity.
SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack.

 */
public class SetOfStacksTest {

    @Test
    public void Test() {
        int[] numbers = {1,2,3,11,22};
        SetOfStacks stacks = new SetOfStacks(3);
        for(int n : numbers){ stacks.push(n); }
        printStacks(stacks);

        System.out.println("#pop " + stacks.pop());
        printStacks(stacks);
        System.out.println("#pop " + stacks.pop());
        printStacks(stacks);
        System.out.println("#pop " + stacks.pop());
        printStacks(stacks);
        System.out.println("#pop " + stacks.pop());
        printStacks(stacks);
    }

    static class Node{
        public Node(int data){this.data = data;}
        public int data;
        public Node above;
        public Node below;
    }
    static class Stack{
        private int capacity;
        public Node top, bottom;
        public int size = 0;
        public Stack(int capacity){this.capacity = capacity;}
        public boolean isFull() {return capacity == size;}
        public void join(Node above, Node below){
            if(above != null) above.below = below;
            if(below != null) below.above = above;
        }
        public boolean push(int v){
            if(size >= capacity) return false;
            size++;
            Node n = new Node(v);
            if(size == 1) bottom = n;
            join(n, top);
            top = n;
            return true;
        }
        public int pop(){
            Node t = top;
            top = top.below;
            size--;
            return t.data;
        }
        public boolean isEmpty(){ return size == 0; }
        public int removeBottom(){
            Node b = bottom;
            bottom = bottom.above;
            if(bottom != null) bottom.below = null;
            size--;
            return b.data;
        }
    }
    static class SetOfStacks{
        ArrayList<Stack> stacks = new ArrayList<Stack>();
        public int capacity;
        public SetOfStacks(int capacity){this.capacity = capacity;}
        public ArrayList<Stack> getStacks(){ return stacks; }
        public Stack getLastStack(){
            if(stacks.size() == 0) return null;
            return stacks.get(stacks.size() - 1);
        }
        public void push(int v){
            Stack last = getLastStack();
            if(last != null && !last.isFull()){  // add to last stack
                last.push(v);
            }
            else{  // must create new stack
                Stack stack = new Stack(capacity);
                stack.push(v);
                stacks.add(stack);
            }
        }
        public int pop(){
            Stack last = getLastStack();
            int v = last.pop();
            if(last.size == 0) stacks.remove(stacks.size() - 1);
            return v;
        }
        public boolean isEmpty(){
            Stack last = getLastStack();
            return last == null || last.isEmpty();
        }
        public int popAt(int index){
            return leftShift(index, true);
        }
        public int leftShift(int index, boolean removeTop){
            Stack stack = stacks.get(index);
            int removed_item;
            if(removeTop) removed_item = stack.pop();
            else removed_item = stack.removeBottom();
            if(stack.isEmpty()){stacks.remove(index);}
            else if(stacks.size() > index + 1){
                int v = leftShift(index + 1, false);
                stack.push(v);
            }
            return removed_item;
        }
    }

    private static void printStacks(SetOfStacks stacks) {
        for(Stack stack : stacks.stacks){
            Node current = stack.top;
            while(current != null){
                System.out.print(current.data + "->");
                current = current.below;
            }
            System.out.println("null");
        }
    }
}
/*
output:
3->2->1->null
22->11->null
#pop 22
3->2->1->null
11->null
#pop 11
3->2->1->null
#pop 3
2->1->null
#pop 2
1->null
 */
