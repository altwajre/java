package com.company.app.simulations.feeders

import com.company.app.feeder.EmailFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{http, status}

class IteratorTest extends Simulation {
  val feedEmails: ScenarioBuilder = scenario("Feed emails")
    .feed(EmailFeeder.feeder)
    .exec(http("get whiskies")
      .get("http://localhost:8080/api/whiskies")
      .check(
        status is 200
      ))
    .exec(session => {
      val email = session.get("email").asOption[String]
      println(email)

      session
    })

  setUp(
    feedEmails.inject(
      atOnceUsers(3)
    )
  )

}
