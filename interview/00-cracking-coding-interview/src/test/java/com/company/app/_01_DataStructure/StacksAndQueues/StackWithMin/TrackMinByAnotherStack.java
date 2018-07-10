package com.company.app._01_DataStructure.StacksAndQueues.StackWithMin;

import org.junit.Test;

import java.util.Stack;

/*
Q: design a stack has a function min which returns the minimum element.

A: better approach - not waste a lot of space by keeping track of the min for every single element.
use additional stack which keeps stack of the mins.
s1 push|5| (5) node - s2 push|5|
------------------------------------
s1 push|6| (6) node - s2     |5|
       |5|
------------------------------------
s1 push|3| (3) node - s2 push|3|
       |6|                   |5|
       |5|
------------------------------------
s1 push|7| (7) node - s2     |3|
       |3|                   |5|
       |6|
       |5|
------------------------------------
s1  pop|3| (7) node - s2     |3|
       |6|                   |5|
       |5|
------------------------------------
s1  pop|6| (3) node - s2  pop|5| (3)
       |5|
------------------------------------
 */
public class TrackMinByAnotherStack {

    @Test
    public void Test() {
        int[] numbers = {5, 6, 3, 7};
        StackWithMin stack = new StackWithMin();
        for (int n : numbers) {
            stack.push(n);
            System.out.print(stack.min() + " ");
        }
        System.out.println("");
        System.out.println("pop");
        stack.pop();
        System.out.println(stack.min() + " ");
        System.out.println("pop");
        stack.pop();
        System.out.println(stack.min() + " ");
        System.out.println("");
    }

    static class StackWithMin extends Stack<Integer> {
        Stack<Integer> s2;

        public StackWithMin() {
            s2 = new Stack<Integer>();
        }

        public void push(int value) {
            if (value <= min()) {
                s2.push(value);
            }
            super.push(value);
        }

        public Integer pop() {
            int value = super.pop();
            if (value == min()) {
                s2.pop();
            }
            return value;
        }

        public int min() {
            if (s2.isEmpty()) {
                return Integer.MAX_VALUE;  // Error value
            } else {
                return s2.peek();
            }
        }
    }
}
/*
output:
5 5 3 3
pop
3
pop
5
 */
