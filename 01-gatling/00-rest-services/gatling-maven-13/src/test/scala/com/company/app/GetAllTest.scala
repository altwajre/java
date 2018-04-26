package com.company.app

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class GetAllTest extends Simulation {
  val getAll: ScenarioBuilder = scenario("Get All")
    .exec(http("Get whiskies")
      .get("http://localhost:8080/api/whiskies")
      .extraInfoExtractor(extraInfo =>
        List(
          extraInfo.requestName,
          s"\nResponse Body:\r\n${extraInfo.response.body.string}"
        )
      )
    )

  setUp(
    getAll.inject(atOnceUsers(1))
//      getAll.inject(constantUsersPerSec(50) during( 10 seconds))
  )

}
