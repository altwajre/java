package _01_DataStructure.TreesAndGraphs.Trees;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
Q: check if T2 is a subtree of T1

 */
public class IsSubtree {

    @Test
    public void Test() {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        Node root = createTree(numbers, 0, numbers.length - 1);
        System.out.println("Root: " + root.data);
        System.out.print("Bfs: ");
        bfs(root);
        System.out.println("");

        subTreeTest(root);
        notSubTreeTest(root);
    }

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
    static boolean containsTree(Node t1, Node t2){
        if(t2 == null) return true; // The empty tree is always a subtree
        return isSubTree(t1, t2);
    }
    static boolean isSubTree(Node r1, Node r2){
        if(r1 == null) return false; // big tree empty & subtree still not found.
        if(r1.data == r2.data){
            if(matchTree(r1, r2)) return true;
        }
        return isSubTree(r1.left, r2) || isSubTree(r1.right, r2);
    }
    static boolean matchTree(Node r1, Node r2){
        if(r2 == null && r1 == null) return true; // nothing left in the subtree
        // if one, but not both, are empty
        if(r1 == null || r2 == null) return false;
        if(r1.data != r2.data) return false;  // data doesn't match
        return (matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right));
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

    static void subTreeTest(Node root) {
        System.out.println("#subTreeTest");
        Node n2 = root.left;
        boolean result = containsTree(root, n2);
        System.out.println("is " + n2.data + " a subtree of " + root.data + "? " + result);
    }
    static void notSubTreeTest(Node root){
        System.out.println("#notSubTreeTest");
        Node node = new Node(18);
        boolean result = containsTree(root, node);
        System.out.println("is " + node.data + " a subtree of " + root.data + "? " + result);
    }
}
/*
output:
Root: 5
Bfs: 5 2 7 1 3 6 8 4 9
#subTreeTest
is 2 a subtree of 5? true
#notSubTreeTest
is 18 a subtree of 5? false
 */
