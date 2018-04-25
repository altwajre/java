package com.company.app.feeder

import io.gatling.core.Predef._

object JsonUrlFeeder {
  // user.json is at resources/data/user.json
  val feeder = jsonUrl(getClass.getClassLoader.getResource("data/user.json").toString)

}
