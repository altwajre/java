package com.company.app.simulations

import com.company.app.feeder.UuidFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class UseSavedSessionDataInNextRequest extends Simulation {

  val port: String = "8080"
  println(port)
  var myName: String = "Tom"

  val postGet: ScenarioBuilder = scenario("Post Get")
    .feed(UuidFeeder.feeder)
    .exec(http("Create whisky")
      .post(s"http://localhost:${port}/api/whiskies")
      .body(StringBody(s"""{"name": "${myName}"}"""))
      .check(
        status.is(201),
        bodyString.saveAs("postResponseBody")
      )
    )
    .exec(session => {
      val postResponseBody = session.get("postResponseBody").asOption[String].get
      println("# postResponseBody" + postResponseBody)
      // NOTE: save data in session
      session.set("request", postResponseBody)
    })
    .pause(1)
    .exec(http("Create whisky using session saved data")
      .post(s"http://localhost:${port}/api/whiskies")
      // NOTE: use saved session data
      .body(StringBody("${request}"))
      .check(
      status.is(201)
    ))

  setUp(
    postGet.inject(atOnceUsers(2))
    //      postGet.inject(constantUsersPerSec(50) during( 10 seconds))
  )

}
