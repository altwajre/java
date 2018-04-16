package com.company.app._12CollectionsWithFunctions

object _07Zip extends App {
  val a = List(1, 2, 3, 4)
  val b = List('a', 'b', 'c', 'd')
  private val tuples: List[(Int, Char)] = a zip b
  println(tuples)
  // List((1,a), (2,b), (3,c), (4,d))

  println((1 to 5) zip ('a' to 'c'))
  // Vector((1,a), (2,b), (3,c))

}
