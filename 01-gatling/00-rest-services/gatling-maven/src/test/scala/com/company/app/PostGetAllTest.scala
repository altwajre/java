package com.company.app

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

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
        bodyString.saveAs("body"))
    )
    .pause(1)
    .exec(http("Get whisky")
      .get(s"http://localhost:${port}/api/whiskies")
      .check(status.is(200))
    )
    .exec(session => {
      val id = session.get("id").asOption[String]
      println(id)
      val body = session.get("body").asOption[String]
      println(body)
      session
    })

  setUp(
    //    postGetAll.inject(atOnceUsers(1))
    postGetAll.inject(constantUsersPerSec(5) during (2 seconds))
  )

  private def randomName(): String = {
    UUID.randomUUID().toString
  }
}
