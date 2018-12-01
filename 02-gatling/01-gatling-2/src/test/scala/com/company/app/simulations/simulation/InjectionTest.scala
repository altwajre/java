package com.company.app.simulations.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class InjectionTest extends Simulation {

  val nothingForScenario = scenario("nothingFor")
    .exec(http("get one post")
      .get("https://jsonplaceholder.typicode.com/posts/1")
      .check(
        status is 200,
        bodyString.saveAs("getPostResponseBody")
      )
    )
    .exec(session => {
      val getPostResponseBody = session.get("getPostResponseBody").asOption[String]
      println(getPostResponseBody.get)
      session
    })
    .inject(
      nothingFor(2 seconds),
      atOnceUsers(2)
    )

  val rampUsersScenario = scenario("rampUsers")
    .exec(http("get one post")
      .get("https://jsonplaceholder.typicode.com/posts/1")
      .check(
        status is 200,
        bodyString.saveAs("getPostResponseBody")
      )
    )
    .exec(session => {
      val getPostResponseBody = session.get("getPostResponseBody").asOption[String]
      println(getPostResponseBody.get)
      session
    })
    .inject(
      // Injects a given number of users with a linear ramp over a given duration
      rampUsers(10) over (5 seconds)
    )

  val constantUsersPerSecScenario = scenario("constantUsersPerSec")
    .exec(http("get one post")
      .get("https://jsonplaceholder.typicode.com/posts/1")
      .check(
        status is 200,
        bodyString.saveAs("getPostResponseBody")
      )
    )
    .exec(session => {
      val getPostResponseBody = session.get("getPostResponseBody").asOption[String]
      println(getPostResponseBody.get)
      session
    })
    .inject(
      constantUsersPerSec(3) during(3 seconds)
    )

  val constantUsersPerSecRandomScenario = scenario("constantUsersPerSec randomized")
    .exec(http("get one post")
      .get("https://jsonplaceholder.typicode.com/posts/1")
      .check(
        status is 200,
        bodyString.saveAs("getPostResponseBody")
      )
    )
    .exec(session => {
      val getPostResponseBody = session.get("getPostResponseBody").asOption[String]
      println(getPostResponseBody.get)
      session
    })
    .inject(
      constantUsersPerSec(3) during(3 seconds) randomized
    )

  val rampUsersPerSecScenario = scenario("rampUsersPerSec")
    .exec(http("get one post")
      .get("https://jsonplaceholder.typicode.com/posts/1")
      .check(
        status is 200,
        bodyString.saveAs("getPostResponseBody")
      )
    )
    .exec(session => {
      val getPostResponseBody = session.get("getPostResponseBody").asOption[String]
      println(getPostResponseBody.get)
      session
    })
    .inject(
      rampUsersPerSec(2) to 8 during(8 seconds)
    )

  setUp(
    nothingForScenario,
    rampUsersScenario,
    constantUsersPerSecScenario,
    constantUsersPerSecRandomScenario,
    rampUsersPerSecScenario
  )

}
