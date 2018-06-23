package com.company.app._11Collection

object UuidFeeder {
  val feeder = Iterator.continually(Map("uuid" -> java.util.UUID.randomUUID.toString()))
}
