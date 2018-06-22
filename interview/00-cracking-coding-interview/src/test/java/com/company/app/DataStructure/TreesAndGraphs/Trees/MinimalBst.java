package com.company.app.DataStructure.TreesAndGraphs.Trees;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: given a sorted (ascending order) array with unique integers,
   write a algorithm to to create a binary search tree with minimal height.

 */
public class MinimalBst {

    @Test
    public void Test() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Node root = createMinimalBST(numbers, 0, numbers.length - 1);
        System.out.println("Numbers:");
        for (int n : numbers) {
            System.out.print(n + " ");
        }
        System.out.println("\nRoot: " + root.data);
        System.out.println("Bfs:");
        bfs(root);
    }

    static class Node {
        public Node(int data) {
            this.data = data;
        }

        public int data;
        public Node left;
        public Node right;
    }

    static Node createMinimalBST(int arr[], int startIndex, int endIndex) {
        if (endIndex < startIndex) return null;
        int mid = (startIndex + endIndex) / 2;
        Node node = new Node(arr[mid]);
        node.left = createMinimalBST(arr, startIndex, mid - 1);
        node.right = createMinimalBST(arr, mid + 1, endIndex);
        return node;
    }

    static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.remove();
            System.out.print(tmp.data + " ");
            if (tmp.left != null) queue.add(tmp.left);
            if (tmp.right != null) queue.add(tmp.right);
        }
    }
    /*
          {1,2,3,4,5,6,7,8,9} <- data
          [0,1,2,3,4,5,6,7,8] <- index
                   5(arr[4])
                /          \
          {1,2,3,4}      {6,7,8,9}
             2(arr[1])      7
           /   \          /   \
         {1}  {3,4}     {6}   {8,9}
          1    3         6     8
                \               \
                {4}             {9}
                 4               9
     */
}
/*
output:
Numbers:
1 2 3 4 5 6 7 8 9
Root: 5
Bfs:
5 2 7 1 3 6 8 4 9
 */
