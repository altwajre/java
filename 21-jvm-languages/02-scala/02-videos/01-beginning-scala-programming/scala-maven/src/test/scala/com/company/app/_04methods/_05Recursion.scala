package com.company.app._04methods

import org.scalatest.FunSuite

class _05Recursion extends FunSuite{
  test("recursion") {
    def factorial(n:BigInt):BigInt = {
      if (n == 0 || n == 1) 1
      else n * factorial(n - 1)
    }

    println(factorial(5))
    // 120
  }

}
