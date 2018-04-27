package com.company.app.simulation.scenarios

import com.company.app.feeder.ArrayFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, status}

class ForeverScenario extends Simulation {
  val lambdaUser = scenario("Standard User")
    .exec(session => {
      println("# forever")
      session
    })
    .forever {
      feed(ArrayFeeder.feeder.circular)
        .exec(http("get whiskies")
          .get("http://localhost:8080/api/whiskies")
          .check(
            status is 200
          ))
    }

  setUp(
    lambdaUser.inject(atOnceUsers(2))
  )

}
