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
        bodyString.saveAs("createWhiskyResponse"))
    )
    .pause(1)
    .exec(http("Get whisky")
      .get(s"http://localhost:${port}/api/whiskies")
      .check(
        status.is(200),
        bodyString.saveAs("getWhiskiesResponse")
      )
    )
    .exec(session => {
      val id = session.get("id").asOption[String]
      println(id)

      val createWhiskyResponse = session.get("createWhiskyResponse").asOption[String]
      println(createWhiskyResponse)

      val getWhiskiesResponse = session.get("getWhiskiesResponse").asOption[String]
      println(getWhiskiesResponse)
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
