package com.company.app.simulation.simulation

import com.company.app.scenairos.{CreateWhisky, GetWhiskies}
import com.company.app.util.{Environemnt, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._

// Post two whiskies and Get all whiskies
class PostTwoGetAll extends Simulation {

  val httpConf = http
    .baseURL(Environemnt.baseURL)
    .headers(Headers.commonHeader)

  val postWhiskyScenario = CreateWhisky.createWhiskyScenario

  val getWhiskiesScenario = GetWhiskies.getWhiskiesScenario

  setUp(
    postWhiskyScenario.inject(atOnceUsers(2)), // Post two whiskies
    getWhiskiesScenario.inject(atOnceUsers(1))
  )
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lessThan(Environemnt.maxResponseTime.toInt)
    )

}
