package _01_DataStructure.TreesAndGraphs.Trees;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class HasCycle {

    @Test
    public void Test() {
        noCycleTest();

        cycleTest();
    }

    static class Node{
        public Node(int id){
            this.id = id;
        }
        public int id;
        public ArrayList<Node> children = new ArrayList<Node>();
        public void connect(Node node){
            children.add(node);
        }
        public boolean hasCycle(){
            int count = 0;
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(this);
            while(!queue.isEmpty()){
                Node tmp = queue.remove();
                if(tmp.id == this.id){
                    count++;
                    if(count > 1){
                        return true;
                    }
                }
                for(Node n : tmp.children){
                    queue.add(n);
                }
            }
            return false;
        }
    }

    private static void noCycleTest() {
        System.out.println("#noCycleTest");
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        // 1 - [2,4,3]
        node1.connect(node2);
        node1.connect(node4);
        node1.connect(node3);
        // 2 - [3]
        node2.connect(node3);
        // 3 - [4,5]
        node3.connect(node4);
        node3.connect(node5);
        // 4 - [5]
        node4.connect(node5);
        System.out.println(" node1.hasCycle()="+node1.hasCycle());
    }

    private static void cycleTest() {
        System.out.println("#cycleTest");
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        // 1 - [2,5,4]
        node1.connect(node2);
        node1.connect(node5);
        node1.connect(node4);
        // 2 - [3]
        node2.connect(node3);
        // 3 - [4]
        node3.connect(node4);
        // 5 - [4]
        node5.connect(node4);
        // 4 - [1]
        node4.connect(node1);
        System.out.println(" node1.hasCycle()="+node1.hasCycle());
    }
}
/*
output:
#noCycleTest
 node1.hasCycle()=false
#cycleTest
 node1.hasCycle()=true
 */
