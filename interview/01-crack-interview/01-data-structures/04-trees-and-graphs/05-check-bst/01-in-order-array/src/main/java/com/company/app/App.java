package com.company.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Q: check if a binary tree is a binary search tree

A: in-order traversal with array
 */
public class App 
{
    static class Node{
        public int data;
        public Node left;
        public Node right;
        public Node(int data){this.data = data;}
    }
    static Node createTree(int arr[], int startIndex, int endIndex){
        if(endIndex < startIndex) return null;
        int mid = (startIndex + endIndex) / 2;
        Node node = new Node(arr[mid]);
        node.left = createTree(arr, startIndex, mid - 1);
        node.right = createTree(arr, mid + 1, endIndex);
        return node;
    }
    static void bfs(Node node){
        if(node == null) return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(node);
        while(!queue.isEmpty()){
            Node tmp = queue.poll();
            System.out.print(tmp.data + " ");
            if(tmp.left != null) queue.offer(tmp.left);
            if(tmp.right != null) queue.offer(tmp.right);
        }
    }

    // Solution 1: in-order traversal with array
    static void copyBST(Node node, List<Integer> list){  // in-order traversal
        if(node == null) return;
        copyBST(node.left, list);
        list.add(node.data);
        copyBST(node.right, list);
    }
    static boolean checkBST(Node node){
        List<Integer> list = new ArrayList<Integer>();
        copyBST(node, list);
        for(int i = 0; i < list.size() - 1; i++){
            if(list.get(i) > list.get(i + 1)){
                System.out.println("\nnot bst due to (" + list.get(i) + "," + list.get(i + 1) + ")");
                return false;
            }
        }
        return true;
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
        bstTest();
        notBstTest();
    }
    private static void bstTest() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        bfs(root);
        System.out.println("\nis binary search tree?: " + checkBST(root));
    }
    private static void notBstTest() {
        int[] numbers = {1,3,2,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        bfs(root);
        System.out.println("is binary search tree?: " + checkBST(root));
    }
}
