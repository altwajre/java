package com.company.app;

import java.util.*;

/*
Q: Given a directed graph, find out whether there is a route between two nodes.

A: breath first search
 */
public class App
{
    static class Node {
        private int value;
        private boolean discovered;
        private List<Node> adj;
        public Node(int value) {
            this.value = value;
            this.adj = new ArrayList<Node>();
        }
        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
        public boolean isDiscovered() { return discovered; }
        public void setDiscovered(boolean discovered) { this.discovered = discovered; }
        public List<Node> getAdj() { return adj; }
        public void setAdj(List<Node> adj) { this.adj = adj; }
        public void connect(Node node){ this.adj.add(node); }
        @Override
        public String toString(){ return value + ""; }
        @Override
        public boolean equals(Object o){
            if(this == o){return true;}
            if(o == null || this.getClass() != o.getClass()){ return false; }
            Node node = (Node) o;
            if(value != node.value){ return false; }
            return true;
        }
        @Override
        public int hashCode(){ return value; }
    }
    static class Graph{
        private Map<Integer, Node> nodes;
        public Graph(int[][] adj_matrix){
            if(adj_matrix == null || adj_matrix.length == 0){
                throw new IllegalArgumentException("adj matrix is null or empty");
            }
            nodes = new HashMap<Integer, Node>();
            // create a graph with the an adjacency list
            Node node1 = null;
            Node node2 = null;
            for(int i = 0; i < adj_matrix.length; i++){
                int key1 = i + 1;
                node1 = nodes.get(key1);
                if(node1 == null){
                    node1 = new Node(key1);
                    nodes.put(key1, node1);
                }
                for(int j = 0; j < adj_matrix[i].length; j++){
                    int key2 = j + 1;
                    node2 = nodes.get(key2);
                    if(node2 == null){
                        node2 = new Node(key2);
                        nodes.put(key2, node2);
                    }
                    if(adj_matrix[i][j] == 1){ node1.connect(node2); }
                }
            }
        }
        public Map<Integer, Node> getNodes(){ return nodes; }
    }
    // perform a depth first or breath first search and see if you hit a node - O([V| + |E|)
    static boolean reachable(Graph graph, Node src, Node dest){
        if(src.equals(dest)){ return true; }
        // conduct a BFS or DFS and check to see if dest is reachable from src. here we do a BFS
        for(Node node : graph.getNodes().values()){ node.setDiscovered(false); }
        Queue<Node> queue = new LinkedList<Node>();
        src.setDiscovered(true);
        queue.add(src);
        Node node;
        while(!queue.isEmpty()){
            node = queue.remove();
            for(Node adj : node.getAdj()){
                if(adj.equals(dest)){ return true; }
                if(!adj.isDiscovered()){
                    adj.setDiscovered(true);
                    queue.add(adj);
                }
            }
        }
        return false;
    }
    public static void main( String[] args )
    {
        // create a graph
        /* Graph.nodes {{1,[1]},{2,[2]},{3,[3]},{4,[4]},{5,{[5]},{6,[6]}}
          [1]-[2]
            \ |
             \|
         [4]-[5]   [3]
          |
         [6]
         */
        int[][] adjacency_matrix = {
                // 1  2  3  4  5  6
         /* 1 */  {1, 1, 0, 0, 1, 0},
         /* 2 */  {1, 0, 0, 0, 1, 0},
         /* 3 */  {0, 0, 0, 0, 0, 0},
         /* 4 */  {0, 0, 0, 0, 1, 1},
         /* 5 */  {1, 1, 0, 1, 0, 0},
         /* 6 */  {0, 0, 0, 1, 0, 0}
        };
        Graph graph = new Graph(adjacency_matrix);
        Map<Integer, Node> nodes = graph.getNodes();
        Node src = nodes.get(2);
        Node dest = nodes.get(3);
        System.out.println("is 2 -> 3 reachable: " + reachable(graph, src, dest));
        src = nodes.get(1);
        dest = nodes.get(6);
        System.out.println("is 1 -> 6 reachable: " + reachable(graph, src, dest));
    }
}
