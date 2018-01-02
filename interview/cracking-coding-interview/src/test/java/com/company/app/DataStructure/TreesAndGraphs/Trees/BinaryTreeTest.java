package com.company.app.DataStructure.TreesAndGraphs.Trees;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*

inOrder
preOrder
postOrder
dfs
bfs
getHeight
isBalanced

Q: a balanced tree is defined to be a tree such that the heights of
the two subtrees of any node never differ by more than one.

 */
public class BinaryTreeTest {

    @Test
    public void Test() {
        int[] numbers = {7, 5, 8, 4, 1, 6, 9};
        BinaryTree binaryTree = new BinaryTree();
        for (int n : numbers) {
            binaryTree.insert(new Node(n));
        }
        Node root = binaryTree.root;
        System.out.println("***bfs***");
        binaryTree.bfs(root);
        System.out.println("");
        System.out.println("***dfs***");
        binaryTree.dfs(root);

        System.out.println("\n***getHeight***");
        System.out.println(getHeight(root));

        System.out.println("***isBalanced***");
        System.out.println(isBalanced(root));
    }

    static class Node {
        public Node(int data) {
            this.data = data;
        }

        public int data;
        public Node left;
        public Node right;
    }

    static class BinaryTree {
        public Node root;

        public void insert(Node node) {
            if (node == null) return;
            if (root == null) root = node;
            else {
                Node current = root;
                while (true) {
                    if (node.data < current.data) {
                        if (current.left == null) {
                            current.left = node;
                            break;
                        } else {
                            current = current.left;
                        }
                    } else {
                        if (current.right == null) {
                            current.right = node;
                            break;
                        } else {
                            current = current.right;
                        }
                    }
                }
            }
        }

        public void inOrder(Node node) {
            if (node == null) return;
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }

        public void preOrder(Node node) {
            if (node == null) return;
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }

        public void postOrder(Node node) {
            if (node == null) return;
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }

        public void dfs(Node node) {  // Depth First Search
            if (node == null) return;
            Stack<Node> stack = new Stack<Node>();
            stack.push(node);
            while (!stack.isEmpty()) {
                Node tmp = stack.pop();
                System.out.print(tmp.data + " ");
                if (tmp.left != null) stack.push(tmp.left);
                if (tmp.right != null) stack.push(tmp.right);
            }
        }

        public void bfs(Node node) {  // Breadth First Search
            if (node == null) return;
            Queue<Node> queue = new LinkedList<Node>();
            queue.offer(node);
            while (!queue.isEmpty()) {
                Node tmp = queue.poll();
                System.out.print(tmp.data + " ");
                if (tmp.left != null) queue.offer(tmp.left);
                if (tmp.right != null) queue.offer(tmp.right);
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

    static int getHeight(Node root) {
        if (root == null) return 0; // Base case
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    static boolean isBalanced(Node root) {
        if (root == null) return true; // Base case
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {  // Recurse
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }
}
/*
output:
***bfs***
7 5 8 4 6 9 1
***dfs***
7 8 9 5 6 4 1
***getHeight***
4
***isBalanced***
true
 */
