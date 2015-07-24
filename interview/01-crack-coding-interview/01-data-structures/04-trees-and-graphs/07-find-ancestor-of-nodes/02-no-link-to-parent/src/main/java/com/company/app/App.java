package com.company.app;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: find the first common ancestor of two nodes in a binary tree

A: start from the root to children, return common ancestor when two nodes are on different sides.
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
    // Returns true if p is a descendent of root
    static boolean covers(Node root, Node p){
        if(root == null) return false;
        if(root == p) return true;
        return covers(root.left, p) || covers(root.right, p);
    }
    static Node commonAncestorHelper(Node root, Node p, Node q){
        if(root == null) return null;
        if(root == p || root == q) return root;
        boolean is_p_on_left = covers(root.left, p);
        boolean is_q_on_left = covers(root.left, q);
        // if p and q are on different sides, return root.
        if(is_p_on_left != is_q_on_left) {
            return root;
        }
        // Else, they are on the same side. Traverse this side.
        Node child_side = is_p_on_left ? root.left : root.right;
        return commonAncestorHelper(child_side, p, q);
    }
    static Node commonAncestor(Node root, Node p, Node q){
        if(!covers(root, p) || !covers(root, q)){  // Error check
            return null;
        }
        return commonAncestorHelper(root, p, q);
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
        Node ca = commonAncestor(root, n1, n4);
        System.out.print("\n" + n1.data + " & " + n4.data +" commonAncestor is ");
        System.out.print(ca.data);
    }
}
