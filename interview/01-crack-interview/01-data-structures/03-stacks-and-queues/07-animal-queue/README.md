# animal-queue

maven-archetype-quickstart project

## Build package

> `mvn package` or `mvn clean install`

## Run the application

> `java -cp target/app-1.0-SNAPSHOT.jar com.company.app.App`

```
1 cat1; 4 cat2; 0 dog1; 2 dog2; 3 dog3; 
dequeueAny
1 cat1; 4 cat2; 2 dog2; 3 dog3; 
dequeueDogs
1 cat1; 4 cat2; 3 dog3; 
dequeueCats
4 cat2; 3 dog3;
```
