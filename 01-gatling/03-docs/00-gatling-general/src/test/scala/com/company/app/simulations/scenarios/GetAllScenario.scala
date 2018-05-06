package com.company.app.simulations.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetAllScenario extends Simulation {

  val getOnePost = scenario("Get Fake REST API")
    .exec(http("get one post")
      .get("https://jsonplaceholder.typicode.com/posts/1")
        .check(
          status is 200,
          bodyString.saveAs("getPostResponseBody")
        )
    )
    .exec(session => {
      println(session)
      val getPostResponseBody = session.get("getPostResponseBody").asOption[String]
      println(getPostResponseBody)
      session.set("name", "Tom")
    })
    .pause(1)
    .exec(session => {
      val getPostResponseBody = session.get("getPostResponseBody").asOption[String]
      println(getPostResponseBody.get)

      val name = session.get("name").asOption[String]
      println(name.get)

      session
    })
    .inject(atOnceUsers(1))

  val getAll = scenario("Get All")
    .exec(http("Get whiskies")
      .get("http://localhost:8080/api/whiskies")
      .check(
        status is 200,
        bodyString.saveAs("getResponseBody")
      )
    )
    .exec(session => {
      println("# Get all whiskies session")
      val getResponseBody = session.get("getResponseBody").asOption[String]
      println(getResponseBody.get)
      session
    })
    .inject(atOnceUsers(1))
  //    .inject(constantUsersPerSec(50) during (10 seconds))

  setUp(
    getOnePost,
    getAll
  )

}
