# Gatling Maven

https://www.safaribooksonline.com/library/view/building-microservices-with/9781788292658/video2_1.html


## Load Test

mvn clean gatling:execute

/test/scala/

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

## feeders

https://www.rubix.nl/blogs/basic-gatling-load-script-feeders

> Random uuid each scenario

- UuidFeeder.scala

```
object UuidFeeder {
  val feeder = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString()))
}
```

- LoadTest.scala

```
val postGet: ScenarioBuilder = scenario("Post Get")
.feed(UuidFeeder.feeder)  <- add feeder
.exec(http("Create whisky")
  .post(s"http://localhost:${port}/api/whiskies")
  .body(StringBody(s"""{"name": "$${uuid}", "origin": "Scotland"}""")).asJSON <- use uuid
```

## Steps

- server
Launch vertx-server

- test
mvn clean gatling:execute

> run a specific test

specific the class at `gatling-maven-plugin` `simulationClass`

```
  <plugin>
    <groupId>io.gatling</groupId>
    <artifactId>gatling-maven-plugin</artifactId>
    <version>${gatling-plugin.version}</version>
    <executions>
      <execution>
        <id>getUsers</id>
        <goals>
          <goal>execute</goal>
        </goals>
        <configuration>
          <simulationClass>com.company.app.LoadTest</simulationClass>
        </configuration>
      </execution>
    </executions>
  </plugin>
```
