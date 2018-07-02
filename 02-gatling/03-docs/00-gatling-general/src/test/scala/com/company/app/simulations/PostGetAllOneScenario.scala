package com.company.app.simulations

import com.company.app.feeder.UuidFeeder
import com.company.app.scenairos.{CreateWhisky, GetWhiskies}
import com.company.app.util.{Environemnt, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._

class PostGetAllOneScenario extends Simulation {
  val httpConf = http.baseURL(Environemnt.baseURL)
    .headers(Headers.commonHeader)

  val createGetAllScenarios = List(

    scenario("Create Whisky")
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
      .inject(
        atOnceUsers(2)
      ),

    scenario("Get All Whiskies")
      .exec(GetWhiskies.getWhiskiesHttp)
      .exec(session => {
        println("# Get All Whiskies")
        val getWhiskiesResponse = session.get("getWhiskiesResponse").asOption[String]
        println(getWhiskiesResponse)
        session
      })
      .inject(
        nothingFor(2 seconds), // wait for create to finish
        atOnceUsers(1)
      )
  )

  setUp(createGetAllScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environemnt.maxResponseTime.toInt)
    )
}
