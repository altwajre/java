package com.company.app.simulation.checks

import com.company.app.feeder.UuidFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class CheckPostGetTest extends Simulation {
  val port: String = "8080"
  println(port)

  private val postUrl = s"http://localhost:${port}/api/whiskies"
  private val getUrl: String = s"http://localhost:${port}/api/whiskies" + "/${id}"
  val postGet = scenario("Post Get")
    .feed(UuidFeeder.feeder)
    .exec(http("Create whisky")
      .post(postUrl)
      // $${uuid} is from UuidFeeder.feeder
      .body(StringBody(s"""{"name": "$${uuid}", "origin": "Scotland"}""")).asJSON
      .check(
        status.is(201),
        status not 404,
        status not 500,
        currentLocation is postUrl,
        responseTimeInMillis.lessThan(100L),
        jsonPath("$.id").find.saveAs("id"),
//        jsonPath("$..id").find.saveAs("id"),
        bodyString.saveAs("postResponseBody"))
    )
    .exec(session => {
      println("# Create whisky session")

      val id = session.get("id").asOption[String]
      println(id.get)
      val postResponseBody = session.get("postResponseBody").asOption[String]
      println(postResponseBody)
      session
    })
    .pause(1)
    .exec(http("Get whisky")
      .get(getUrl)
      .check(
        status.is(200),
        currentLocation is getUrl,
        currentLocationRegex(getUrl).ofType[String],
        bodyString.saveAs("getResponseBody")
      )
    )
    .exec(session => {
      println("# Get whisky session")
      val id = session.get("id").asOption[String]
      println(id.get)
      val getResponseBody = session.get("getResponseBody").asOption[String]
      println(getResponseBody)
      session
    })
    .inject(atOnceUsers(1))
//    .inject(constantUsersPerSec(50) during (10 seconds))

  setUp(
    postGet
  )

}
