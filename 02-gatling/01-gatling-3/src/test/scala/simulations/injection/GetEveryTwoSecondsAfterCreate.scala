package simulations.injection

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.io.Source

class GetEveryTwoSecondsAfterCreate extends Simulation {
  val lambdaUser = scenario("Standard User")
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
    .exec(session => {
      println("# run every 2 seconds")
      session
    })
    .repeat(3) {
      pace(3 seconds)
        .exec(http("get whisky")
          .get(s"http://localhost:8080/api/whiskies" + "/${id}")
          .check(
            status is 200
          ))
    }

  setUp(
    lambdaUser.inject(atOnceUsers(2))
  )

}
