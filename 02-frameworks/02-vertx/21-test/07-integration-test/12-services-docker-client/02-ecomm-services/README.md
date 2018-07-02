# product service

## Run All Tests

`mvn clean package`

### Offer consumes Product api test

> Launch product rest api

App.main()

> Run Offer rest api that uses product rest api

OfferVerticle

```
  private void getOne(RoutingContext context) {
    String id = context.request().getParam("id");

    if (id == null) {
      context.response().setStatusCode(400).end();
    } else {
      Integer idAsInteger = Integer.valueOf(id);
      Offer offer = offers.get(idAsInteger);
      if (offer == null) {
        context.response().setStatusCode(404).end();
      } else {

        Integer productPort = 8081;

        WebClient.create(vertx)
            .get(productPort, "localhost", "/api/products/1")
            .as(BodyCodec.json(Product.class))
            .send(ar -> {
              if (ar.succeeded()) {
                HttpResponse<Product> response = ar.result();

                Product product = response.body();
                System.out.println("Body:\n" + product.toString());
                offer.setProduct(product);

                // NOTE: put context.response() inside of send() callback
                context.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(offer));
              } else {
                System.out.println("Error=" + ar.cause());
              }
            });

      }
    }

  }
```

test

```
  @Test
  public void testGetOne(TestContext context) {
    Async async = context.async();

    WebClient.create(vertx)
        .get(port, "localhost", "/api/offers/1")
        .as(BodyCodec.json(Offer.class))
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Offer> response = ar.result();

            Offer body = response.body();
            System.out.println("Body:\n" + body.toString());

          } else {
            System.out.println("Error=" + ar.cause());
          }
          async.complete();
        });

  }
```

> jar

Use `maven-shade-plugin` to create fat jar

> config

`src/main/conf/conf.json`

```
{
  "http.port": 8082
}
```

> Compile

```
$ mvn clean package
```

> Run App with `-config`

```
$ java -jar target/app-config-1.0-SNAPSHOT.jar -conf src/main/conf/conf.json
```

curl http://localhost:8082/api/products

```
[ {
  "id" : 0,
  "name" : "iPhone"
}, {
  "id" : 1,
  "name" : "MacBook"
} ]
```

> Run Test - `mvn clean test`

`ProductVerticleTest`

```
Running com.company.app.ProductVerticleTest
8081
[ {
  "id" : 0,
  "name" : "iPhone"
}, {
  "id" : 1,
  "name" : "MacBook"
} ]
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.124 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

## Docker - manual steps

> build image

```
$ docker build -t local-product .
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED              SIZE
local-product      latest              26ea0a1d2895        About a minute ago   319MB
java                8-jre               e44d62cf8862        6 months ago         311MB
```

> run container

```
                host:container
$ docker run -p 8081:8080 -d local-product
5295f1b99e80582e130ce3622dde7919d2cdbc213af8c52f5f150c85141e8210
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
5295f1b99e80        local-product      "sh -c 'exec java ..."   41 seconds ago      Up 42 seconds       0.0.0.0:8081->8080/tcp   zealous_galileo
```

> test

curl http://localhost:8081/api/products

```
[
{
id: 0,
name: "iPhone"
},
{
id: 1,
name: "MacBook"
}
]
```

> cleanup

```
docker rm -f $(docker ps -a -q)
docker rmi -f $(docker images -q)
```
