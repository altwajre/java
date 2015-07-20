# graph

maven-archetype-quickstart project

## Build package

> `mvn package` or `mvn clean install`

## Run the application

> `java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App`

```
#digraph g {
	A -> B [ cost=3 ]
	X -> Y [ cost=8 ]
}
#Graph.nodes: A B \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 \0 X Y \0 
#Non-null Nodes:
Node A[0]
	A -> B [ cost=3 ]
Node B[1]
\n
Node X[23]
	X -> Y [ cost=8 ]
Node Y[24]
```
