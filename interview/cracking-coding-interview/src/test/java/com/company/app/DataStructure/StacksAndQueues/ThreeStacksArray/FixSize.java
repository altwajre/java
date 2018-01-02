package com.company.app.DataStructure.StacksAndQueues.ThreeStacksArray;

import org.junit.Test;

/*
Q: use a single array to implement three stacks

A: Fixed Division
divide the array in three equal parts and allow the individual stack to grow in that limited space.
 - For stack 1, [0, n/3)
 - For stack 2, [n/3, 2n/3)
 - For stack 3, [2n/3, n)
 note: "[" means inclusive, "(" means exclusive
 */
public class FixSize {

    @Test
    public void Test() throws Exception {
        int[] stackN1 = {1,2,3,4};
        int[] stackN2 = {11,22,33};
        int[] stackN3 = {111,222,333};
        createStacks(stackN1, stackN2, stackN3);
        printStack();
        popStacks();
        printStack();
    }

    static int stackSize = 4;
    static int numberOfStacks = 3;
    static int totalSize = stackSize * numberOfStacks;
    static int[] buffer = new int[totalSize];

    static int[] stackPointer = {-1,-1,-1};  // pointers to track top element
    // returns index of top of stack "stackNum", in absolute terms
    static int absTopOfStack(int stackNum){
        return stackNum * stackSize + stackPointer[stackNum];
    }
    static void push(int stackNum, int value) throws Exception {
        // Check if we have space
        if(stackPointer[stackNum] + 1 >= stackSize){  // Last element
            throw new Exception("Out of space.");
        }
        // Increment stack pointer and then update top value
        stackPointer[stackNum]++;
        buffer[absTopOfStack(stackNum)] = value;
    }
    static int pop(int stackNum) throws Exception{
        if(stackPointer[stackNum] == -1){
            throw new Exception("Trying to pop an empty stack.");
        }
        int value = buffer[absTopOfStack(stackNum)];  // Get top
        buffer[absTopOfStack(stackNum)] = 0; // clear index
        stackPointer[stackNum]--;  // Decrement pointer
        return value;
    }
    static int peek(int stackNum){
        int index = absTopOfStack(stackNum);
        return buffer[index];
    }
    static boolean isEmpty(int stackNum){
        return stackPointer[stackNum] == -1;
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
/*
output:
        int[] stackN1 = {1,2,3,4};
        int[] stackN2 = {11,22,33};
        int[] stackN3 = {111,222,333};
        createStacks(stackN1, stackN2, stackN3);
        printStack();
        popStacks();
        printStack();
 */
