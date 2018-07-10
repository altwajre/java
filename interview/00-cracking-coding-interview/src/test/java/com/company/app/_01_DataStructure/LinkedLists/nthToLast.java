package com.company.app._01_DataStructure.LinkedLists;

import org.junit.Test;

/*
Q: find the kth to last element of a singly linked list

 */
public class nthToLast {
    static class Node {
        public int Data;
        public Node Next;
    }

    @Test
    public void Test() {
        nthToLastRecurse();
        nthToLastTest();
    }

    private static void nthToLastTest() {
        Node head = createLinkedList();
        printLinkedList(head);
        Node target = nthToLast(head, 3);
        System.out.println(target.Data);
    }

    /*
    Iterative:
     */
    static Node nthToLast(Node head, int k) {
        if (k <= 0) return null;
        Node p1 = head;
        Node p2 = head;
        // Move p2 forward k nodes into the list.
        for (int i = 0; i < k - 1; i++) {
            if (p2 == null) return null; // Error check
            p2 = p2.Next;
        }
        System.out.println(p2.Data);
        if (p2 == null) return null;
        /*
        Now, move p1 and p2 at the same speed. When p2 hits the end, p1 will be at the right element.
         */
        while (p2.Next != null) {
            p1 = p1.Next;
            p2 = p2.Next;
        }
        return p1;
    }

    private static void nthToLastRecurse() {
        Node head = createLinkedList();
        printLinkedList(head);
        int result = nthToLastRecurse(head, 2);
        System.out.println(result);
    }

    /*
    Recursive:
      recurse through the linked list. when it hits the end, the method passes back a counter set to 0.
      Each parent call adds 1 to this counter. When the counter equals k.
     */
    static int nthToLastRecurse(Node head, int k) {
        if (head == null) {
            return 0;
        }
        int i = nthToLastRecurse(head.Next, k) + 1;
        if (i == k) {
            System.out.println(head.Data);
        }
        System.out.println(" " + i);
        return i;
    }

    static void printLinkedList(Node node) {
        while (node != null) {
            System.out.print(node.Data + " ");
            node = node.Next;
        }
        System.out.println("");
    }

    static Node createLinkedList() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Node head = new Node();
        Node current = head;
        for (int i = 0; i < numbers.length; i++) {
            current.Data = numbers[i];
            if (i != numbers.length - 1) {
                current.Next = new Node();
                current = current.Next;
            }
        }
        return head;
    }
}
/*
output:
1 2 3 4 5 6 7 8 9
 1
8
 2
 3
 4
 5
 6
 7
 8
 9
9
1 2 3 4 5 6 7 8 9
3
7
 */
