package com.company.app.scenairos

import com.company.app.feeder.UuidFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CreateWhisky {

  private val requestBody =
    s"""
       |{
       |  "name": "$${uuid}",
       |  "origin": "Scotland"
       |}
    """.stripMargin
  val createWhiskyHttp = http("Create whisky")
    .post(s"/")
    .body(StringBody(requestBody)).asJSON
    .check(
      status.is(201),
      jsonPath("$..id").find.saveAs("id"),
      bodyString.saveAs("createWhiskyResponse"))

  val createWhiskyScenario = scenario(" POST whisky")
    .feed(UuidFeeder.feeder)
    .exec(createWhiskyHttp)

}
