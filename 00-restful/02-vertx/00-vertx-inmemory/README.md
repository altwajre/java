# Vertx Server

http://vertx.io/blog/unit-and-integration-tests/

mvn clean package -DskipTests -DskipITs
java -jar ./target/vertx-inmemory-1.0-SNAPSHOT-fat.jar

- GET all
curl http://localhost:8080/api/whiskies

- GET one
curl http://localhost:8080/api/whiskies/1

- POST
curl -X POST http://localhost:8080/api/whiskies -d '{"name": "Bowmore 18 Years", "origin": "Scotland"}'

- UPDATE
curl -X PUT http://localhost:8080/api/whiskies/1 -d '{"name": "Bowmore 18", "origin": "Scotland"}'

- DELETE
curl -X DELETE http://localhost:8080/api/whiskies/1
