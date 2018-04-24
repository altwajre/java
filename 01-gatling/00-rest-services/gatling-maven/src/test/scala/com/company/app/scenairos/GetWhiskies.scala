package com.company.app.scenairos

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetWhiskies {

  val getWhiskiesHttp = http("Get whiskies")
    .get("/")
    .check(
      status is 200,
      bodyString.saveAs("getWhiskiesResponse")
    )

  val getWhiskies = scenario("Get all whiskies")
    .exec(getWhiskiesHttp)
    .exec(session => {
      val getWhiskiesResponse = session.get("getWhiskiesResponse").asOption[String]
      println(getWhiskiesResponse)
      session
    })

}
