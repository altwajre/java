package com.company.app.scenairos

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetWhisky {
  val getWhiskyHttp = http("Get whisky")
    .get("/${id}")
    .check(
      status.is(200),
      bodyString.saveAs("getWhiskyResponse")
    )

  val getWhisky = scenario("Get whisky")
    .exec(getWhiskyHttp)
    .exec(session => {
      val getWhiskiesResponse = session.get("getWhiskyResponse").asOption[String]
      println(getWhiskiesResponse)
      session
    })
}
