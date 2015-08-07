package com.company.app;

import java.util.HashMap;

/*
  Q: Cache the results of the most recent queries.
     Most Recently Used (MRU): https://en.wikipedia.org/wiki/Cache_algorithms

  Solution:
   - A Linked list to move "fresh" items to the front.
   - A hash table allows lookup of data.
 */
public class App 
{
    static class Node {
        public Node prev;
        public Node next;
        public String[] results;
        public String query;
        public Node(String q, String[] res) {
            results = res;
            query = q;
        }
    }
    static class Cache {
        public static int MAX_SIZE = 10;
        public Node head;
        public Node tail;
        public HashMap<String, Node> map;
        public int size = 0;
        public Cache() { map = new HashMap<String, Node>(); }
        public void moveToFront(Node node) {
            if (node == head) { return; }
            removeFromLinkedList(node);
            node.next = head;
            if (head != null) head.prev = node;
            head = node;
            size++;
            if (tail == null) tail = node;
        }
        public void moveToFront(String query) {
            Node node = map.get(query);
            moveToFront(node);
        }
        public void removeFromLinkedList(Node node) {
            if (node == null) return;
            if (node.next != null || node.prev != null) size--;
            Node prev = node.prev;
            if (prev != null) prev.next = node.next;
            Node next = node.next;
            if (next != null) next.prev = prev;
            if (node == head) head = next;
            if (node == tail) tail = prev;
            node.next = null;
            node.prev = null;
        }
        public void insertResults(String query, String[] results) {
            if (map.containsKey(query)) {
                Node node = map.get(query);
                node.results = results;
                moveToFront(node);
                return;
            }
            Node node = new Node(query, results);
            moveToFront(node);
            map.put(query, node);
            if (size > MAX_SIZE) {
                map.remove(tail.query);
                removeFromLinkedList(tail);
            }
        }
        public String[] getResults(String query) {
            if (map.containsKey(query)) {
                Node node = map.get(query);
                moveToFront(node);
                return node.results;
            }
            return null;
        }
    }
    static String[] generateResults(int i){
        String[] results = {"resultA" + i, "resultB" + i, "resultC" + i};
        return results;
    }
    static void printCacheList(Cache cache){
        Node temp = cache.head;
        System.out.print("Display cache list: ");
        while(temp != null){
            System.out.print(temp.query + " ");
            temp = temp.next;
        }
        System.out.println("");
    }
    public static void main( String[] args )
    {
        Cache cache = new Cache();
        for (int i = 0; i < 20; i++) {
            String query = "query" + i;
            cache.insertResults(query, generateResults(i));
            printCacheList(cache);
            if (i == 9 || i == 16 || i == 19) {
                cache.getResults("query" + 2);
                cache.getResults("query" + 6);
                cache.getResults("query" + 9);
                System.out.print("  After getResults - ");
                printCacheList(cache);
            }
        }

        System.out.println("");
        printCacheList(cache);
        for (int i = 0; i < 25; i++) {
            String query = "query" + i;
            String[] results = cache.getResults(query);
            System.out.print(query + ": ");
            if (results == null) System.out.print("null");
            else {
                for (String s : results) System.out.print(s + ", ");
            }
            System.out.println("");
        }
        printCacheList(cache);
    }
}

/*
Output:
Display cache list: query0
Display cache list: query1 query0
Display cache list: query2 query1 query0
Display cache list: query3 query2 query1 query0
Display cache list: query4 query3 query2 query1 query0
Display cache list: query5 query4 query3 query2 query1 query0
Display cache list: query6 query5 query4 query3 query2 query1 query0
Display cache list: query7 query6 query5 query4 query3 query2 query1 query0
Display cache list: query8 query7 query6 query5 query4 query3 query2 query1 query0
Display cache list: query9 query8 query7 query6 query5 query4 query3 query2 query1 query0
  After getResults - Display cache list: query9 query6 query2 query8 query7 query5 query4 query3 query1 query0
Display cache list: query10 query9 query6 query2 query8 query7 query5 query4 query3 query1
Display cache list: query11 query10 query9 query6 query2 query8 query7 query5 query4 query3
Display cache list: query12 query11 query10 query9 query6 query2 query8 query7 query5 query4
Display cache list: query13 query12 query11 query10 query9 query6 query2 query8 query7 query5
Display cache list: query14 query13 query12 query11 query10 query9 query6 query2 query8 query7
Display cache list: query15 query14 query13 query12 query11 query10 query9 query6 query2 query8
Display cache list: query16 query15 query14 query13 query12 query11 query10 query9 query6 query2
  After getResults - Display cache list: query9 query6 query2 query16 query15 query14 query13 query12 query11 query10
Display cache list: query17 query9 query6 query2 query16 query15 query14 query13 query12 query11
Display cache list: query18 query17 query9 query6 query2 query16 query15 query14 query13 query12
Display cache list: query19 query18 query17 query9 query6 query2 query16 query15 query14 query13
  After getResults - Display cache list: query9 query6 query2 query19 query18 query17 query16 query15 query14 query13

Display cache list: query9 query6 query2 query19 query18 query17 query16 query15 query14 query13
query0: null
query1: null
query2: resultA2, resultB2, resultC2,
query3: null
query4: null
query5: null
query6: resultA6, resultB6, resultC6,
query7: null
query8: null
query9: resultA9, resultB9, resultC9,
query10: null
query11: null
query12: null
query13: resultA13, resultB13, resultC13,
query14: resultA14, resultB14, resultC14,
query15: resultA15, resultB15, resultC15,
query16: resultA16, resultB16, resultC16,
query17: resultA17, resultB17, resultC17,
query18: resultA18, resultB18, resultC18,
query19: resultA19, resultB19, resultC19,
query20: null
query21: null
query22: null
query23: null
query24: null
Display cache list: query19 query18 query17 query16 query15 query14 query13 query9 query6 query2
 */

