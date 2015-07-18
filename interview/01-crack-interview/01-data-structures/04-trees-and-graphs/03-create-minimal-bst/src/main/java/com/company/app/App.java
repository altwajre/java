package com.company.app;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: given a sorted (ascending order) array with unique integers,
   write a algorithm to to create a binary search tree with minimal height.

 */
public class App 
{
    static class Node {
        public Node(int data){this.data = data;}
        public int data;
        public Node left;
        public Node right;
    }
    static Node createMinimalBST(int arr[], int start, int end){
        if(end < start) return null;
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.left = createMinimalBST(arr, start, mid - 1);
        node.right = createMinimalBST(arr, mid + 1, end);
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
    public static void main( String[] args )
    {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createMinimalBST(numbers, 0, numbers.length - 1);
        System.out.println("Numbers:");
        for(int n : numbers){
            System.out.print(n + " ");
        }
        System.out.println("\nRoot: " + root.data);
        System.out.println("Bfs:");
        bfs(root);
    }
}
