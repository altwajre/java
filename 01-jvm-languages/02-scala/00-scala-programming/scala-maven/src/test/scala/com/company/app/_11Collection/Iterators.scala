package com.company.app._11Collection

object Iterators extends App {
  var it = Iterator("a", "b", "c")
  val list: List[String] = it.toList
  println(list)
  println(list.head)

  val feeder: Iterator[Map[String, String]] = UuidFeeder.feeder
  println(feeder.next().get("uuid").get)

}
