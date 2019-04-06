package com.company.app.manual

import com.company.app.feeder.ArrayFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RunEveryTwoSeconds extends Simulation {
  val lambdaUser = scenario("Standard User")
    .exec(session => {
      println("# run every 2 seconds")
      session
    })
//    .forever {
//      pace(2 seconds)
//        .feed(ArrayFeeder.feeder.circular)
//        .exec(http("get whiskies")
//          .get("http://localhost:8080/api/whiskies")
//          .check(
//            status is 200
//          ))
//    }
      .repeat(3) {
        pace(2 seconds)
          .feed(ArrayFeeder.feeder.circular)
          .exec(http("get whiskies")
            .get("http://localhost:8080/api/whiskies")
            .check(
              status is 200
            ))
      }

  setUp(
    lambdaUser.inject(atOnceUsers(2))
  )

}
