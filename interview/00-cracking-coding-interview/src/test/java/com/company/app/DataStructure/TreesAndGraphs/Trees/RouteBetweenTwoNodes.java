package com.company.app.DataStructure.TreesAndGraphs.Trees;

import org.junit.Test;

import java.util.*;

/*
Q: Given a directed graph, find out whether there is a route between two nodes

A: breath first search

https://en.wikipedia.org/wiki/Directed_graph
 */
public class RouteBetweenTwoNodes {

    @Test
    public void Test() {
        // create a graph
        /* GraphTestFoo.nodes: {{1,[1]},{2,[2]},{3,[3]},{4,[4]},{5,{[5]},{6,[6]}}
          [1]-[2]
            \ |
             \|
         [4]-[5]   [3]
          |
         [6]
         */
        int[][] adjacencyMatrix = {
                // 1  2  3  4  5  6
                /* 1 */  {1, 1, 0, 0, 1, 0},
                /* 2 */  {1, 0, 0, 0, 1, 0},
                /* 3 */  {0, 0, 0, 0, 0, 0},
                /* 4 */  {0, 0, 0, 0, 1, 1},
                /* 5 */  {1, 1, 0, 1, 0, 0},
                /* 6 */  {0, 0, 0, 1, 0, 0}
        };
        Graph graph = new Graph(adjacencyMatrix);
        Map<Integer, Node> nodeMap = graph.nodes;
        Node src = nodeMap.get(2);
        Node dest = nodeMap.get(3);
        System.out.println("is 2 -> 3 reachableFromParent: " + reachable(graph, src, dest));
        src = nodeMap.get(1);
        dest = nodeMap.get(6);
        System.out.println("is 1 -> 6 reachableFromParent: " + reachable(graph, src, dest));
    }

    static class Node{  // it is also vertice
        public int value;
        public boolean isVisited;
        public List<Node> adjacent;
        public Node(int value){
            this.value = value;
            this.adjacent = new ArrayList<Node>();
        }
        public void connect(Node node){ this.adjacent.add(node); }
    }
    static class Graph{
        public Map<Integer, Node> nodes;
        public Graph(int[][] adjacentMatrix){
            nodes = new HashMap<Integer, Node>();
            // create a graph with the an adjacency list
            Node node1, node2;
            for(int i = 0; i < adjacentMatrix.length; i++){ // row by row - y coordinate
                int key1 = i + 1;
                node1 = nodes.get(key1);
                if(node1 == null){
                    node1 = new Node(key1);
                    nodes.put(key1, node1);
                }
                for(int j = 0; j < adjacentMatrix[i].length; j++){  // x coordinate
                    int key2 = j + 1;
                    node2 = nodes.get(key2);
                    if(node2 == null) {
                        node2 = new Node(key2);
                        nodes.put(key2, node2);
                    }
                    if(adjacentMatrix[i][j] == 1){node1.connect(node2);}
                }
            }
        }
    }
    // perform a depth first or breath first search and see if you hit a node - O([V| + |E|)
    static boolean reachable(Graph graph, Node src, Node dest){
        if(src == dest) {return true;}
        for(Node node : graph.nodes.values()){node.isVisited = false;}
        Queue<Node> queue = new LinkedList<Node>();
        src.isVisited = true;
        queue.add(src);
        while(!queue.isEmpty()){
            Node tmp = queue.remove();
            for(Node node : tmp.adjacent){  // go through all neighbor nodes
                if(node == dest) return true;  // reach to the dest node, return true
                if(!node.isVisited){  // add unvisited neighbor nodes to the queue
                    node.isVisited = true;
                    queue.add(node);
                }
            }
        }
        return false;
    }
}
/*
output:
is 2 -> 3 reachableFromParent: false
is 1 -> 6 reachableFromParent: true
 */
