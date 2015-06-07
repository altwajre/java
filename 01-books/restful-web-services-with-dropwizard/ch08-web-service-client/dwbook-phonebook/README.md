# web-service-client - dwbook-phonebook

maven-archetype-quickstart project

## Client side code

> /dwbook-phonebook/src/main/java/com/dwbook/phonebook/resources/ClientResource.java

> /dwbook-phonebook/src/main/java/com/dwbook/phonebook/App.java

```
  // build the client and add the resource to the environment
  final Client client = ClientBuilder.newClient();
  e.jersey().register(new ClientResource(client));
```

## Start mysql server

> `mysql.server start`

> `mysql.server stop` when you are done

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## Visit

> Open chrome, developer tool, Network

### POST

> http://localhost:8080/client/newContact?firstName=Jane&lastName=Doe&phone=98765432

```
Remote Address:[::1]:8080
Request URL:http://localhost:8080/client/newContact?firstName=Jane&lastName=Doe&phone=98765432
Request Method:GET
Status Code:201 Created
```

### PUT

> http://localhost:8080/client/updateContact?id=3&firstName=Alex&lastName=Updated&phone=3210465

{
id: 3,
firstName: "Alex",
lastName: "Updated",
phone: "3210465"
}

### DELETE

> http://localhost:8080/client/deleteContact?id=3

```
Remote Address:[::1]:8080
Request URL:http://localhost:8080/client/deleteContact?id=8
Request Method:GET
Status Code:204 No Content
```
