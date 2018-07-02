package com.company.app.simulations.feeders

import com.company.app.feeder.JsonFileFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, status}

class JsonTest extends Simulation {
  val loadFromJsonFile = scenario("Load from jsonFile")
    .feed(JsonFileFeeder.feeder)
    .exec(http("get whiskies")
      .get("http://localhost:8080/api/whiskies")
      .check(
        status is 200
      ))
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

  setUp(
    loadFromJsonFile
  )

}

