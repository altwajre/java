# authentication - dwbook-phonebook

maven-archetype-quickstart project

## Start mysql server

> `mysql.server start`

> `mysql.server stop` when you are done

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## Client

### curl

> $ curl http://localhost:8080/contact/1

```
$ curl http://localhost:8080/contact/1
Credentials are required to access this resource.
```

### Visit

> http://localhost:8080/client/showContact?id=1

```
ID: 1
First name: John
Last name: Doe
Phone: +123456789
```


