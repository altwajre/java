package common;

import org.junit.Test;

/*
Reverse Linkedlist
while current != null
1, set next = current.Next - to keep the next pointer, so we can advance the current later
2, set current.Next to previous - to reserve the Next pointer
3, set previous to current - to advance the previous to current
4, set current to next - to advance the current to next
 */
public class LinkedListReverse {
  class Node {
    public int Data;
    public Node Next;
  }

  @Test
  public void Test() {
    Node head = createLinkedList(new int[]{1, 2, 3, 4, 5});
    printLinkedList(head);
    Node reversedHead = reverse(head);
    System.out.println("\nAfter reversed");
    printLinkedList(reversedHead);
  }

  Node reverse(Node current) {
    Node previous = null;
    Node next;
    while (current != null) {
      next = current.Next;     // keep the next pointer, so we can advance the current later
      current.Next = previous; // reserve the Next pointer
      previous = current;      // advance the previous to current
      current = next;          // advance the current to next
    }
    return previous;
  }

  void printLinkedList(Node head) {
    Node current = head;
    while (current != null) {
      System.out.print(current.Data + "->");
      current = current.Next;
    }
  }

  Node createLinkedList(int[] numbers) {
    Node head = new Node();
    Node current = head;
    for (int i = 0; i < numbers.length; i++) {
      current.Data = numbers[i];
      if (i < numbers.length - 1) {
        current.Next = new Node();
        current = current.Next;
      }
    }
    return head;
  }
}
/*
output:
1->2->3->4->5->
After reversed
5->4->3->2->1->
 */
