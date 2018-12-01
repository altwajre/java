package com.company.app.feeder

import io.gatling.core.Predef._

object JsonFileFeeder {
  // user.json is at resources/data/user.json
  val feeder = jsonFile("data/user.json")
}
