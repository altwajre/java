package com.company.app.feeder

// Data driven
object ArrayFeeder {
  val feeder = Array(
    Map("uuid" -> UuidFeeder.feeder, "name" -> UuidFeeder.feeder, "age" -> 28, "likecake" -> true),
    Map("uuid" -> UuidFeeder.feeder, "name" -> UuidFeeder.feeder, "age" -> 38, "likecake" -> true),
    Map("uuid" -> UuidFeeder.feeder, "name" -> UuidFeeder.feeder, "age" -> 18, "likecake" -> false)
  )
}
