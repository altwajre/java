package com.company.app

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class GoogleSimulation extends Simulation {
  val test = scenario("Google scenario")
    .exec(http("Get Google")
      .get("https://www.google.com")
      .check(
        status.is(200),
        bodyString.saveAs("getBody"),
        currentLocation.saveAs("url")
      ))
    .exec(session => {
      val getBody = session.get("getBody").asOption[String]
      println("getBody: " + getBody)
      val url = session.get("url").asOption[String]
      println("url: " + url)
      session
    })

  setUp(
    test.inject(atOnceUsers(2))
    //    test.inject(constantUsersPerSec(10) during (2 seconds))
  )

}
