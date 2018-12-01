package com.company.app.util

object Environemnt {
  val baseURL = scala.util.Properties.envOrElse("baseURL", "http://localhost:8080/api/whiskies")
  val users = scala.util.Properties.envOrElse("numberOfUsers", "5000")
  val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "5000") // in milliseconds

}
