package com.company.app;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class App
{
    static class Node{
        public Node(int data){this.data = data;}
        public int data;
        public Node left;
        public Node right;
    }
    static class BinaryTree{
        public Node root;
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
        public void inOrder(Node node){
            if(node == null) return;
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
        public void preOrder(Node node){
            if(node == null) return;
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
        public void postOrder(Node node){
            if(node == null) return;
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
        public void dfs(Node node){  // Depth First Search
            if(node == null) return;
            Stack<Node> stack = new Stack<Node>();
            stack.push(node);
            while(!stack.isEmpty()){
                Node tmp = stack.pop();
                System.out.print(tmp.data + " ");
                if(tmp.left != null) stack.push(tmp.left);
                if(tmp.right != null) stack.push(tmp.right);
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
    /*            7
                /   \
               5     8
             /  \     \
            4   6      9
           /
          1
     */
    public static void main( String[] args )
    {
        int[] numbers = {7,5,8,4,1,6,9};
        BinaryTree binaryTree = new BinaryTree();
        for(int n : numbers){
            binaryTree.insert(new Node(n));
        }
        Node root = binaryTree.root;
        System.out.println("***in order***");
        binaryTree.inOrder(root);
        System.out.println("\n***pre order***");
        binaryTree.preOrder(root);
        System.out.println("\n***post order***");
        binaryTree.postOrder(root);
        System.out.println("\n***dfs***");
        binaryTree.dfs(root);
        System.out.println("\n***bfs***");
        binaryTree.bfs(root);
    }
}
