package com.company.app

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadTest extends Simulation{

  val post: ScenarioBuilder = scenario("Post")
    .exec(http("Create whisky")
      .post("http://localhost:8080/api/whiskies")
      .body(StringBody(s"""{"name": "${randomName()}", "origin": "Scotland"}""")).asJSON
    )

  val getAll: ScenarioBuilder = scenario("Get All")
    .exec(http("Get whiskies")
      .get("http://localhost:8080/api/whiskies"))

  setUp(
    post.inject(constantUsersPerSec(50) during( 10 seconds)),
    //    get.inject(atOnceUsers(1))
    getAll.inject(constantUsersPerSec(50) during( 10 seconds))
  )

  private def randomName(): String = {
    UUID.randomUUID().toString
  }
}
