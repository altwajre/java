package _01_DataStructure.LinkedLists;

import org.junit.Test;

/*
Q: delete a node in the middle of a singly linked list, given only access to that node

 */
public class DeleteNode {
    static class Node {
        public int Data;
        public Node Next;
    }

    @Test
    public void Test() {
        int[] numbers = {1, 2, 3, 4, 5};
        Node head = createLinkedList(numbers);
        printLinkedList(head);

        Node middleNode = head.Next.Next;  // get the middle node that is 3
        System.out.println(middleNode.Data);
        deleteNode(middleNode);

        printLinkedList(head);
    }

    /*
    Solution:
    only have access to the middle node. the solution is to copy the data from the next node over to the current node,
    and then to delete the next node.
     */
    static boolean deleteNode(Node n) {
        if (n == null || n.Next == null) {
            return false;
        }
        Node next = n.Next;
        n.Data = next.Data;
        n.Next = next.Next;
        return true;
    }

    static void printLinkedList(Node node) {
        while (node != null) {
            System.out.print(node.Data + " ");
            node = node.Next;
        }
        System.out.println("");
    }

    static Node createLinkedList(int[] numbers) {
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
1 2 3 4 5
3
1 2 4 5
 */
