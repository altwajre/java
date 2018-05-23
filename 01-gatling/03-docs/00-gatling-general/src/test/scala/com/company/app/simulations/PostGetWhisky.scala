package com.company.app.simulations

import java.util.UUID

import com.company.app.feeder.UuidFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class PostGetWhisky extends Simulation {

  val port: String = "8080"
  println(port)

  val postGet: ScenarioBuilder = scenario("Post Get")
    .feed(UuidFeeder.feeder)
    .exec(http("Create whisky")
      .post(s"http://localhost:${port}/api/whiskies")
      // $${uuid} is from UuidFeeder.feeder
//      .body(StringBody(s"""{"name": "$${uuid}", "origin": "Scotland"}""")).asJSON
      .body(StringBody(s"""{"name": "$${uuid}", "origin": "$${originUuid}"}""")).asJSON
      // DO NOT do following because it is not random UUID
//      .body(StringBody(s"""{"name": "${UUID.randomUUID.toString}", "origin": "${UUID.randomUUID.toString}"}""")).asJSON
      .check(
        status.is(201),
        jsonPath("$..id").find.saveAs("id"),
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
      .get(s"http://localhost:${port}/api/whiskies" + "/${id}")
      .check(
        status.is(200),
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

  setUp(
    postGet.inject(atOnceUsers(2))
//      postGet.inject(constantUsersPerSec(50) during( 10 seconds))
  )

}
