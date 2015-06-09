# rest-api - dwbook-phonebook and testng

maven-archetype-quickstart project

## Start mysql server

> `mysql.server start`

> `mysql.server stop` when you are done

## Server

> `cd 01-server`

### Compile

> `mvn package` or `mvn clean install`

### Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## Client

### cURL HTTP requests

#### DELETE request

> `$ curl -X GET http://localhost:8080/contact/all`

> `$ curl -X DELETE http://localhost:8080/contact/3`

> `$ curl -X GET http://localhost:8080/contact/3`

### Browser - visit

> Open chrome, developer tool, Network

#### GET

> http://localhost:8080/client/showContact?id=1

```
ID: 1
First name: John
Last name: Doe
Phone: +123456789
```

## Test - TestNg

> `cd 02-testng`

### Run tests

> In intellij, right click on test and select run test

> `mvn package`
