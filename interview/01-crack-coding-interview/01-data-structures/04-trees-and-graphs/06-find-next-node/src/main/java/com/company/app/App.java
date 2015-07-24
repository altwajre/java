package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
Q: find the 'next' node of a given node in a binary search tree.
   assume that each node has a link to its parent.

 */
public class App 
{
    static class Node{
        public int data;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int data){this.data = data;}
    }
    static Node createTree(int arr[], int startIndex, int endIndex){
        if(endIndex < startIndex) return null;
        int mid = (startIndex + endIndex) / 2;
        Node node = new Node(arr[mid]);
        node.left = createTree(arr, startIndex, mid - 1);
        if(node.left != null) node.left.parent = node;
        node.right = createTree(arr, mid + 1, endIndex);
        if(node.right != null) node.right.parent = node;
        return node;
    }
    static List<Node> list = new ArrayList<Node>();
    static void inOrder(Node node){
        if(node == null) return;
        inOrder(node.left);
        list.add(node);
        inOrder(node.right);
    }
    // Solution:
    static Node findNext(Node node){
        if(node == null) return null;
        // Found right children -> return leftmost node of right subtree
        if(node.right != null){
            return leftMostChild(node.right);
        }
        else{
            Node n = node;
            Node p = n.parent;
            // Go up until we're on left instead of right
            while(p != null && p.left != n){
                n = p;
                p = p.parent;
            }
            return p;
        }
    }
    static Node leftMostChild(Node node){
        if(node == null) return null;
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    /*
                   5
                /     \
              2        7
            /  \      /  \
          1     3    6    8
                \          \
                 4          9
     */
    public static void main( String[] args )
    {
        findRootNext();
        find1Next();
        find4Next();
        find9Next();
    }
    private static void findRootNext() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        inOrder(root);
        Node next = findNext(root);
        System.out.println("" + root.data + "'s next: " + next.data);
    }
    private static void find1Next() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        inOrder(root);
        Node ll = root.left.left;
        Node next = findNext(ll);
        System.out.println(ll.data + "'s next: " + next.data);
    }
    private static void find4Next() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        inOrder(root);
        Node lrr = root.left.right.right;
        Node next = findNext(lrr);
        System.out.println(lrr.data + "'s next: " + next.data);
    }
    private static void find9Next() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        inOrder(root);
        Node rrr = root.right.right.right;
        Node next = findNext(rrr);
        if(next == null){
            System.out.println(rrr.data + "'s next: " + "null");
        }
    }
}
