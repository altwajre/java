# find-sum

maven-archetype-quickstart project

## Build package

> `mvn package` or `mvn clean install`

## Run the application

> `java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App`

```
Root: 5
Bfs: 5 2 7 1 3 6 8 4 9 
#findSum(root, 8)
5 foundSum: false
2 foundSum: false
  Print path: 5 2 1 
1 foundSum: true
3 foundSum: false
4 foundSum: false
7 foundSum: false
6 foundSum: false
  Print path: 8 
8 foundSum: true
9 foundSum: false
#findSum(root, 14)
5 foundSum: false
2 foundSum: false
1 foundSum: false
3 foundSum: false
  Print path: 5 2 3 4 
4 foundSum: true
7 foundSum: false
6 foundSum: false
8 foundSum: false
9 foundSum: false
```
