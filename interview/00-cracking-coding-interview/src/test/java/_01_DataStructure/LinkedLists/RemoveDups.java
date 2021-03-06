package _01_DataStructure.LinkedLists;

import org.junit.Test;

import java.util.HashSet;
import java.util.Hashtable;

/*
Q: remove duplicates from an unordered linked list
 */
public class RemoveDups {
    static class Node {
        public int Data;
        public Node Next;
    }

    @Test
    public void Test() {
        deleteDupsTest();
        deleteDupsWithoutBufferTest();
    }

    private static void deleteDupsTest() {
        System.out.println("#deleteDupsTest");
        Node head = createlinkedList();
        printLinkedList(head);
        deleteDups(head);
        printLinkedList(head);
    }

    /*
    the solution takes O(N) space, and O(N) time, where N is the number of elements.
     */
    static void deleteDupsByHashtable(Node current) {
        System.out.println("#deleteDups");
        Hashtable hashtable = new Hashtable();
        Node previous = null;
        while (current != null) {
            if (hashtable.containsKey(current.Data)) {
                previous.Next = current.Next;
            } else {
                hashtable.put(current.Data, true);
                previous = current;
            }
            current = current.Next;
        }
    }

    static void deleteDups(Node head) {
        HashSet<Integer> set = new HashSet<Integer>();

        Node current = head;
        set.add(current.Data);

        while (current.Next != null) {
            if (set.add(current.Next.Data)) { // able to add
                current = current.Next;
            } else {// fail to add
                current.Next = current.Next.Next;
            }
        }
    }

    static void deleteDupsWithoutBufferTest() {
        System.out.println("\n#deleteDupsWithoutBufferTest");
        Node head = createlinkedList();
        printLinkedList(head);
        deleteDupsWithoutBuffer(head);
        printLinkedList(head);
    }

    /*
    the solution takes O(1) space, but O(NxN) time
     */
    static void deleteDupsWithoutBuffer(Node head) {
        if (head == null) return;
        Node current = head;
        while (current != null) {
            // remove all future nodes that have the same value
            Node runner = current;
            while (runner.Next != null) {  // the last Node has different value because of the next line if statement
                if (runner.Next.Data == current.Data) {
                    runner.Next = runner.Next.Next;
                } else {
                    runner = runner.Next;
                }
            }
            current = current.Next;
        }
    }

    static void printLinkedList(Node head) {
        System.out.println("#printLinkedList");
        Node current = head;
        while (current != null) {
            System.out.print(current.Data + "->");
            current = current.Next;
        }
        System.out.println("null");
    }

    static Node createlinkedList() {
        System.out.println("#createlinkedList");
        int[] numbers = {7, 2, 3, 2, 3, 4, 5, 5};
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
#deleteDupsTest
#createlinkedList
#printLinkedList
7->2->3->2->3->4->5->5->null
#printLinkedList
7->2->3->4->5->null

#deleteDupsWithoutBufferTest
#createlinkedList
#printLinkedList
7->2->3->2->3->4->5->5->null
#printLinkedList
7->2->3->4->5->null
 */
