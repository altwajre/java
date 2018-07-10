package com.company.app._01_DataStructure.TreesAndGraphs.Trees;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class LevelLinkedlist {

    @Test
    public void Test() {
        /* pre-order
        5(o:1,l:0)
        2(o:2,l:1) 7(o:6,l:1)
        1(o:3,l:2) 3(o:4,l:2) 6(o:7,l:2) 8(o:8,l:2)
        4(o:5,l:3) 9(o:9,l:3)
         */
        createLevelLinkedListRecurseTest();

        order = 1; // reset order
        // breadth first search
        createLevelLinkedListTest();
    }

    static class Node {
        public int level;
        public int order;
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    static Node createTree(int arr[], int startIndex, int endIndex) {
        if (endIndex < startIndex) return null;
        int mid = (startIndex + endIndex) / 2;
        Node node = new Node(arr[mid]);
        node.left = createTree(arr, startIndex, mid - 1);
        node.right = createTree(arr, mid + 1, endIndex);
        return node;
    }

    static int order = 1;

    // Solution 1: pre-order
    static void createLevelLinkedListRecurse(Node node, ArrayList<LinkedList<Node>> list, int level) {
        if (node == null) return;  // base case
        LinkedList<Node> linkedList = null;
        if (list.size() == level) {  // level not contained in list
            linkedList = new LinkedList<Node>();
            /* Levels are always traversed in order. So, if this is the first we've visited level i,
               we must have seen levels 0 through i - 1. We can therefore safely add the level at level at the end.
             */
            list.add(linkedList);
        } else {
            linkedList = list.get(level);
        }
        node.level = level;
        node.order = order;
        order++;
        linkedList.add(node);
        createLevelLinkedListRecurse(node.left, list, level + 1);
        createLevelLinkedListRecurse(node.right, list, level + 1);
    }

    static ArrayList<LinkedList<Node>> createLevelLinkedListRecurse(Node root) {
        ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
        createLevelLinkedListRecurse(root, lists, 0);
        return lists;
    }

    // Solution 2: breadth first search
    static ArrayList<LinkedList<Node>> createLevelLinkedList(Node root) {
        ArrayList<LinkedList<Node>> list = new ArrayList<LinkedList<Node>>();
        // "Visit" the root
        LinkedList<Node> linkedList = new LinkedList<Node>();
        if (root != null) {
            root.order = order;
            order++;
            linkedList.add(root);
        }
        while (linkedList.size() > 0) {
            list.add(linkedList);  // Add previous level
            LinkedList<Node> parents = linkedList;  // Go to next level
            linkedList = new LinkedList<Node>();
            for (Node parent : parents) {
                // Visit the children
                if (parent.left != null) {
                    parent.left.order = order;
                    order++;
                    linkedList.add(parent.left);
                }
                if (parent.right != null) {
                    parent.right.order = order;
                    order++;
                    linkedList.add(parent.right);
                }
            }
        }
        return list;
    }

    /*
          [0,1,2,3,4,5,6,7,8] <- index
          {1,2,3,4,5,6,7,8,9} <- data
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

    private static void createLevelLinkedListTest() {
        System.out.println("#createLevelLinkedListTest");
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        ArrayList<LinkedList<Node>> levelLinkedList = createLevelLinkedList(root);
        for (LinkedList<Node> linkedList : levelLinkedList) {
            for (Node node : linkedList) {
                System.out.print(node.data + "(" + node.order + ")" + " ");
            }
            System.out.println("");
        }
    }

    private static void createLevelLinkedListRecurseTest() {
        System.out.println("#createLevelLinkedListRecurseTest");
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        ArrayList<LinkedList<Node>> levelLinkedList = createLevelLinkedListRecurse(root);
        for (LinkedList<Node> linkedList : levelLinkedList) {
            for (Node node : linkedList) {
                System.out.print(node.data + "(o:" + node.order + ",l:" + node.level + ")" + " ");
            }
            System.out.println("");
        }
    }
}
