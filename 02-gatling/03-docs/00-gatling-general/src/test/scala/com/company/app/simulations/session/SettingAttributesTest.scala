package com.company.app.simulations.session

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{http, status}

class SettingAttributesTest extends Simulation {
  val sessionSet: ScenarioBuilder = scenario("Create new session for setting new attributes")
    .exec(http("get whiskies")
      .get("http://localhost:8080/api/whiskies")
      .check(
        status is 200
      ))
    .exec(session => {
      session.set("name", "Tom").set("age", 18)
    })
    .exec(session => {
      val name = session.get("name").asOption[String]
      val age = session.get("age").asOption[String]

      println(name.get + " is " + age.get)
      val newSession = session.set("name", "Harry").set("age", 28) // create new session and change session attributes
      println(newSession)
      newSession // return new session
    })
    .exec(session => {
      val name = session.get("name").asOption[String]
      val age = session.get("age").asOption[String]

      println(name.get + " is " + age.get)
      session
    })

  setUp(
    sessionSet.inject(
      atOnceUsers(1)
    )
  )

}
