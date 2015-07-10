package com.company.app;

import java.util.Arrays;
import java.util.Random;

/*
Q: partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x.

 */
public class App 
{
    static class Node{
        public int Data;
        public Node Next;
    }
    public static void main( String[] args )
    {
        partitionTest();

        partitionWithLessVarTest();
    }

    private static void partitionWithLessVarTest() {
        System.out.println("#partitionWithLessVarTest");
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node head = createLinkedList(numbers);
        printLinkedList(head);
        Node partitionList = partitionWithLessVar(head, 5);
        printLinkedList(partitionList);
    }

    /*
    partition using Node beforeStart, afterStart
     */
    static Node partitionWithLessVar(Node node, int x){
        Node beforeStart = null;
        Node afterStart = null;
        // Partition list
        while(node != null){
            Node next = node.Next;
            if(node.Data < x){
                // Insert node into start of before list
                node.Next = beforeStart;
                beforeStart = node;
            }
            else{
                // Insert node into front of after list
                node.Next = afterStart;
                afterStart = node;
            }
            node = next;
        }
        // Merge before list and after list
        if (beforeStart == null) {
            return afterStart;
        }
        // Find end of before list, and merge the lists
        Node head = beforeStart;
        while(beforeStart.Next != null){
            beforeStart = beforeStart.Next;
        }
        beforeStart.Next = afterStart;
        return head;
    }

    private static void partitionTest() {
        System.out.println("#partitionTest");
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node head = createLinkedList(numbers);
        printLinkedList(head);
        Node partitionList = partition(head, 5);
        printLinkedList(partitionList);
    }

    /*
    partition using Node beforeStart, beforeEnd, afterStart, afterEnd
     */
    static Node partition(Node node, int x){
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;
        // Partition list
        while(node != null){
            Node next = node.Next;
            node.Next = null;
            if(node.Data < x){
                // Insert node into end of before list
                if(beforeStart == null){
                    beforeStart = node;
                    beforeEnd = beforeStart;
                }
                else{
                    beforeEnd.Next = node;
                    beforeEnd = node;
                }
            }
            else{
                // Insert node into end of after list
                if(afterStart == null){
                    afterStart = node;
                    afterEnd = afterStart;
                }
                else{
                    afterEnd.Next = node;
                    afterEnd = node;
                }
            }
            node = next;
        }
        if(beforeStart == null){
            return afterStart;
        }
        // Merge before list and after list
        beforeEnd.Next = afterStart;
        return beforeStart;
    }

    static void printLinkedList(Node node){
        while (node != null){
            System.out.print(node.Data + " ");
            node = node.Next;
        }
        System.out.println("");
    }
    static Node createLinkedList(int[] numbers) {
        shuffle(numbers);
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
    static void shuffle(int[] numbers){
        Random random = new Random();
        for(int i = 0; i < numbers.length; i++){
            int randNum = random.nextInt(8);
            int tmp = numbers[i];
            numbers[i] = numbers[randNum];
            numbers[randNum] = tmp;
        }
    }
}
