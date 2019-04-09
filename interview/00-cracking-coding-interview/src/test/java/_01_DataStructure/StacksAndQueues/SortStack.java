package _01_DataStructure.StacksAndQueues;

import org.junit.Test;

import java.util.Stack;

/*
Q: sort a stack in ascending order (with biggest items on top).

A: sort s1 by inserting each element from s1 in order into s2 that is sorted.
Imagine we have the following left most stack, where s2 is "sorted" and s1 is not.

 */
public class SortStack {

    @Test
    public void Test() {
        int[] numbers = {3, 2, 5, 1, 4};
        printStack(ascendingSort(createStack(numbers)));
        printStack(descendingSort(createStack(numbers)));
    }

    private Stack<Integer> createStack(int[] numbers) {
        System.out.println("create stack");
        Stack<Integer> stack = new Stack<Integer>();
        for (int n : numbers) {
            stack.push(n);
        }
        printStack(stack);
        return stack;
    }

    static Stack<Integer> ascendingSort(Stack<Integer> s1) {
        System.out.println("ascending sort");
        Stack<Integer> s2 = new Stack<Integer>();
        while (!s1.isEmpty()) {
            int tmp = s1.pop();  // Step 1
            while (!s2.isEmpty() && s2.peek() < tmp) {  // Step 2
                s1.push(s2.pop());
            }
            s2.push(tmp);  // Step 3
        }
        return s2;
    }

    static Stack<Integer> descendingSort(Stack<Integer> s1) {
        System.out.println("descending sort");
        Stack<Integer> s2 = new Stack<Integer>();
        while (!s1.isEmpty()) {
            int tmp = s1.pop();  // Step 1
            while (!s2.isEmpty() && s2.peek() > tmp) {  // Step 2
                s1.push(s2.pop());
            }
            s2.push(tmp);  // Step 3
        }
        return s2;
    }

    private static void printStack(Stack<Integer> stack) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
        System.out.println("--");
    }
}
/*
output:
create stack
4
1
5
2
3
--
ascending sort
1
2
3
4
5
--
create stack
4
1
5
2
3
--
descending sort
5
4
3
2
1
--
 */
