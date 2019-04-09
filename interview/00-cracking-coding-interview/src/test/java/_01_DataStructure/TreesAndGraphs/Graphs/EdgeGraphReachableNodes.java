package _01_DataStructure.TreesAndGraphs.Graphs;

import org.junit.Test;

import java.util.*;

// Graph has nodes (store only uppercase letters) and edges, bfs()
public class EdgeGraphReachableNodes {

    @Test
    public void Test() {
        // Create a graph: add a new node by itself "C C 0"
        /* Graph.nodes: {{1,[1]},{2,[2]},{3,[3]},{4,[4]},{5,{[5]},{6,[6]}}
          [A]-[B]
            \ |
             \|
         [D]-[E]   [C]
          |
         [F]
         */
        String input = "A B 1\n A E 2\n B A 1\n B E 2\n C C 0\n D E 1\n D F 1\n E A 2\n E B 2\n E D 1\n F D 1";
        Graph graph = new Graph(input);
        Node[] nodes = graph.nodes;
        System.out.print("#Graph.nodes: \n");
        for(Node node : nodes){
            if(node != null){
                String out = node.data + "(edges:";
                for(Edge edge : node.edgeList){
                    out = out + edge.from.data + "->" + edge.to.data + ",";
                }
                out = out + ");";
                System.out.println(out);
            }
            else System.out.print("\\0" + " ");
        }
        Node src = nodes[1];  // B
        Node dest = nodes[2];  // C
        System.out.println("\nis B -> C reachable: " + reachable(graph, src, dest));
        src = nodes[0];  // A
        dest = nodes[5];  // F
        System.out.println("is A -> F reachable: " + reachable(graph, src, dest));
    }

    static class Edge{  // Edges are directed (A -> B), represented using pairs of Nodes
        public Node from, to;
        public int cost;
        public Edge(Node from, Node to, int cost){ this.from = from; this.to = to; this.cost = cost; }
    }
    static class Node{
        public boolean isVisited;
        public char data;
        public List<Edge> edgeList;
        public Node(char l){this.data =l; edgeList = new ArrayList<Edge>();}
        public void addEdge(Edge edge){ edgeList.add(edge); }
    }
    static class Graph{
        public Node[] nodes;
        private static final int MAX_NODES = 26;
        private Edge[] edges = new Edge[MAX_NODES * MAX_NODES];
        private int edgeIndex = 0;
        private Scanner input;
        public Graph(String s){
            nodes = new Node[MAX_NODES];
            input = new Scanner(s);
            System.out.println("#Create graph: ");
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
                edges[edgeIndex] = new Edge(nodes[fromIndex], nodes[toIndex], cost);
                nodes[fromIndex].addEdge(edges[edgeIndex]);
                Edge e = edges[edgeIndex];
                System.out.println("edges[" + edgeIndex + "]: " + e.from.data + " -> " + e.to.data + "; cost: " + e.cost);
                edgeIndex++;
            }
        }
    }
    // perform a depth first or breath first search and see if you hit a node - O([V| + |E|)
    static boolean reachable(Graph graph, Node src, Node dest){
        if(src == dest) {return true;}
        Node[] nodes = graph.nodes;
        for(Node node : nodes){
            if(node != null){ node.isVisited = false; }
        }
        Queue<Node> queue = new LinkedList<Node>();
        src.isVisited = true;
        queue.add(src);
        while(!queue.isEmpty()){
            Node tmp = queue.remove();
            for(Edge edge : tmp.edgeList){  // go through all neighbor nodes
                Node node = edge.to;
                if(node == dest) return true;  // reach to the dest node, return true
                if(!node.isVisited){  // add unvisited neighbor nodes to the queue
                    node.isVisited = true;
                    queue.add(node);
                }
            }
        }
        return false;
    }
    // 1 2 3 4 5 6 <- 02-route-between-two-nodes Node.data
    // A B C D E F
    // 0 1 2 3 4 5 <- index
}
/*
output:
#Create graph:
edges[0]: A -> B; cost: 1
edges[1]: A -> E; cost: 2
edges[2]: B -> A; cost: 1
edges[3]: B -> E; cost: 2
edges[4]: C -> C; cost: 0
edges[5]: D -> E; cost: 1
edges[6]: D -> F; cost: 1
edges[7]: E -> A; cost: 2
edges[8]: E -> B; cost: 2
edges[9]: E -> D; cost: 1
edges[10]: F -> D; cost: 1
#Graph.nodes:
A(edges:A->B,A->E,);
B(edges:B->A,B->E,);
C(edges:C->C,);
D(edges:D->E,D->F,);
E(edges:E->A,E->B,E->D,);
F(edges:F->D,);
\0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0
is B -> C reachable: false
is A -> F reachable: true
 */
