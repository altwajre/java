/*
Inner Classes

https://docs.scala-lang.org/tour/inner-classes.html

Inner classes are bound to the outer object.
 */
class Graph {
  class Node {
    var connectedNodes: List[Node] = List.empty
    def connectTo(node: Node) {
      if (connectedNodes.find(node.equals).isEmpty) {
        connectedNodes = node :: connectedNodes
      }
    }
  }
  var nodes: List[Node] = List.empty
  def newNode: Node = {
    val res = new Node
    nodes = res :: nodes
    res
  }
}

object InnerClasses {
  def main(args: Array[String]): Unit = {
    val graph: Graph = new Graph
    val node1: graph.Node = graph.newNode
    val node2: graph.Node = graph.newNode
    val node3: graph.Node = graph.newNode

    node1.connectTo(node2)
    node3.connectTo(node1)

    println(graph.nodes)
  }
}
/*
List(Graph$Node@2f7c7260, Graph$Node@6b71769e, Graph$Node@2752f6e2)
 */
