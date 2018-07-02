package com.company.app.simulations.scenarios

import com.company.app.feeder.ArrayFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, status}

// https://gatling.io/docs/current/general/scenario/
class CoreSimulation extends Simulation {

  var likeCoke = true;

  val lambdaUser = scenario("Standard User")
    .exec(session => {
      println("# repeat")
      session
    })
    .repeat(2) {
      feed(ArrayFeeder.feeder.circular)
        .exec(http("get whiskies")
          .get("http://localhost:8080/api/whiskies")
          .check(
            status is 200
          ))
    }
    .exec(session => {
      println("# during")
      session
    })
    .during(2) {
      feed(ArrayFeeder.feeder.circular)
        .exec(http("get whiskies")
          .get("http://localhost:8080/api/whiskies")
          .check(
            status is 200
          ))
    }
    .exec(session => {
      println("# group")
      session
    })
    .group("group") {
      feed(ArrayFeeder.feeder.circular)
        .exec(http("get whiskies")
          .get("http://localhost:8080/api/whiskies")
          .check(
            status is 200
          ))
        .exec(session => {
          val name = session.get("name").asOption[String]
          println(name)
          session
        })
    }

  setUp(
    lambdaUser.inject(atOnceUsers(2))
  )

}
