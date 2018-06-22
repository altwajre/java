package com.company.app.DataStructure.LinkedLists;

import org.junit.Test;

import java.util.Stack;

/*
Q: check if a linked list is a palindrome.
1 -> 2 -> 3 -> 4 -> 3 -> 2 -> 1
 */
public class IsPalindrome {
    static class Node{
        public int Data;
        public Node Next;
    }

    @Test
    public void Test() {
        isPalindromeTest();

        isPalindromRecurseTest();
    }

    private static void isPalindromRecurseTest() {
        System.out.println("#isPalindromRecurseTest");
        int[] numbers = {1,2,3,4,3,2,1};
        Node head = createList(numbers);
        System.out.println(listSize(head));
        boolean result = isPalindromeR(head);
        System.out.println(result);
    }

    /*
    1 -> 2 -> 3 -> 4 -> 3 -> 2 -> 1
    push half of linked list node into call stack by using length - 2
      |3|
      |2|
      |1|
    returns return new Result(head.Next, true) that head is 4, head.Next is 3 after 4 because 7 - 2 x 3 = 1
    compare Result.node 3 and call stack 3, continue since they are equal
    move Result.node to Next node 2, and compare Result.node 2 and call stack 2, continue since they are equal
    move Result.node to Next node 1, and compare Result.node 1 and call stack 1, continue since they are equal
    done since all call stacks are popped
     */
    static Result isPalindromRecurse(Node head, int length){
        if(head == null || length == 0){
            return new Result(null, true);
        }
        else if(length == 1){
            return new Result(head.Next, true);
        }
        else if(length == 2){
            return new Result(head.Next.Next, head.Data == head.Next.Data);
        }
        Result res = isPalindromRecurse(head.Next, length - 2);
        if(!res.result || res.node == null){
            return res;
        }
        else{
            res.result = head.Data == res.node.Data;
            res.node = res.node.Next;
            return res;
        }
    }
    static class Result{
        public Result(Node node, boolean result){
            this.node = node;
            this.result = result;
        }
        public Node node;
        public boolean result;
    }
    static int listSize(Node node){
        int count = 0;
        while(node != null){
            node = node.Next;
            count++;
        }
        return count;
    }
    static boolean isPalindromeR(Node head){
        Result p = isPalindromRecurse(head, listSize(head));
        return p.result;
    }

    private static void isPalindromeTest() {
        System.out.println("#isPalindromeTest");
        int[] numbers = {1,2,3,4,3,2,1};
        Node head = createList(numbers);
        printList(head);
        boolean result = isPalindrome(head);
        System.out.println("\nIs palindrome: " + result);
    }

    /*
    If we don't know the size of the linked list,
    We can iterate through the linked list, using the fast runner / slow runner technique.
    At each step in the loop, we push the data from the slow runner onto a stack.
    When the fast runner hits the end of the list, the slow runner will reached the middle of linked list.
    By this point, the stack will have all the elements from the front of the linked list, but in reverse order.
    Now, we simply iterate through the rest of the linked list.
    At each iteration, we compare the node data to the top of the stack.
     */
    static boolean isPalindrome(Node head){
        Node fast = head;
        Node slow = head;
        Stack<Integer> stack = new Stack<Integer>();
        /*
        Push elements from first half of linked list onto stack. When fast runner (which is moving at 2x speed)
        reaches the end of the linked list, then we know we're at the middle
         */
        while(fast != null && fast.Next != null){
            stack.push(slow.Data);
            slow = slow.Next;
            fast = fast.Next.Next;
        }
        // Has odd number of elements, so skip the middle element
        if(fast != null){
            slow = slow.Next;
        }
        while(slow != null){
            int top = stack.pop().intValue();
            // If values are different, then it's not a palindrome
            if(top != slow.Data){
                return false;
            }
            slow = slow.Next;
        }
        return true;
    }
    private static Node createList(int[] numbers) {
        Node head = new Node();
        Node current = head;
        for(int i = 0; i < numbers.length; i++){
            current.Data = numbers[i];
            if(i != numbers.length - 1){
                current.Next = new Node();
                current = current.Next;
            }
        }
        return head;
    }
    static void printList(Node node){
        while(node != null){
            System.out.print(node.Data + " ");
            node = node.Next;
        }
    }
}
/*
#isPalindromeTest
1 2 3 4 3 2 1
Is palindrome: true
#isPalindromRecurseTest
7
true
 */
