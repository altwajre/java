package com.company.app;

import java.util.LinkedList;
import java.util.Queue;

/*
 */
public class App 
{
    static class TreeNode{
        public TreeNode(int data){this.data = data;}
        public int data;
        public TreeNode left;
        public TreeNode right;
    }
    static TreeNode createMinimalBST(int arr[], int start, int end){
        if(end < start) return null;
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(arr[mid]);
        n.left = createMinimalBST(arr, start, mid - 1);
        n.right = createMinimalBST(arr, mid + 1, end);
        return n;
    }
    static void bfs(TreeNode node){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(node);
        while(!queue.isEmpty()){
            TreeNode tmp = queue.remove();
            System.out.print(tmp.data + " ");
            if(tmp.left != null) queue.add(tmp.left);
            if(tmp.right != null) queue.add(tmp.right);
        }
    }
    public static void main( String[] args )
    {
        int[] numbers = {7,2,5,1,8,4,9,3,6};
        TreeNode root = createMinimalBST(numbers, 0, numbers.length - 1);
        for(int n : numbers){
            System.out.print(n + " ");
        }
        System.out.println("\nRoot: " + root.data);
        System.out.println("Bfs:");
        bfs(root);
    }
}
