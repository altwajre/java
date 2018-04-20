package com.company.app._04methods

import org.scalatest.FunSuite

class _03DiffReturnTypes extends FunSuite {
  test("different return types") {
    def add(x: Int, y: Int) = {
      if (x > 10) (x + y).toString() // returns string
      else x + y // returns Int
    }

    println(add(2, 3))
    // 5

    val str = add(13, 8)
    println(str.getClass)
    // class java.lang.String
    println(str)
    // 10
  }

}
