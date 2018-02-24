# JsonNode vs JsonObject

> JsonNode works by default

it works

> JsonObject Issues

```
    String response = given()
        .contentType(ContentType.JSON)
        .body(whisky.toString())
//        .body(whisky) // ISSUE: does not work due to passing map inside of json body to server
        .when()
        .post("/api/whiskies")
        .asString();
//        .as(JsonObject.class); // ISSUE: does not work due to no deserializer for JsonObject by default
```
