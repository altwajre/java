package _01_DataStructure.TreesAndGraphs.Graphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphTest {

    @Test
    public void Test() {
        Graph graph = new Graph("A B 3\n X Y 8");
        System.out.println(graph);
        Node[] nodes = graph.nodes;
        System.out.print("#Graph.nodes: ");
        for(Node node : nodes){
            if(node != null) System.out.print(node.data + " ");
            else System.out.print("\\0" + " ");
        }
        System.out.println("\n#Non-null Nodes:");
        for(Node node : nodes){
            if(node != null) System.out.println(node);
        }
    }

    static class Edge{  // Edges are directed, represented using pairs of Nodes
        public Node from, to;
        public int cost;
        public Edge(Node from, Node to, int cost){ this.from = from; this.to = to; this.cost = cost; }
        public String toString(){ return "\t" + from.data + " -> " + to.data + " [ cost=" + cost + " ]"; }
    }
    static class Node{
        public char data;
        private List<Edge> edgeList = new ArrayList<Edge>();
        public Node(char l){this.data =l;}
        public int getIndex(){return data - 'A';}
        public void addEdge(Edge edge){ edgeList.add(edge); }
        public String toString(){
            String out = "Node " + data + "[" + getIndex() + "]\n";
            for(Edge edge : edgeList){ out = out + edge.toString(); }
            return out;
        }
    }
    static class Graph{
        public Node[] nodes;
        private static final int MAX_NODES = 26;
        private Edge[] edges = new Edge[MAX_NODES * MAX_NODES];
        private int numberEdges = 0;
        private Scanner input;
        public Graph(String s){
            nodes = new Node[MAX_NODES];
            input = new Scanner(s);
            while(input.hasNext()){
                char from, to;
                int cost;
                from = input.next().charAt(0);
                to = input.next().charAt(0);
                cost = input.nextInt();
                int fromIndex = from - 'A';
                int toIndex = to - 'A';
                // Create nodes if data is new; no checking for now.
                if(nodes[fromIndex] == null){ nodes[fromIndex] = new Node(from); }
                if(nodes[toIndex] == null){ nodes[toIndex] = new Node(to); }
                // Create edge, add edge to list of edges for the "from" node
                edges[numberEdges] = new Edge(nodes[fromIndex], nodes[toIndex], cost);
                nodes[fromIndex].addEdge(edges[numberEdges]);
                numberEdges++;
            }
        }
        // Outputs a .dot file as a String
        public String toString(){
            String s = "#digraph g {\n";
            for(int i = 0; i < numberEdges; i++){ s = s + edges[i].toString() + "\n"; }
            return s + "}";
        }
    }
}
/*
output:
#digraph g {
	A -> B [ cost=3 ]
	X -> Y [ cost=8 ]
}
#Graph.nodes: A B \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 X Y \0
#Non-null Nodes:
Node A[0]
	A -> B [ cost=3 ]
Node B[1]

Node X[23]
	X -> Y [ cost=8 ]
Node Y[24]
 */
