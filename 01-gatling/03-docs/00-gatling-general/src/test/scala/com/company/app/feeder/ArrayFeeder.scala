package com.company.app.feeder

// Data driven
object ArrayFeeder {
  val feeder = Array(
    Map("uuid" -> UuidFeeder.feeder, "name" -> "Tom", "age" -> 28, "likecake" -> true),
    Map("uuid" -> UuidFeeder.feeder, "name" -> "Dick", "age" -> 38, "likecake" -> true),
    Map("uuid" -> UuidFeeder.feeder, "name" -> "Harry", "age" -> 18, "likecake" -> false)
  )
}
