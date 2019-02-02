package simulations.injection

import io.gatling.core.Predef.{rampUsersPerSec, _}
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.io.Source

class InjectionTest extends Simulation {
  val whiskyTest: ScenarioBuilder = scenario("Create Get")
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
    .exec(http("Suspend")
      .post(s"http://localhost:8080/api/whiskies" + "/${id}" + "/suspend")
      .body(StringBody("")).asJson
      .check(
        status.is(200),
        bodyString.saveAs("suspendResponseBody")
      )
    )
    .exec(session => {
      println("# Suspend session")
      val id = session("id").asOption[String]
      println(id.get)
      val suspendResponseBody = session("suspendResponseBody").asOption[String]
      println(suspendResponseBody)
      session
    })

  setUp(
    // Injects a given number of users with a linear ramp over a given duration.
    whiskyTest.inject(
      //      rampUsers(10) during(5 seconds)
      rampUsersPerSec(1) to 3 during (5 seconds) // total users 45
//        constantUsersPerSec(1) during (20 seconds)
//       atOnceUsers(1)
    )
  )

}
