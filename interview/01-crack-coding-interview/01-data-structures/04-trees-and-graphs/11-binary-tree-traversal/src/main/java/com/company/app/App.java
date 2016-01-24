package com.company.app;

/*
In-Order, Pre-Order, Post-Order
 */
class Node{
    public Node(int data){this.data = data;}
    public int data;
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
}
public class App
{
    public static void main( String[] args )
    {
        testBinaryTreeTraversal();
        testCreateMinimalBST();
    }

    private static void testCreateMinimalBST() {
        System.out.println("\n\n##testCreateMinimalBST");
        int[] numbers = {7,5,8,4,1,6,9};
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createMinimalBST(numbers);
        Node root = binaryTree.root;
        System.out.println("***in-order***");
        binaryTree.inOrder(root);
        System.out.println("\n***pre-order***");
        binaryTree.preOrder(root);
        System.out.println("\n***post-order***");
        binaryTree.postOrder(root);
    }

    private static void testBinaryTreeTraversal() {
        System.out.println("##testBinaryTreeTraversal");
        int[] numbers = {7,5,8,4,1,6,9};
        BinaryTree binaryTree = new BinaryTree();
        for(int n : numbers){
            binaryTree.insert(new Node(n));
        }
        Node root = binaryTree.root;
        System.out.println("***in-order***");
        binaryTree.inOrder(root);
        System.out.println("\n***pre-order***");
        binaryTree.preOrder(root);
        System.out.println("\n***post-order***");
        binaryTree.postOrder(root);
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
##testBinaryTreeTraversal
***in order***
1 4 5 6 7 8 9
***pre order***
7 5 4 1 6 8 9
***post order***
1 4 6 5 9 8 7

##testCreateMinimalBST
***in order***
7 5 8 4 1 6 9
***pre order***
4 5 7 8 6 1 9
***post order***
7 8 5 1 9 6 4
 */
