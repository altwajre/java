package com.company.app.simulation

import com.company.app.UuidFeeder
import com.company.app.scenairos.{CreateWhisky, GetWhiskies}
import com.company.app.util.{Environemnt, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._

class PostGetAllOneScenario extends Simulation {
  val httpConf = http.baseURL(Environemnt.baseURL)
    .headers(Headers.commonHeader)

  val createGetAllScenarios = List(

    scenario("Create Get All")
      .feed(UuidFeeder.feeder)
      .exec(CreateWhisky.createWhiskyHttp)
      .exec(GetWhiskies.getWhiskiesHttp)
      .exec(session => {
        val id = session.get("id").asOption[String]
        println(id)

        val createWhiskyResponse = session.get("createWhiskyResponse").asOption[String]
        println(createWhiskyResponse)

        val getWhiskiesResponse = session.get("getWhiskiesResponse").asOption[String]
        println(getWhiskiesResponse)
        session
      })
      .inject(
        atOnceUsers(2)
      )
  )

  setUp(createGetAllScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environemnt.maxResponseTime.toInt)
    )
}
