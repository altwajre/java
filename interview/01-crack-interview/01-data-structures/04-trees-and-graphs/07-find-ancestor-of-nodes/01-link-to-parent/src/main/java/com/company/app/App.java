package com.company.app;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: find the first common ancestor of two nodes in a binary tree

A: go to a node's parent node, and use bfs to check if it is reachable to the other node
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
    static void bfs(Node node){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node tmp = queue.remove();
            System.out.print(tmp.data + " ");
            if(tmp.left != null) queue.add(tmp.left);
            if(tmp.right != null) queue.add(tmp.right);
        }
    }
    static boolean reachable(Node ancestor, Node descendant){
        if(ancestor == descendant) return true;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(ancestor);
        while(!queue.isEmpty()){
            Node tmp = queue.remove();
            if(tmp == descendant) return true;
            if(tmp.left != null) queue.add(tmp.left);
            if(tmp.right != null) queue.add(tmp.right);
        }
        return false;
    }
    static Node commonAncestor(Node x, Node y){
        while(x != null){
            if(reachable(x, y)) return x;
            x = x.parent;
        }
        while(y != null){
            if(reachable(y, x)) return y;
            y = y.parent;
        }
        return null;
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
    public static void main( String[] args)
    {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        System.out.println("Root: " + root.data);
        System.out.print("Bfs: ");
        bfs(root);
        reachableTest(root);
    }

    static void reachableTest(Node root) {
        System.out.println("\n#reachableTest");
        Node n1 = root.left.left;
        Node n4 = root.left.right.right;
        Node ca = commonAncestor(n1, n4);
        System.out.print(n1.data + " & " + n4.data +" commonAncestor is ");
        System.out.print(ca.data);
    }
}
