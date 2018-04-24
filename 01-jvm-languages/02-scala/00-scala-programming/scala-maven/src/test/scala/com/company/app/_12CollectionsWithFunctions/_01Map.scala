package com.company.app._12CollectionsWithFunctions

object MapFunction extends App {
  val a = List(1,2,3)
  val f = (x:Int) => x * 2
  println(a.map(f))
  // List(2, 4, 6)
  println(a.map(x => x * 2))
  // List(2, 4, 6)

  val b = Set("Brown", "Red", "Green")
  println(b.map(s => s.size))
  // Set(5, 3)
  println(b.map(s => (s, s.size)))
  // Set((Brown,5), (Red,3), (Green,5))

  val fifaMensChamps = Map('Germany -> 4, 'Brazil -> 5, 'Italy -> 4)
  println(fifaMensChamps.map(t => (Symbol.apply("Team " + t._1.name),t._2)))
  // Map('Team Germany -> 4, 'Team Brazil -> 5, 'Team Italy -> 4)
}
