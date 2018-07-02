package com.company.app.simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class GetAllTest extends Simulation {
  val getAll: ScenarioBuilder = scenario("Get All")
    .exec(http("Get whiskies")
      .get("http://localhost:8080/api/whiskies")
      .check(
        status is 200,
        bodyString.saveAs("getResponseBody")
      )
    )
    .exec(session => {
      println("# Get all whiskies session")
      val getResponseBody = session.get("getResponseBody").asOption[String]
      println(getResponseBody)
      session
    })

  setUp(
    getAll.inject(atOnceUsers(1))
    //      getAll.inject(constantUsersPerSec(50) during( 10 seconds))
  )

}
