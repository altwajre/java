package com.company.app._11Collection

object Tuples extends App {
  val t:(Int, String) = 3 -> "Three"
  println(t)
  // (3,Three)
  val t2 = 3 -> "Three"
  println(t2)
  // (3,Three)
}

object Maps extends App {
  val m = Map.apply((1, "One"), (2, "Two"), (3, "Three"))
  println(m)
  // Map(1 -> One, 2 -> Two, 3 -> Three)
  println(m.get(1))
  // Some(One)

  val m2 = Map((1, "One"), (2, "Two"), (3, "Three"))
  println(m2)
  // Map(1 -> One, 2 -> Two, 3 -> Three)
  println(m2.get(1))
  // Some(One)

  val m3 = Map(1 -> "One", 2 -> "Two", 3 -> "Three")
  println(m3.get(1))
  // Some(One)
  println(m3.apply(1))
  // One
  println(m3(1))
  // One
  println(m3.get(4))
  // None
//  println(m3(4)) // exception occurs

  println(m3.map(t => (t._1 * 2, t._2)))
  // Map(2 -> One, 4 -> Two, 6 -> Three)

  println(m3.toList)
  // List((1,One), (2,Two), (3,Three))
  println(m3.keys)
  // Set(1, 2, 3)
  println(m3.keySet)
  // Set(1, 2, 3)
  println(m3.values)
  // MapLike(One, Two, Three)
  println(m3.values.toList)
  // List(One, Two, Three)
}
