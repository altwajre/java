package simulations.injection

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.io.Source

class InjectionTest extends Simulation {
  val getAll: ScenarioBuilder = scenario("Get All")
    .exec(http("Create whisky")
    .post("http://localhost:8080/api/whiskies")
      .body(StringBody(Source.fromResource("data/mywhisky.json").mkString))
      .check(
        status.is(201),
        jsonPath("$..id").find.saveAs("id"),
        bodyString.saveAs("postResponseBody"))
      .check(currentLocation.saveAs("url"))
    )
    .exec(session => {
      val url = session("url").asOption[String]
      println("url: " + url)
      val source = Source.fromResource("data/mywhisky.json")
      val myBody = source.mkString
      println(myBody)
      println("# Create whisky session")
      val id = session("id").asOption[String]
      println(id.get)
      val postResponseBody = session("postResponseBody").asOption[String]
      println(postResponseBody)
      session
    })
    .pause(1)
    .exec(http("Get whisky")
      .get(s"http://localhost:8080/api/whiskies" + "/${id}")
      .check(
        status.is(200),
        bodyString.saveAs("getResponseBody")
      )
    )
    .exec(session => {
      println("# Get whisky session")
      val id = session("id").asOption[String]
      println(id.get)
      val getResponseBody = session("getResponseBody").asOption[String]
      println(getResponseBody)
      session
    })

  setUp(
    // Injects a given number of users with a linear ramp over a given duration.
    getAll.inject(rampUsers(10) during(5 seconds))
  )

}
