package com.company.app;

import java.util.HashMap;
import java.util.Map;

/*
 Most Recently Used (MRU): https://en.wikipedia.org/wiki/Cache_algorithms
 */
public class App 
{
    static class Node{  // Node = Book
        public Node prev;
        public Node next;
        public int bookID;
        public String bookName;
        public Node(int bookID, String bookName){
            this.bookID = bookID;
            this.bookName = bookName;
        }
    }
    static class Cache{
        int MAX_SIZE = 5;
        int size = 0;
        public Node head, tail;
        public Map<Integer, Node> map;
        public Cache(){map = new HashMap<Integer, Node>();}
        public void Add(Node node){
            size++;
            if(head == null && tail == null){
                head = tail = node;
                map.put(node.bookID, node);
                return;
            }
            moveToFront(node);
            if(size > 5){
                map.remove(tail.bookID);
                tail = tail.prev;
                tail.next = null;
                size--;
            }
        }
        public Node get(int bookID){
            Node tmp = map.get(bookID);
            if(tmp != null){
                moveToFront(tmp);
            }
            return tmp;
        }
        public void moveToFront(Node node){
            if(map.containsKey(node.bookID)) removeFromLinkedList(node);
            node.next = head;
            head.prev = node;
            node.prev = null;
            head = node;
            if(!map.containsKey(node.bookID)) map.put(node.bookID, node);
        }
        public void removeFromLinkedList(Node node){
            if(node == null) return;
            if(node == head){
                head = node.next;
                node.next = null;
            }
            if(node == tail){
                tail = node.prev;
                node.prev = null;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
            map.remove(node.bookID);
        }
    }
    public static void main( String[] args )
    {
        Cache cache = new Cache();
        for(int i = 1; i <= 6; i++){
            Node tmp = new Node(i, "book"+i);
            cache.Add(tmp);
        }
        displayCacheList(cache);
        displayCacheMap(cache);
        Node n3 = cache.get(3);
        System.out.println(n3.bookName);
        displayCacheList(cache);
    }
    static void displayCacheList(Cache cache){
        System.out.print("displayCacheList bookName: ");
        Node node = cache.head;
        while(node != null){
            System.out.print(node.bookName + " ");
            node = node.next;
        }
        System.out.println("");
    }
    static void displayCacheMap(Cache cache){
        System.out.print("displayCacheMap bookID: ");
        for(int key : cache.map.keySet()){
            System.out.print(key + " ");
        }
        System.out.println("");
    }
}
