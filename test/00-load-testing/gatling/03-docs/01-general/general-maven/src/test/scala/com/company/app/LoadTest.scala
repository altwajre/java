package com.company.app

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadTest extends Simulation {


  setUp(
  )

  private def randomName(): String = {
    UUID.randomUUID().toString
  }
}
