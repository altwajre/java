package com.company.app.feeder

import scala.util.Random

object EmailFeeder {
  val feeder = Iterator
    .continually(Map("email" -> (Random.alphanumeric.take(20).mkString + "@foo.com")))
}
