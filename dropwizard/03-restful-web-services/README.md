# restful-web-services

maven-archetype-quickstart project

## Restful service

> https://dropwizard.github.io/dropwizard/index.html

### Compile

> `mvn package` or `mvn clean install`

### Run the application

> `java -jar target/hello-world-0.0.1-SNAPSHOT.jar server hello-world.yml`

### Intellij

#### Configuration - program arguments

> On configurations dialog, `Program arguments`, enter `server hello-world.yml`, click `OK`

### Visit

> http://localhost:8080/hello-world

## Restful client

### Run the application

#### Intellij IDE

> Use Intellij IDE to open 02-restful-client/pom.xml to open the project

> Run, Run..., Select App

> Result `{"id":1,"content":"Hello yml, Stranger yml!"}`

## Restful testng

### Run the testng tests

#### Intellij IDE

> Use Intellij IDE to open 03-restful-testng/pom.xml to open the project

> Right click `TestNgTest` class, and click `Run 'test()'``
