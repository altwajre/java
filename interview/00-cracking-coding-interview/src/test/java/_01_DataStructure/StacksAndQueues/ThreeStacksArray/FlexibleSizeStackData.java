package _01_DataStructure.StacksAndQueues.ThreeStacksArray;

import org.junit.Test;

public class FlexibleSizeStackData {

    @Test
    public void Test() throws Exception {
        expandStack1();
        reset();

        expandStack3();

        System.out.println( "done" );
    }

    /*
    StackData is a simple class that holds a set of data about each stack.
    It does not hold the actual items in the stack.
     */
    static int stackSize = 4;
    static int numberOfStacks = 3;
    static int totalSize = stackSize * numberOfStacks;
    static int[] buffer = new int[totalSize];

    static class StackData{
        public int start;
        public int pointer; // stackPointer
        public int size = 0;
        public int capacity;
        public StackData(int start, int capacity){
            this.start = start;
            this.pointer = start - 1;
            this.capacity = capacity;
        }
        public boolean isWithinStack(int index, int totalSize){
            // Not: if stack wraps, the head (right side) wraps around to the left.
            if(start <= index && index < start + capacity){
                // non-wrapping, or "head" (right side) of wrapping case
                return true;
            }
            else if(start + capacity > totalSize && index < (start + capacity) % totalSize){
                // tail (left side) of wrapping case
                return true;
            }
            return false;
        }
    }
    static StackData[] stacks = {
            new StackData(0, stackSize),
            new StackData(stackSize, stackSize),
            new StackData(2 * stackSize, stackSize)};
    static int numberOfElements(){
        return stacks[0].size + stacks[1].size + stacks[2].size;
    }
    static int nextElement(int index){
        if(index + 1 == totalSize) return 0;
        else return index + 1;
    }
    static int previousElement(int index){
        if(index == 0) return totalSize - 1;
        else return index - 1;
    }
    static void shift(int stackNum){
        StackData stack = stacks[stackNum];
        if(stack.size >= stack.capacity){
            int nextStack = (stackNum + 1) % numberOfStacks;
            shift(nextStack);  // make some room
            stack.capacity++;
        }
        // Shift elements in reverse order
        int endIndex = (stack.start + stack.capacity - 1) % totalSize;
        for(int i = endIndex;
            stack.isWithinStack(i, totalSize);
            i = previousElement(i)){
            buffer[i] = buffer[previousElement(i)];
        }
        buffer[stack.start] = 0;
        stack.start = nextElement(stack.start);  // move stack start
        stack.pointer = nextElement(stack.pointer);  // move pointer
        stack.capacity--;  // return capacity to original
    }
    // Expand stack by shifting over other stacks
    static void expand(int stackNum){
        shift((stackNum + 1) % numberOfStacks);
        stacks[stackNum].capacity++;
    }
    static void push(int stackNum, int value) throws Exception {
        StackData stack = stacks[stackNum];
        // Check that we have space
        if(stack.size >= stack.capacity){
            if(numberOfElements() >= totalSize){  // Totally full
                throw new Exception("Out of space.");
            }
            else{  // just need to shift things around
                expand(stackNum);
            }
        }
        // Find index of top element in array + 1, and increment stack pointer
        stack.size++;
        stack.pointer = nextElement(stack.pointer);
        buffer[stack.pointer] = value;
    }
    static int pop(int stackNum) throws Exception {
        StackData stack = stacks[stackNum];
        if(stack.size == 0){
            throw new Exception("Trying to pop an empty stack.");
        }
        int value = buffer[stack.pointer];
        buffer[stack.pointer] = 0;
        stack.pointer = previousElement(stack.pointer);
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

    private static void expandStack3() throws Exception {
        int[] stackN1 = {111,222};
        for(int i = 0; i < stackN1.length; i++){
            push(0, stackN1[i]);
        }
        int[] stackN2 = {11,22};
        for(int i = 0; i < stackN2.length; i++){
            push(1, stackN2[i]);
        }
        int[] stackN3 = {1,2,3,4,5};
        for(int i = 0; i < stackN3.length; i++){
            push(2, stackN3[i]);
        }
        System.out.println(stacks[0].size);
        System.out.println(stacks[1].size);
        System.out.println(stacks[2].size);
        printStack();
    }

    private static void expandStack1() throws Exception {
        int[] stackN3 = {111,222};
        for(int i = 0; i < stackN3.length; i++){
            push(2, stackN3[i]);
        }
        int[] stackN2 = {11,22};
        for(int i = 0; i < stackN2.length; i++){
            push(1, stackN2[i]);
        }
        int[] stackN1 = {1,2,3,4,5};
        for(int i = 0; i < stackN1.length; i++){
            push(0, stackN1[i]);
        }
        System.out.println(stacks[0].size);
        System.out.println(stacks[1].size);
        System.out.println(stacks[2].size);
        printStack();
    }

    private static void printStack() {
        for(int i = 0; i < buffer.length; i++){
            System.out.print(buffer[i] + " ");
        }
        System.out.println("");
    }
}
/*
output:
5
2
2
1 2 3 4 5 11 22 0 111 222 0 0
2
2
5
5 111 222 0 11 22 0 0 1 2 3 4
done
 */
