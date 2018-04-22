package com.company.app

object UuidFeeder {
  val feeder = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString()))
}
