# Gatling Maven

https://www.safaribooksonline.com/library/view/building-microservices-with/9781788292658/video2_1.html

- Load Test

create `scala` under `test` and mark it as `Test Sources Root`

LoadTest.scala

```
class LoadTest extends Simulation{
  val post: ScenarioBuilder = scenario("Post")
    .exec(http("Create whisky")
      .post("http://localhost:8080/api/whiskies")
      .body(StringBody(s"""{"name": "${randomName()}", "origin": "Scotland"}""")).asJSON
    )
  val get: ScenarioBuilder = scenario("Get All")
    .exec(http("Get whiskies")
      .get("http://localhost:8080/api/whiskies"))
  setUp(
    post.inject(constantUsersPerSec(50) during( 10 seconds)),
    //    get.inject(atOnceUsers(1))
    get.inject(constantUsersPerSec(50) during( 10 seconds))
  )
  private def randomName(): String = {
    UUID.randomUUID().toString
  }
}
```

## Steps

1. Launch vertx-server
2. Run `mvn clean gatling:execute`
