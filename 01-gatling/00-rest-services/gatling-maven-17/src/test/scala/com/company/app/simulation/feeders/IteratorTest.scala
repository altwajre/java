package com.company.app.simulation.feeders

import com.company.app.feeder.EmailFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class IteratorTest extends Simulation {
  val feedEmails: ScenarioBuilder = scenario("Feed emails")
    .feed(EmailFeeder.feeder)
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
