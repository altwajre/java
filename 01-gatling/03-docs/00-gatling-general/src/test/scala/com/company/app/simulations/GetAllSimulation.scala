package com.company.app.simulations

import com.company.app.scenairos.GetWhiskies
import com.company.app.util.{Environemnt, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._

class GetAllSimulation extends Simulation {
  val httpConf = http
    .baseURL(Environemnt.baseURL)
    .headers(Headers.commonHeader)

  val getWhiskiesScenario = List(
    GetWhiskies.getWhiskiesScenario
      .inject(
        atOnceUsers(1)
      )
  )

  setUp(getWhiskiesScenario)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environemnt.maxResponseTime.toInt)
    )
}
