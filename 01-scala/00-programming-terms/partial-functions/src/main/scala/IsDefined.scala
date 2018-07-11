object isDefinedAtApp {
  // explicitly state that the function is defined when the input parameter is not zero
  val divide = new PartialFunction[Int, Int] {
    def apply(x: Int): Int = 42 / x
    def isDefinedAt(x: Int): Boolean = x != 0
  }

  def main(args: Array[String]) = {
    if(divide.isDefinedAt(1)) {
      println(divide(1))
    }

    if(divide.isDefinedAt(0)) {
      println(divide(0))
    }
    else {
      println("divide by zero is not allowed")
    }
  }
}
/*
42
divide by zero is not allowed
 */

object caseApp {
  val divide: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }
  def main(args: Array[String]) = {
    if(divide.isDefinedAt(1)) {
      println(divide(1))
    }

    if(divide.isDefinedAt(0)) {
      println(divide(0))
    }
    else {
      println("divide by zero is not allowed")
    }

  }
}
/*
42
divide by zero is not allowed
 */
