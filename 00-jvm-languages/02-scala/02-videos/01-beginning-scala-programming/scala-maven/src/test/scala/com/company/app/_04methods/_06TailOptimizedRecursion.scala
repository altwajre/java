package com.company.app._04methods

import org.scalatest.FunSuite

import scala.annotation.tailrec

class _06TailOptimizedRecursion extends FunSuite{
  test("tail call recursion") {
    @tailrec
    def fact(n:BigInt, acc:BigInt):BigInt = {
      println(n + ", "+ acc)
      if(n == 0 || n == 1) acc
      else fact(n - 1, acc * n)
    }
    def factorial(n:Int) = fact(n, 1)
    println(factorial(5))
    // 120
  }

}
