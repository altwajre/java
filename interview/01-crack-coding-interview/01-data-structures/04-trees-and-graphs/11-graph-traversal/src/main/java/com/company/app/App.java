package com.company.app;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
Breadth-First Search
https://en.wikipedia.org/wiki/Breadth-first_search

Depth-First Search
https://en.wikipedia.org/wiki/Depth-first_search
 */
class Node{
    public Node(int data){this.data = data;}
    public int data;
    public boolean visited;
    public Node left;
    public Node right;
}
class BinaryTree{
    public Node root;
    public void createMinimalBST(int arr[]){
        root = createMinimalBST(arr, 0, arr.length - 1);
    }
    // create a binary search tree with minimal height
    private Node createMinimalBST(int arr[], int start, int end){
        if(end < start) return null;
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.left = createMinimalBST(arr, start, mid - 1);
        node.right = createMinimalBST(arr, mid + 1, end);
        return node;
    }
    public void insert(Node node){
        if(node == null) return;
        if(root == null) root = node;
        else{
            Node current = root;
            while (true){
                if(node.data < current.data){
                    if(current.left == null){
                        current.left = node;
                        break;
                    }
                    else{ current = current.left; }
                }
                else{
                    if(current.right == null){
                        current.right = node;
                        break;
                    }
                    else{ current = current.right; }
                }
            }
        }
    }
    public void preOrder(Node node){
        if(node == null) return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
    /*
    To print order at https://en.wikipedia.org/wiki/Depth-first_search, push the right child into stack first.

    Similarity:
    DFS and Pre-order traversal is the same.

    Difference:
    DFS is a search, it stops when it finds its target element.
    Pre-order traversal is a traversal and not a search, it visits all the elements in the tree.
     */
    public void dfs(Node node){  // Depth First Search
        if(node == null) return;
        Stack<Node> stack = new Stack<Node>();
        node.visited = true;
        stack.push(node);
        while(!stack.isEmpty()){
            Node tmp = stack.pop();
            System.out.print(tmp.data + " ");
            if(tmp.right != null && !tmp.right.visited) {
                tmp.right.visited = true;
                stack.push(tmp.right);
            }
            if(tmp.left != null && !tmp.left.visited){
                tmp.left.visited = true;
                stack.push(tmp.left);
            }
        }
    }
    public void bfs(Node node){  // Breadth First Search
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
}
public class App
{
    public static void main( String[] args )
    {
        int[] numbers = {7,5,8,4,1,6,9};
        BinaryTree binaryTree = new BinaryTree();
        for(int n : numbers){
            binaryTree.insert(new Node(n));
        }
        Node root = binaryTree.root;
        System.out.println("***pre-order***");
        binaryTree.preOrder(root);
        System.out.println("\n***dfs***");
        binaryTree.dfs(root);
        System.out.println("\n***bfs***");
        binaryTree.bfs(root);
    }
}
/*
              7
            /   \
           5     8
         /  \     \
        4   6      9
       /
      1

output:
***pre-order***
7 5 4 1 6 8 9
***dfs***
7 5 4 1 6 8 9
***bfs***
7 5 8 4 6 9 1
 */