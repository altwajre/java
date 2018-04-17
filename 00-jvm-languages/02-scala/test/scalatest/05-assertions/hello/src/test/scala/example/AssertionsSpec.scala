package example

import org.scalatest.FlatSpec

class AssertionsSpec extends FlatSpec {
  "left" should "equal right" in {
    val left = 1
    val right = 1
    assert(left == right)
  }

  "lets" should "test something" in {
    val a = 1
    val b = 2
    val c = 3
    val d = 4
    assert(a == b || c >= d)
  }
}
