package com.company.app.simulations

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class PostGetAllTest extends Simulation {

  val port: String = "8080"
  println(port)

  val postGetAll: ScenarioBuilder = scenario("Post Get All")
    .exec(http("Create whisky")
      .post(s"http://localhost:${port}/api/whiskies")
      .body(StringBody(s"""{"name": "${randomName()}", "origin": "Scotland"}""")).asJSON
      .check(
        status.is(201),
        jsonPath("$..id").find.saveAs("id"),
        bodyString.saveAs("postResponseBody")
      )
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
    .exec(http("Get all whiskies")
      .get(s"http://localhost:${port}/api/whiskies")
      .check(
        status.is(200),
        bodyString.saveAs("getResponseBody")
      )
    )
    .exec(session => {
      println("# Get all whiskies session")
      val id = session.get("id").asOption[String]
      println(id.get)
      val getResponseBody = session.get("getResponseBody").asOption[String]
      println(getResponseBody)
      session
    })

  setUp(
    postGetAll.inject(atOnceUsers(1))
    //    postGetAll.inject(constantUsersPerSec(50) during (10 seconds))
  )

  private def randomName(): String = {
    UUID.randomUUID().toString
  }
}
