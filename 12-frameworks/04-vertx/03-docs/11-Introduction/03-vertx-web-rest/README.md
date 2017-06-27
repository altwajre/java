# Rest with Vert.x

http://vertx.io/blog/some-rest-with-vert-x/

> pom.xml

add following dependency

```
<dependency>
  <groupId>io.vertx</groupId>
  <artifactId>vertx-web</artifactId>
  <version>3.0.0</version>
</dependency>
```

## Router

The router is the cornerstone of Vert.x Web. This object is responsible for dispatching the HTTP requests to the right handler.

- Routes - which let you define how request are dispatched
- Handlers - which are the actual action processing the requests and writing the result. Handlers can be chained.

## Exposing static resources

```
 // Serve static resources from the /assets directory
 router.route("/assets/*").handler(StaticHandler.create("assets"));
```

It routes requests on "/assets/*" to resources stored in the "assets" directory.
So our `index.htm` page is going to be served using `http://localhost:8080/assets/index.html`

> Compile and Run

```
$ mvn clean package

$ java -jar target/vertx-web-rest-1.0-SNAPSHOT.jar
```

http://localhost:8080/assets/index.html

## REST API

### Get products

> add handler for `GET all`, and add callback

```
router.get("/api/whiskies").handler(this::getAll);

private void getAll(RoutingContext routingContext) {
    routingContext.response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(products.values()));
}
```

> Compile and Run

```
$ mvn clean package

$ java -jar target/vertx-web-rest-1.0-SNAPSHOT.jar
```

http://localhost:8080/assets/index.html

http://localhost:8080/api/whiskies

> CURL - GET all

```
$ curl http://localhost:8080/api/whiskies
```

### Get a product

> add handler for `GET`, and add callback

```
router.get("/api/whiskies/:id").handler(this::getOne);

private void getOne(RoutingContext routingContext) {

    final String id = routingContext.request().getParam("id");
    if(id == null) {
      routingContext.response().setStatusCode(400).end();
    }
    else {
      final Integer idAsInteger = Integer.valueOf(id);
      Whisky whisky = products.get(idAsInteger);
      if(whisky == null) {
        routingContext.response().setStatusCode(404).end();
      }
      else {
        routingContext.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(whisky));
      }
    }
}
```

> Compile and Run

```
$ mvn clean package

$ java -jar target/vertx-web-rest-1.0-SNAPSHOT.jar
```

> CURL - GET a product

```
```

### Create a product

> add handlers for `POST`, and add callback

```
// First router enables the reading of the request body for all routes under "/api/whiskies".
router.route("/api/whiskies*").handler(BodyHandler.create());
router.route("/api/whiskies").handler(this::addOne);

private void addOne(RoutingContext routingContext) {
    // read the request's content and create an instance of Whisky.
    final Whisky whisky = Json.decodeValue(routingContext.getBodyAsString(), Whisky.class);
    // Add it to the backend map
    products.put(whisky.getId(), whisky);
    
    // Return the created whisky as JSON
    routingContext.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(whisky));
}
```

> Compile and Run

```
$ mvn clean package

$ java -jar target/vertx-web-rest-1.0-SNAPSHOT.jar
```

> CURL - POST

```
$ curl -X POST http://localhost:8080/api/whiskies -H 'content-type: application/json' -d '{"name": "Vodka", "origin": "Russian"}'
```

### Put a product

> add handlers for `POST`, and add callback


```
router.put("/api/whiskies/:id").handler(this::updateOne);

private void updateOne(RoutingContext routingContext) {

    final String id = routingContext.request().getParam("id");
    JsonObject json = routingContext.getBodyAsJson();
    
    if(id == null || json == null) {
      routingContext.response().setStatusCode(400).end();
    }
    else {
      final Integer idAsInteger = Integer.valueOf(id);
      Whisky whisky = products.get(idAsInteger);
      if(whisky == null) {
        routingContext.response().setStatusCode(404).end();
      }
      else {
        whisky.setName(json.getString("name"));
        whisky.setOrigin(json.getString("origin"));
        routingContext.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(whisky));
      }
    }
}
```

> Compile and Run

```
$ mvn clean package

$ java -jar target/vertx-web-rest-1.0-SNAPSHOT.jar
```

> CURL - PUT

```
curl -X PUT http://localhost:8080/api/whiskies/1 -H "Content-Type: application/json" -d '{"name": "Brandy", "origin": "Dutch"}'
```

### Delete a product

> add handlers for `DELETE`, and add callback

```
router.delete("/api/whiskies/:id").handler(this::deleteOne);

private void deleteOne(RoutingContext routingContext) {

    String id = routingContext.request().getParam("id");
    if(id == null){
      routingContext.response().setStatusCode(400).end();
    }
    else {
      Integer idAsInteger = Integer.valueOf(id);
      products.remove(idAsInteger);
    }
    routingContext.response().setStatusCode(204).end();
}
```

> Compile and Run

```
$ mvn clean package

$ java -jar target/vertx-web-rest-1.0-SNAPSHOT.jar
```

> CURL - POST

```
$ curl -X DELETE http://localhost:8080/api/whiskies/2
```
