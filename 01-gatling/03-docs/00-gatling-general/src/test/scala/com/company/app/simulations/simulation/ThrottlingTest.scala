package com.company.app.simulations.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class ThrottlingTest extends Simulation {

  val throttlingScenario = scenario("rampUsers")
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
      .throttle(
        // will reach 8 req/s with a ramp of 2 seconds
        reachRps(8) in (2 seconds),
        holdFor(4 seconds),
        // jump to 4 req/s
        jumpToRps(4),
        holdFor(8 seconds)
      )

  setUp(
    throttlingScenario
  )

}
