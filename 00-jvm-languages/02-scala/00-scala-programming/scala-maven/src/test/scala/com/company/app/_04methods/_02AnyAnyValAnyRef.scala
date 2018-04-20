package com.company.app._04methods

import org.scalatest.FunSuite

class _02AnyAnyValAnyRef extends FunSuite{
  test("types") {
    def add(x:Int, y:Int) = x + y
    def substract(x:Double, y:Double) = x - y
    println(add(substract(10, 7).round.toInt, substract(10, 5).round.toInt))
    // 8
  }
}
