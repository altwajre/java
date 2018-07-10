package com.company.app._01_DataStructure.LinkedLists;

import org.junit.Test;

/*
Q: adds the two linked list numbers and returns the sum as a linked list.

 */
public class AddLists {
    static class Node{
        public int Data;
        public Node Next;
    }

    @Test
    public void Test() {
        addListsReverseOrderTest();  // recursive call

        addListsTest();
    }

    private static void addListsTest() {
        System.out.println("#addListsTest");

        int[] number1 = {7,1,6};  // {7,1,6} = 617
        int[] number2 = {5,9,7};  // {5,9,2} = 295
        Node l1 = createLinkedList(number1);
        Node l2 = createLinkedList(number2);
        Node result = addLists(l1, l2);

        System.out.print("   ");
        printLinkedList(l1);
        System.out.print(" + ");
        printLinkedList(l2);
        System.out.print(" ");
        printLinkedList(result);
    }

    static class PartialSum{
        public Node sum = null;
        public int carry = 0;
    }
    static Node addLists(Node l1, Node l2){
        int len1 = length(l1);
        int len2 = length(l2);
        // Pad the shorter list with zeros - see note (1)
        if(len1 < len2){
            l1 = padList(l1, len2 - len1);
        }
        else{
            l2 = padList(l2, len1 - len2);
        }
        // Add lists
        PartialSum sum = addListHelper(l1, l2);
        /*
         If there was a carry value left over, insert this at the front of the list.
         Otherwise, just return the linked list.
        */
        if(sum.carry == 0){
            return sum.sum;
        }
        else{
            Node result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }
    /*
       7 1 6  <- l1
     + 5 9 7  <- l2
     1 3 1 3  <- result
    recursive call to the end of lists l1 and l2.
    use insertBefore() to build the result list from the end to the front
     */
    static PartialSum addListHelper(Node l1, Node l2){
        if(l1 == null && l2 == null){
            PartialSum sum = new PartialSum();
            return sum;
        }
        // Add smaller digits recursively
        PartialSum sum = addListHelper(l1.Next, l2.Next);
        // Add carry to current data
        int val = sum.carry + l1.Data + l2.Data;
        // Insert sum of current digits
        Node full_result = insertBefore(sum.sum, val % 10);
        // Return sum so far, and the carry value
        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;
    }
    static int length(Node node){
        int count = 0;
        while(node != null){
            ++count;
            node = node.Next;
        }
        return count;
    }
    static Node padList(Node l, int padding){
        if(padding <= 0){
            return l;
        }
        Node head = new Node();
        Node current = head;
        for(int i = 1; i < padding; i++){
            Node temp = new Node();
            current.Next = temp;
            current = current.Next;
        }
        current.Next = l;
        return head;
    }
    static Node insertBefore(Node list, int data){
        Node head = new Node();
        head.Data = data;
        head.Next = list;
        return head;
    }

    private static void addListsReverseOrderTest() {
        System.out.println("#addListsReverseOrderTest");

        int[] number1 = {7,1,6};  // {7,1,6} = 617
        int[] number2 = {5,9,2};  // {5,9,2} = 295
        Node l1 = createLinkedList(number1);
        Node l2 = createLinkedList(number2);
        Node result = addListsReverseOrder(l1, l2, 0);

        System.out.print("   ");
        printLinkedList(l1);
        System.out.print(" + ");
        printLinkedList(l2);
        System.out.print("   ");
        printLinkedList(result);
    }

    /*
    Q: The digits are stored in reverse order, such that the 1's digit is at the head of the list.

    A: recursively by adding node by node, carrying over any "excess" data to the next node.

     */
    static Node addListsReverseOrder(Node l1, Node l2, int carry){
        // we're done if both lists are null AND the carry value is 0
        if(l1 == null && l2 == null && carry == 0){
            return null;
        }
        Node result = new Node();
        // Add value, and the data from l1 and l2
        int value = carry;
        if(l1 != null){
            value += l1.Data;
        }
        if(l2 != null){
            value += l2.Data;
        }
        result.Data = value % 10; // Second digit of number
        // Recurse
        if(l1 != null || l2 != null){
            Node more = addListsReverseOrder(l1 == null ? null : l1.Next,
                    l2 == null ? null : l2.Next,
                    value > 10 ? 1 : 0);
            result.Next = more;
        }
        return result;
    }
    static void printLinkedList(Node node){
        while(node != null){
            System.out.print(node.Data + " ");
            node = node.Next;
        }
        System.out.println("");
    }
    private static Node createLinkedList(int[] number) {
        Node head = new Node();
        Node current = head;
        for(int i = 0; i < number.length; i++){
            current.Data = number[i];
            if(i != number.length - 1){
                current.Next = new Node();
                current = current.Next;
            }
        }
        return head;
    }
}
/*
#addListsReverseOrderTest
   7 1 6
 + 5 9 2
   2 1 9
#addListsTest
   7 1 6
 + 5 9 7
 1 3 1 3
 */
