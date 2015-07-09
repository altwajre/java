package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
Q: given a circular linked list, implement an algorithm which returns the node at the beginning of the loop.

 */
public class App 
{
    static class Node{
        public int Data;
        public Node Next;
    }
    public static void main( String[] args )
    {
        Node head = createCircularList(4);
        printLinkedList(head);
        Node loopStartNode = findLoopBeginning(head);
        System.out.println("Found loop starting node: " + loopStartNode.Data);
    }

    static Node findLoopBeginning(Node head){
        Node slow = head;
        Node fast = head;
        // Find meeting point. This will be LOOP_SIZE - k steps into the linked list
        while (fast != null && fast.Next != null){
            slow = slow.Next;
            fast = fast.Next.Next;
            if(slow == fast){  // Collision
                break;
            }
        }
        // Error check - no meeting point, and therefore no loop
        if(fast == null || fast.Next == null){
            return null;
        }
        /*
        Move slow to Head. Keep fast at Meeting Point. Each are k steps from the loop Start.
        If they move at the same pace, they must meet at Loop Start.
         */
        slow = head;
        while (slow != fast){
            slow = slow.Next;
            fast = fast.Next;
        }
        // Both now point to the start of the loop
        return fast;
    }

    private static Node createCircularList(int loopStartPointIndex) {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10,11};
        Node head = new Node();
        Node current = head;
        for(int i = 0; i < numbers.length; i++){
            current.Data = numbers[i];
            if(i != numbers.length - 1) {
                current.Next = new Node();
                current = current.Next;
            }
        }
        Node loopStartPointNode = findNthNode(head, loopStartPointIndex);
        System.out.println("Loop starting point: " + loopStartPointNode.Data);
        current.Next = loopStartPointNode;
        return head;
    }

    static Node findNthNode(Node node, int nth){
        while(node != null && nth != 1){
            node = node.Next;
            nth--;
        }
        return node;
    }

    static void printLinkedList(Node node){
        System.out.print("Linked list: ");
        List<Node> items = new ArrayList<Node>();
        boolean foundStartPoint = false;
        while(node != null){
            for(Node n : items){
                if(n == node) foundStartPoint = true;
            }
            if(foundStartPoint) break;
            items.add(node);
            System.out.print(node.Data + " ");
            node = node.Next;
        }
        System.out.println("\nCircular list loop starting point is " + node.Data);
    }
}
