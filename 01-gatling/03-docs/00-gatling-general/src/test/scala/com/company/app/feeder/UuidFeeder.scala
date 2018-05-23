package com.company.app.feeder

import java.util.UUID

object UuidFeeder {
  val feeder = Iterator.continually(
    Map(
      "uuid" -> UUID.randomUUID.toString(),
      "originUuid" -> UUID.randomUUID.toString()
    )
  )
}
