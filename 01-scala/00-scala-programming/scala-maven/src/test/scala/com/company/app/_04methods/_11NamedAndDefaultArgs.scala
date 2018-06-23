package com.company.app._04methods

import org.scalatest.FunSuite

class _11NamedAndDefaultArgs extends FunSuite{
  test("named and default arguments") {
    def processNumbers(b:Boolean = true, x:Int, y:Int) = if (b) x else y
    println(processNumbers(x = 10, y = 41))
    // 10

    def add(x:Int, y:Int = 10) = x + y
    println(add(4,3))
    // 7
    println(add(20))
    // 30
  }

}
