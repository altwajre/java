package com.company.app.simulation.scenarios

import com.company.app.feeder.ArrayFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, status}

class doIfScenario extends Simulation {
  var likeCoke = true

  val lambdaUser = scenario("Standard User")
    .exec(session => {
      println("# doIf")
      session
    })
    .doIf(s"${likeCoke}".toBoolean) {
      feed(ArrayFeeder.feeder.circular)
        .exec(http("get whiskies")
          .get("http://localhost:8080/api/whiskies")
          .check(
            status is 200
          ))
        .exec(session => {
          println(likeCoke)
          likeCoke = false
          session
        })
    }

  setUp(
    lambdaUser.inject(atOnceUsers(2))
  )

}
