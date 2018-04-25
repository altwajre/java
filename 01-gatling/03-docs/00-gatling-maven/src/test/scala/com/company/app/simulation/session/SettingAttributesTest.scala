package com.company.app.simulation.session

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class SettingAttributesTest extends Simulation {
  val sessionSet: ScenarioBuilder = scenario("Create new session for setting new attributes")
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
