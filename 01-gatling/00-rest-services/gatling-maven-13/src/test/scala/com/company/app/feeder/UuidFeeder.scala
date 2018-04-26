package com.company.app.feeder

object UuidFeeder {
  val feeder = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString()))
}
