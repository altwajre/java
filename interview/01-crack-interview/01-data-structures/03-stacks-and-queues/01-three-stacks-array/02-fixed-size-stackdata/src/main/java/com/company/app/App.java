package com.company.app;

/*
Q: use a single array to implement three stacks

A: Fixed Division
divide the array in three equal parts and allow the individual stack to grow in that limited space.
 - For stack 1, [0, n/3)
 - For stack 2, [n/3, 2n/3)
 - For stack 3, [2n/3, n)
 note: "[" means inclusive, "(" means exclusive
 */
public class App 
{
    static int stackSize = 4;
    static int numberOfStacks = 3;
    static int totalSize = stackSize * numberOfStacks;
    static int[] buffer = new int[totalSize];

    static class StackData{
        public int start;
        public int pointer;  // stackPointer
        public int size = 0;
        public int capacity;
        public StackData(int start, int capacity){
            this.start = start;
            this.pointer = start - 1;
            this.capacity = capacity;
        }
    }
    static StackData[] stacks = {
            new StackData(0, stackSize),
            new StackData(stackSize, stackSize),
            new StackData(2 * stackSize, stackSize)};
    static void push(int stackNum, int value) throws Exception {
        StackData stack = stacks[stackNum];
        if(stack.size >= stack.capacity){
            throw new Exception("Out of space.");
        }
        // increment stack size, and increment pointer to top of stack
        stack.size++;
        stack.pointer++;
        buffer[stack.pointer] = value;
    }
    static int pop(int stackNum) throws Exception {
        StackData stack = stacks[stackNum];
        if(stack.size == 0){
            throw new Exception("Trying to pop an empty stack.");
        }
        int value = buffer[stack.pointer];
        buffer[stack.pointer] = 0;
        stack.pointer--;
        stack.size--;
        return value;
    }
    static int peek(int stackNum){
        StackData stack = stacks[stackNum];
        return buffer[stack.pointer];
    }
    static boolean isEmpty(int stackNum){
        StackData stack = stacks[stackNum];
        return stack.size == 0;
    }
    static void reset(){
        stacks[0] = new StackData(0, stackSize);
        stacks[1] = new StackData(stackSize, stackSize);
        stacks[2] = new StackData(stackSize * 2, stackSize);
        for(int i = 0; i < buffer.length; i++){
            buffer[i] = 0;
        }
    }
    public static void main( String[] args ) throws Exception {
        int[] stackN1 = {1,2,3,4};
        int[] stackN2 = {11,22,33};
        int[] stackN3 = {111,222,333};
        createStacks(stackN1, stackN2, stackN3);
        printStack();
        popStacks();
        printStack();
    }
    private static void popStacks() throws Exception {
        pop(0);
        pop(1);
        pop(2);
    }

    private static void printStack() {
        for(int i = 0; i < buffer.length; i++){
            System.out.print(buffer[i] + " ");
        }
        System.out.println("");
    }

    private static void createStacks(int[] stackN1, int[] stackN2, int[] stackN3) throws Exception {
        for(int i = 0; i < stackN1.length; i++){
            push(0, stackN1[i]);
        }
        for(int i = 0; i < stackN2.length; i++){
            push(1, stackN2[i]);
        }
        for(int i = 0; i < stackN3.length; i++){
            push(2, stackN3[i]);
        }
    }
}
