package com.company.app

import com.company.app.feeder.UuidFeeder
import com.company.app.scenairos.{CreateWhisky, GetWhiskies}
import com.company.app.util.{Environemnt, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._

// http.warmup() is used for warming up Gatling itself
class delayedSimulation extends Simulation {
  val httpConf = http
    .baseURL(Environemnt.baseURL)
//    .warmUp("http://localhost:8080/api/whiskies")
    .headers(Headers.commonHeader)

  val createGetAllScenarios = List(

    scenario("Warmup scenario: perform Get only to warmup server")
      .exec(http("Get whiskies")
        .get("/")
      )
      .exec(session => {
        println("# 1 Warmup")
        session
      })
      .inject(
        constantUsersPerSec(1) during (1 seconds)
      ),

    scenario("Create Whisky")
      .feed(UuidFeeder.feeder)
      .exec(CreateWhisky.createWhiskyHttp)
      .exec(session => {
        println("# 2 Create Whisky")
        val id = session.get("id").asOption[String]
        println(id)

        val createWhiskyResponse = session.get("createWhiskyResponse").asOption[String]
        println(createWhiskyResponse)

        session
      })
      .inject(
        nothingFor(2 seconds), // wait for warmup
        atOnceUsers(2)
      ),

    scenario("Get All Whiskies")
      .exec(GetWhiskies.getWhiskiesHttp)
      .exec(session => {
        println("# 3 Get All Whiskies")
        val getWhiskiesResponse = session.get("getWhiskiesResponse").asOption[String]
        println(getWhiskiesResponse)
        session
      })
      .inject(
        nothingFor(3 seconds), // wait for create
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
