package com.company.app.simulation.feeders

import com.company.app.feeder.{JsonFileFeeder, JsonUrlFeeder}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class JsonTest extends Simulation {
  val loadFromJsonFile = scenario("Load from jsonFile")
    .feed(JsonFileFeeder.feeder)
    .exec(session => {
      println("# loadFromJsonFile")
      val name = session.get("name").asOption[String]
      val age = session.get("age").asOption[String]

      println(name.get + " is " + age.get)

      session
    })
    .inject(
      atOnceUsers(3)
    )
  val loadFromJsonUrl = scenario("Load from jsonUrl")
    .feed(JsonUrlFeeder.feeder)
    .exec(session => {
      println("# loadFromJsonUrl")
      val name = session.get("name").asOption[String]
      val age = session.get("age").asOption[String]

      println(name.get + " is " + age.get)

      session
    })
    .inject(
      atOnceUsers(2)
    )

  setUp(
    loadFromJsonFile,
    loadFromJsonUrl
  )

}

