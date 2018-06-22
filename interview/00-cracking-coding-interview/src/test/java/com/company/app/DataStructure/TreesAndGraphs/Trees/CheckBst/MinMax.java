package com.company.app.DataStructure.TreesAndGraphs.Trees.CheckBst;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: check if a binary tree is a binary search tree

A: min max solution
 */
public class MinMax {

    @Test
    public void Test() {
        bstTest();
        notBstTest();
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
    // Solution: min max
    static boolean checkBST(Node node, int min, int max){
        if(node == null) return true;
        if(node.data < min || node.data >= max){
            if(node.data < min)
                System.out.println("\nnot bst due to 'node.data < min' (" + min + "," + node.data + ")");
            if(node.data > max)
                System.out.println("\nnot bst due to 'node.data >= max' (" + node.data + "," + max + ")");
            return false;
        }
        if(!checkBST(node.left, min, node.data) || !checkBST(node.right, node.data, max)) return false;
        return true;
    }
    static boolean checkBST(Node node){
        return checkBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    /*  Valid BST     Invalid BST
             20          20
            /              \
          20                20
     */

    /*
                   5
                /     \
              2        7
            /  \      /  \
          1     3    6    8
                \          \
                 4          9
     */
    private static void bstTest() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        bfs(root);
        System.out.println("\nis binary search tree?: " + checkBST(root));
    }
    /*
                   5
                /     \
              3        7
            /  \      /  \
          1     2    6    8
                \          \
                 4          9
     */
    private static void notBstTest() {
        int[] numbers = {1,3,2,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        bfs(root);
        System.out.println("is binary search tree?: " + checkBST(root));
    }
}
/*
output:
5 2 7 1 3 6 8 4 9
is binary search tree?: true
5 3 7 1 2 6 8 4 9
not bst due to 'node.data < min' (3,2)
is binary search tree?: false
 */
