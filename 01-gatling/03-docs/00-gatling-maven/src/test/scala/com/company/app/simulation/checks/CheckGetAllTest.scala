package com.company.app.simulation.checks

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class CheckGetAllTest extends Simulation {
  val getUrl = "http://localhost:8080/api/whiskies"
  val getAll = scenario("Get All")
    .exec(http("Get whiskies")
      .get(getUrl)
      .check(
        status is 200,
        status not 404,
        status not 500,
        currentLocation is getUrl,
        //        currentLocation is getUrl + "x", // it will fail
        bodyString.saveAs("getResponseBody")
      )
    )
    .exec(session => {
      val getResponseBody = session.get("getResponseBody").asOption[String]
      println(getResponseBody)
      session
    })
    .inject(atOnceUsers(1))
//    .inject(constantUsersPerSec(50) during (10 seconds))

  setUp(
    getAll
    //      getAll.inject(constantUsersPerSec(50) during( 10 seconds))
  )

}
