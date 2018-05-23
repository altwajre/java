package com.company.app.simulations.feeders

import com.company.app.feeder.ArrayFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{http, status}

// Data driven
// https://gatling.io/docs/current/session/feeder/
class ArrayMapHasUuidTest extends Simulation {
  val feedCustomers: ScenarioBuilder = scenario("Feed customers")
    // feeder.queue: exception occurs when Feeder is empty
    //    .feed(CustomerFeeder.feeder.queue) // default behavior: use an Iterator on the underlying sequence
    //    .feed(CustomerFeeder.feeder.random) // randomly pick an entry in the sequence
    .feed(ArrayFeeder.feeder.circular) // go back to the top of the sequence once the end if reached
    .exec(http("get whiskies")
    .get("http://localhost:8080/api/whiskies")
    .check(
      status is 200
    ))
    .exec(session => {
      val uuid = session.get("uuid").asOption[Iterator[Map[String, String]]]
      val name = session.get("name").asOption[Iterator[Map[String, String]]]
      val age = session.get("age").asOption[String]

      println(uuid.get.next().get("uuid").get + " - " + name.get.next().get("uuid").get + " is " + age.get)

      session
    })

  setUp(
    feedCustomers.inject(
      atOnceUsers(5)
    )
  )

}
