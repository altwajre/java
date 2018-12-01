package com.company.app.simulations.session

import com.company.app.feeder.ArrayFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{http, status}

class ChangeFeederSessionAttributeTest extends Simulation {
  val feedCustomers: ScenarioBuilder = scenario("Change Session attributes")
    .feed(ArrayFeeder.feeder.circular) // 'Feeder is now empty' exception may occur
    .exec(http("get whiskies")
    .get("http://localhost:8080/api/whiskies")
    .check(
      status is 200
    ))
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
