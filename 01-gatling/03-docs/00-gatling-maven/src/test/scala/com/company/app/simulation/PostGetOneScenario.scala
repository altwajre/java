package com.company.app.simulation

import com.company.app.UuidFeeder
import com.company.app.scenairos.{CreateWhisky, GetWhisky}
import com.company.app.util.{Environemnt, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._

class PostGetOneScenario extends Simulation {
  val httpConf = http.baseURL(Environemnt.baseURL)
    .headers(Headers.commonHeader)

  val createGetOneScenarios = List(

    scenario("Create Get All")
      .feed(UuidFeeder.feeder)
      .exec(CreateWhisky.createWhiskyHttp)
      .exec(session => {
        println("# Create Whisky")
        val id = session.get("id").asOption[String]
        println(id)

        val createWhiskyResponse = session.get("createWhiskyResponse").asOption[String]
        println(createWhiskyResponse)

        session
      })
      .exec(GetWhisky.getWhiskyHttp)
      .exec(session => {
        println("# Get Whisky")

        val id = session.get("id").asOption[String]
        println(id)

        val createWhiskyResponse = session.get("createWhiskyResponse").asOption[String]
        println(createWhiskyResponse)

        val getWhiskyResponse = session.get("getWhiskyResponse").asOption[String]
        println(getWhiskyResponse)
        session
      })
      .inject(
        atOnceUsers(3)
      )
  )

  setUp(createGetOneScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environemnt.maxResponseTime.toInt)
    )
}
