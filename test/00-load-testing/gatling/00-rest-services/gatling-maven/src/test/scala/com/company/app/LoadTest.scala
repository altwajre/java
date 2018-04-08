package com.company.app

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadTest extends Simulation {

  val port: String = "8080"
  println(port)

  val postResponseBody: ScenarioBuilder = scenario("Post get response body")
    .exec(http("Create whisky")
      .post(s"http://localhost:${port}/api/whiskies")
      .body(StringBody(s"""{"name": "${randomName()}", "origin": "Scotland"}""")).asJSON
      .check(status.is(201), bodyString.saveAs("body"))
    )
    .pause(1)
    .exec(session => {
      val body = session.get("body").asOption[String]
      println(body)
      session
    })

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

  val postGet: ScenarioBuilder = scenario("Post Get")
    .feed(UuidFeeder.feeder)
    .exec(http("Create whisky")
      .post(s"http://localhost:${port}/api/whiskies")
      .body(StringBody(s"""{"name": "$${uuid}", "origin": "Scotland"}""")).asJSON
      //      .body(StringBody(s"""{"name": """" + "${uuid}" + """", "origin": "Scotland"}""")).asJSON
      //      .body(StringBody(s"""{"name": "${randomName()}", "origin": "Scotland"}""")).asJSON
      //      .body(StringBody(ResourceHelper.get("data/create.json").toString)).asJSON
      .check(
      status.is(201),
      jsonPath("$..id").find.saveAs("id"),
      bodyString.saveAs("body"))
    )
    .pause(1)
    .exec(http("Get whisky")
      .get(s"http://localhost:${port}/api/whiskies" + "/${id}")
      .check(status.is(200))
    )
    .exec(session => {
      val id = session.get("id").asOption[String]
      println(id)
      val body = session.get("body").asOption[String]
      println(body)
      session
    })

  val getAll: ScenarioBuilder = scenario("Get All")
    .exec(http("Get whiskies")
      .get("http://localhost:8080/api/whiskies")
      .extraInfoExtractor(extraInfo =>
        List(
          extraInfo.requestName,
          s"\nResponse Body:\r\n${extraInfo.response.body.string}"
        )
      )
    )

  setUp(
    //    postResponseBody.inject(atOnceUsers(1))
    //    postGetAll.inject(atOnceUsers(1))
//    postGet.inject(atOnceUsers(2))
          postGet.inject(constantUsersPerSec(50) during( 10 seconds))
    //    getAll.inject(atOnceUsers(1))
    //        post.inject(constantUsersPerSec(50) during( 10 seconds)),
    //        getAll.inject(constantUsersPerSec(50) during( 10 seconds))
  )

  private def randomName(): String = {
    UUID.randomUUID().toString
  }
}
