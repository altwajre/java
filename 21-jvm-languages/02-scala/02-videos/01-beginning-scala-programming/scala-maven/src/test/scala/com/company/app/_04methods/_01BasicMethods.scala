package com.company.app._04methods

import org.scalatest.FunSuite

class _01BasicMethods extends FunSuite{
  test("basic methods") {
    def add(x: Int, y: Int): Int = x + y // :Int is return type
    println(add(2, 3))
    // 5

    def numberStatus(a: Int) =
      if (a < 10) "Less than 10"
      else if (a > 10) "Greater than 10"
      else "It is 10!"
    println(numberStatus(10))
    // It is 10!

  }

}
