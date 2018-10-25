package com.company.app;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
 Most Recently Used cache algorithm (MRU): https://en.wikipedia.org/wiki/Cache_algorithms

 Solution:
  - A Linked list to move "fresh" items to the front, and maintain list order
  - A hash table allows lookup of data.
 */
public class CacheMostRecentUsed {

  @Test
  public void Test() {
    Cache cache = new Cache();
    for (int i = 1; i <= 6; i++) {
      Node tmp = new Node(i, "book" + i);
      cache.Add(tmp);
    }
    displayCacheList(cache);
    displayCacheMap(cache);
    Node n3 = cache.get(3);
    System.out.println(n3.bookName);
    displayCacheList(cache);
  }

  static class Node {  // Node = Book
    public Node prev;
    public Node next;
    public int bookID;
    public String bookName;

    public Node(int bookID, String bookName) {
      this.bookID = bookID;
      this.bookName = bookName;
    }
  }
/*
displayCacheList bookName: book6 book5 book4 book3 book2
displayCacheMap bookID: 2 3 4 5 6
book3
displayCacheList bookName: book3 book6 book5 book4 book2
 */

  static class Cache {
    int MAX_SIZE = 5;
    public Node head, tail; // insert to front
    public Map<Integer, Node> map; // lookup data

    public Cache() {
      map = new HashMap<>();
    }

    public void Add(Node node) {
      if (head == null && tail == null) {
        head = tail = node;
        map.put(node.bookID, node);
        return;
      }
      moveToFront(node);
      if (map.size() > MAX_SIZE) {
        map.remove(tail.bookID);
        tail = tail.prev;
        tail.next = null;
      }
    }

    public Node get(int bookID) {
      Node tmp = map.get(bookID);
      if (tmp != null) {
        moveToFront(tmp);
      }
      return tmp;
    }

    private void moveToFront(Node node) {
      if (map.containsKey(node.bookID)) {
        removeFromLinkedList(node);
        map.remove(node.bookID);
      }
      node.next = head;
      head.prev = node;
      head = node;
      if (!map.containsKey(node.bookID)) map.put(node.bookID, node);
    }
    /*
    node.next -> head
    node <- head.prev
    head = node
     */

    private void removeFromLinkedList(Node node) {
      if (node == null) return;
      if (node == head) {
        head = node.next;
        node.next = null;
        head.prev = null;
      } else if (node == tail) {
        tail = node.prev;
        node.prev = null;
        tail.next = null;
      } else {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
      }
    }
  }

  static void displayCacheList(Cache cache) {
    System.out.print("displayCacheList bookName: ");
    Node node = cache.head;
    while (node != null) {
      System.out.print(node.bookName + " ");
      node = node.next;
    }
    System.out.println();
  }

  static void displayCacheMap(Cache cache) {
    System.out.print("displayCacheMap bookID: ");
    for (int key : cache.map.keySet()) {
      System.out.print(key + " ");
    }
    System.out.println();
  }
}
