package com.company.app._01_DataStructure.StacksAndQueues;

import org.junit.Test;

import java.util.Stack;

/*
Q: implements a queue using two stacks

A: stackNewest has the newest elements on the top and stackOldest has the oldest elements on top.
When we dequeue an element, we want to remove the oldest element first, and so we dequeue from stackOldest.
If stackOldest is empty, then we want to transfer all elements from stackNewest into this stack in reverse order.
To insert an element, we push onto stackNewest, since it has the newest elements on top.
 */
public class TwoStacksQueue {

    @Test
    public void Test() {
        int[] numbers = {1, 2, 3};
        MyQueue<Integer> queue = new MyQueue<Integer>();
        Stack<Integer> stackNewest = queue.getStackNewest();
        Stack<Integer> stackOldest = queue.getStackOldest();
        for (int n : numbers) {
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

    static class MyQueue<T> {
        Stack<T> stackNewest, stackOldest;

        public Stack<T> getStackNewest() {
            return stackNewest;
        }

        public Stack<T> getStackOldest() {
            return stackOldest;
        }

        public MyQueue() {
            stackNewest = new Stack<T>();
            stackOldest = new Stack<T>();
        }

        public int size() {
            return stackNewest.size() + stackNewest.size();
        }

        public void add(T value) {
            // Push onto stackNewest, which always has the newest elements on top
            stackNewest.push(value);
        }

        /*
        Move elements from stackNewest into stackOldest.
        This is usually done so that we can do operations on stackOldest.
         */
        private void shiftStacks() {
            if (stackOldest.isEmpty()) {
                while (!stackNewest.isEmpty()) {
                    stackOldest.push(stackNewest.pop());
                }
            }
        }

        public T peek() {
            shiftStacks();  // Ensure stackOldest has the current elements
            return stackOldest.peek();  // retrieve the oldest item.
        }

        public T remove() {
            shiftStacks();  // Ensure stackOldest has the current elements
            return stackOldest.pop();  // pop the oldest item.
        }
    }

    private static void printStacks(Stack<Integer> stackNewest, Stack<Integer> stackOldest) {
        printStack(stackNewest, "stackNewest");
        printStack(stackOldest, "stackOldest");
    }

    private static void printStack(Stack<Integer> stack, String name) {
        System.out.println("--Print " + name + " disks--");
        if (stack.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }
}
/*
output:
--Print stackNewest disks--
3
2
1
--Print stackOldest disks--
Empty
#remove 1
--Print stackNewest disks--
Empty
--Print stackOldest disks--
2
3
#add 11
--Print stackNewest disks--
11
--Print stackOldest disks--
2
3
#remove 2
--Print stackNewest disks--
11
--Print stackOldest disks--
3
#remove 3
--Print stackNewest disks--
11
--Print stackOldest disks--
Empty
#remove 11
--Print stackNewest disks--
Empty
--Print stackOldest disks--
Empty
 */
