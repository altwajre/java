# set-of-stacks

maven-archetype-quickstart project

## Build package

> `mvn package` or `mvn clean install`

## Run the application

> `java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App`

```
$ java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App
3->2->1->null
22->11->null
#pop 22
3->2->1->null
11->null
#pop 11
3->2->1->null
#pop 3
2->1->null
#pop 2
1->null
```
