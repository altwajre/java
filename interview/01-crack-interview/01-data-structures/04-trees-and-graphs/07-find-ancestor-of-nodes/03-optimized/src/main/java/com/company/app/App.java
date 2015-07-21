package com.company.app;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: find the first common ancestor of two nodes in a binary tree

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
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node tmp = queue.remove();
            System.out.print(tmp.data + " ");
            if(tmp.left != null) queue.add(tmp.left);
            if(tmp.right != null) queue.add(tmp.right);
        }
    }
    // The below code has a bug.
    static Node commonAncestorBad(Node root, Node d1, Node d2){
        if(root == null) {
            return null;
        }
        if(root == d1 && root == d2){
            return root;
        }
        Node l = commonAncestorBad(root.left, d1, d2);
        if(l != null && l != d1 && l != d2){  // found ancestor
            return l;
        }
        Node r = commonAncestorBad(root.right, d1, d2);
        if(r != null && r != d1 && r != d2){
            return r;
        }
        if(l != null && r != null){  // p and q found in diff subtrees
            return root;  // This is the common ancestor
        }
        else if(root == d1 || root == d2){
            return root;
        }
        else{
            // If either x or y is non-null, return the non-null vlaue
            return l == null ? r : l;
        }
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
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        System.out.println("Root: " + root.data);
        System.out.print("Bfs: ");
        bfs(root);
        Node n1 = root.left.left;
        Node n4 = root.left.right.right;
        Node ca = commonAncestorBad(root, n1, n4);
        System.out.print("\n" + n1.data + " & " + n4.data +" commonAncestor is ");
        System.out.print(ca.data);
    }
}
