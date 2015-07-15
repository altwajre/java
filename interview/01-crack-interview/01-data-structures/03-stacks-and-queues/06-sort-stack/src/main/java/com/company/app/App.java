package com.company.app;

import java.util.Stack;

/*
Q: sort a stack in ascending order (with biggest items on top).

A: sort s1 by inserting each element from s1 in order into s2 that is sorted.
Imagine we have the following left most stack, where s2 is "sorted" and s1 is not.
pop 5 from s1 and holding it in a temporary variable.
Then, we move 12 and 8 over to s1 and then push 5 onto s2.
         step 1     step 2     step 3
|s1|s2|  |s1|s2|    |s1|s2|    |s1|s2|
|  |12|  |  |12|    | 8|  |    | 8|  |
| 5| 8|  |  | 8| -> |12|  | -> |12| 5|
|10| 3|  |10| 3|    |10|  |    |10| 3|
| 7| 1|  | 7| 1|    | 7|  |    | 7| 1|

 */
public class App 
{
    static Stack<Integer> sort(Stack<Integer> s){
        Stack<Integer> r = new Stack<Integer>();
        while(!s.isEmpty()){
            int tmp = s.pop();  // Step 1
            while(!r.isEmpty() && r.peek() > tmp){  // Step 2
                s.push(r.pop());
            }
            r.push(tmp);  // Step 3
        }
        return r;
    }
    public static void main( String[] args )
    {
        int[] numbers = {3,2,5,1,4};
        Stack<Integer> stack = new Stack<Integer>();
        for(int n : numbers){
            stack.push(n);
        }
        printStack(stack);
        Stack<Integer> sortedStack = sort(stack);
        printStack(sortedStack);
    }
    private static void printStack(Stack<Integer> stack) {
        for(int i = stack.size() - 1; i >= 0; i--){
            System.out.println(stack.get(i));
        }
        System.out.println("--");
    }
}
