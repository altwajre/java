package com.company.app.simulation.session

import com.company.app.feeder.CustomerFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class ChangeFeederSessionAttributeTest extends Simulation {
  val feedCustomers: ScenarioBuilder = scenario("Change Session attributes")
    .feed(CustomerFeeder.feeder) // 'Feeder is now empty' exception may occur when
    .exec(session => {
      println("# session_1")
      val name = session.get("name").asOption[String]
      val age = session.get("age").asOption[String]
      println(name.get + " is " + age.get)

      // if name == "Tom" return newSession else session
      if(name.get == "Tom"){
        println("# It is Tom")
        session.set("name", "Tommy").set("newName", "New Name")
      }
      else {
        println("# It is not Tom")
        session.set("newName", "New Name")
      }

    })
    .exec(session => {
      println("# session_2")
      val name = session.get("name").asOption[String]
      val age = session.get("age").asOption[String]

      println(name.get + " is " + age.get)

      val newName = session.get("newName").asOption[String]
      println(newName)

      session
    })

  setUp(
    feedCustomers.inject(
      atOnceUsers(5)
    )
  )

}
