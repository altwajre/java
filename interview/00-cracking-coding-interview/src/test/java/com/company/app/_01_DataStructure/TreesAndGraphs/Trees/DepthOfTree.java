package com.company.app._01_DataStructure.TreesAndGraphs.Trees;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: get the depth of tree

 */
public class DepthOfTree {

    @Test
    public void Test() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        bfs(root);
        System.out.println("\n  depth: " + depth(root));
        System.out.println("#add a new node to node 4 right child");
        Node n4 = root.left.right.right;
        n4.right = new Node(11);
        System.out.println("  depth: "+depth(root));
    }

    static class Node{
        public int data;
        public Node left;
        public Node right;
        public Node(int data){this.data=data;}
    }
    static Node createTree(int[] arr, int min, int max){
        if(max < min) return null;
        int mid = (min + max)/2;
        Node node = new Node(arr[mid]);
        node.left = createTree(arr, min, mid-1);
        node.right = createTree(arr, mid+1, max);
        return node;
    }
    static void bfs(Node node){
        if(node == null) return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node tmp = queue.remove();
            System.out.print(tmp.data + " ");
            if(tmp.left != null) queue.add(tmp.left);
            if(tmp.right != null) queue.add(tmp.right);
        }
    }
    static int depth(Node node){
        if(node == null)
            return 0;
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        int max = Math.max(leftDepth, rightDepth);
        return 1 + max;
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
/*
output:
5 2 7 1 3 6 8 4 9
  depth: 4
#add a new node to node 4 right child
  depth: 5
 */
