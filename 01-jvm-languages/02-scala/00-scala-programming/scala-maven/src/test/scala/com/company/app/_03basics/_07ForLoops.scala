package com.company.app._03basics

import org.scalatest.FunSuite

class _07ForLoops extends FunSuite {
  test("functional for - GOOD") {
    val xs = List(1, 2, 3, 4)
    val result = for (a <- xs) yield (a + 1) // for comprehensions
    println(result)
  }
  /*
  List(2, 3, 4, 5)
   */

  test("imperative for - BAD") {
    var result = ""
    for (a <- 1 to 10) {
      result += a
      if (a < 10) result += ", "
    }
    println(result)

    val xs = List(1, 2, 3, 4)
    var r = List[Int]()
    for (a <- xs) {
      r = r :+ (a + 1) // : means append
    }
    println(r)
  }
  /*
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10
  List(2, 3, 4, 5)
   */


}
