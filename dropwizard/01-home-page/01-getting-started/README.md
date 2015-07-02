# getting-started

maven-archetype-quickstart project

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/app-1.0-SNAPSHOT.jar server hello-world.yml`

## Visit

> `http://localhost:8080/hello-world`

```
{
  id: 1,
  content: "Hello, Stranger yml!"
}
```

> `http://localhost:8080/hello-world?name=Tom`

```
{
  id: 2,
  content: "Hello, Tom!"
}
```