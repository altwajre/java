package com.company.app._03basics

import org.scalatest.FunSuite

class _05IfElse extends FunSuite {
  test("functional if - GOOD") {
    val a = 10
    val result = if (a < 10) "Less than 10"
    else if (a > 10) "Greater than 10"
    else "It is 10!"
    println(result)
  }
  /*
  It is 10!
   */

  test("imperative if - BAD") {
    val a = 10;
    var result = ""
    if (a < 10) {
      result = "Less than 10"
    }
    else if (a > 10) {
      result = "Greater than 10"
    }
    else {
      result = "It is 10!"
    }
    println(result)
  }
  /*
  It is 10!
   */

}
