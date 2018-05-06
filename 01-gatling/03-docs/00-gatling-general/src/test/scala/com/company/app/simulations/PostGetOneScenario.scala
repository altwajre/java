package com.company.app.simulations

import com.company.app.feeder.UuidFeeder
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

        println("# before get uuid")
        val uuid = session.get("uuid").asOption[String]
        println(uuid)
        println("# after get uuid")

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
      global.responseTime.max.lt(Environemnt.maxResponseTime.toInt)
    )
}
