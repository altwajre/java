package com.company.app.simulation.scenarios

import com.company.app.feeder.ArrayFeeder
import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, status}

class doIfEqualsOrElse extends Simulation {
  val lambdaUser = scenario("Standard User")
    .exec(session => {
      println("# doIfEqualsOrElse likecake")
      session
    })
    .group("doIfEqualsOrElse likecake") {
      feed(ArrayFeeder.feeder.circular)
        .exec(session => {
          val likecake = session.get("likecake").asOption[Boolean]
          println(likecake)

          val name = session.get("name").asOption[String]
          println(name)
          session
        })
        .doIfEqualsOrElse(true, "${likecake}") {
          exec(http("get whiskies")
            .get("http://localhost:8080/api/whiskies")
            .check(
              status is 200
            ))
            .exec(session => {
              println("# session: true")
              session
            })
        } {
          exec(http("get whiskies")
            .get("http://localhost:8080/api/whiskies/1")
            .check(
              status is 200
            ))
            .exec(session => {
              println("# session: false")
              session
            })
        }
    }
    .pause(2)
    .exec(session => {
      println("# doIfEqualsOrElse name")
      session
    })
    .group("doIfEqualsOrElse name") {
      feed(ArrayFeeder.feeder.circular)
        .exec(session => {
          val likecake = session.get("likecake").asOption[Boolean]
          println(likecake)

          val name = session.get("name").asOption[String]
          println(name)
          session
        })
        .doIfEqualsOrElse("Tom", "${name}") {
          exec(http("get whiskies")
            .get("http://localhost:8080/api/whiskies")
            .check(
              status is 200
            ))
            .exec(session => {
              println("# session: true")
              session
            })
        } {
          exec(http("get whiskies")
            .get("http://localhost:8080/api/whiskies/1")
            .check(
              status is 200
            ))
            .exec(session => {
              println("# session: false")
              session
            })
        }
    }

  setUp(
    lambdaUser.inject(atOnceUsers(3))
  )


}
