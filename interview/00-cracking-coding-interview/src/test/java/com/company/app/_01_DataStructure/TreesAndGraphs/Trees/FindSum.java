package com.company.app._01_DataStructure.TreesAndGraphs.Trees;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: print all paths which sum to a given value. The path does not need to start or end at the root or a leaf.

 */
public class FindSum {

    @Test
    public void Test() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        System.out.println("Root: " + root.data);
        System.out.print("Bfs: ");
        bfs(root);
        System.out.println("");
        System.out.println("#findSum(root, 8)");
        findSum(root, 8);
        System.out.println("#findSum(root, 14)");
        findSum(root, 14);
    }

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
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node tmp = queue.remove();
            System.out.print(tmp.data + " ");
            if(tmp.left != null) queue.add(tmp.left);
            if(tmp.right != null) queue.add(tmp.right);
        }
    }
    static void print(int[] path, int start, int end){
        System.out.print("  Print path: ");
        for(int i = start; i <= end; i++) System.out.print(path[i] + " ");
        System.out.println("");
    }
    static void findSum(Node node, int sum, int[] path, int level){
        if(node == null) return;
        // Insert current node into path.
        path[level] = node.data;
        // Look for paths with a sum that ends at this node.
        int t = 0;
        boolean foundSum = false;
        for(int i = level; i >= 0; i--){
            t += path[i];
            if(t == sum){
                print(path, i, level);
                foundSum = true;
            }
        }
        System.out.println(node.data + " foundSum: " + foundSum);
        // Search nodes beneath this one.
        findSum(node.left, sum, path, level + 1);
        findSum(node.right, sum, path, level + 1);

        /* Remove current node from path. Not strictly necessary,
           since we would ignore this value, but it's good practice.
        */
        path[level] = 0;
    }
    static int depth(Node node){
        if(node == null) return 0;
        else return 1 + Math.max(depth(node.left), depth(node.right));
    }
    static void findSum(Node node, int sum){
        int depth = depth(node);
        int[] path = new int[depth];
        findSum(node, sum, path, 0);
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
}
