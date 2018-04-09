package com.company.app._03basics

import org.scalatest.FunSuite

class _06WhileDowhile extends FunSuite{
  test("functional loop - GOOD") {
    val result = (1 to 10).reverse.mkString(", ") // makeString
    println(result)

    println((10 to 1 by -1).mkString(", "))
  }
  /*
  10, 9, 8, 7, 6, 5, 4, 3, 2, 1
  10, 9, 8, 7, 6, 5, 4, 3, 2, 1
   */

  test("imperative while; do-while - BAD") {
    var a = 10
    var result = ""
    while(a > 0) {
      result = result + a
      if(a > 1)result += ", "
      a = a - 1
    }
    println(result)

    // do-while
    var x = 10
    var r = ""
    do {
      r += x
      if (x > 1) r += ", "
      x -= 1
    } while(x > 0)
    println(r)

  }
  /*
  10, 9, 8, 7, 6, 5, 4, 3, 2, 1
  10, 9, 8, 7, 6, 5, 4, 3, 2, 1
   */

}
