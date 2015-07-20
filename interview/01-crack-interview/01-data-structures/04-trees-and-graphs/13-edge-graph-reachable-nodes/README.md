# edge-graph-reachable-nodes

maven-archetype-quickstart project

## Build package

> `mvn package` or `mvn clean install`

## Run the application

> `java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App`

```
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
```
