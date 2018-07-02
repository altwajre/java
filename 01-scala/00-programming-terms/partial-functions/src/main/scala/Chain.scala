/*

orElse and andThen

We can chain partial functions together.
 */

object Chain {
  // converts 1 to "one", etc., up to 5
  val convert1to5 = new PartialFunction[Int, String] {
    val nums = Array("one", "two", "three", "four", "five")
    def apply(i: Int) = nums(i-1)
    def isDefinedAt(i: Int) = i > 0 && i < 6
  }

  // converts 6 to "six", etc., up to 10
  val convert6to10 = new PartialFunction[Int, String] {
    val nums = Array("six", "seven", "eight", "nine", "ten")
    def apply(i: Int) = nums(i-6)
    def isDefinedAt(i: Int) = i > 5 && i < 11
  }

  def main(args: Array[String]): Unit = {
//    val handle1to10 = convert1to5.orElse(convert6to10)
    val handle1to10 = convert1to5 orElse convert6to10
    if(handle1to10.isDefinedAt(3)) {
      println(handle1to10(3))
    }

    handle1to10(18)
  }
}
/*
three
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 12
	at Chain$$anon$2.apply(Chain.scala:19)
	at Chain$$anon$2.apply(Chain.scala:17)
	at scala.PartialFunction.applyOrElse(PartialFunction.scala:123)
	at scala.PartialFunction.applyOrElse$(PartialFunction.scala:122)
	at Chain$$anon$1.applyOrElse(Chain.scala:10)
	at scala.PartialFunction$OrElse.apply(PartialFunction.scala:168)
	at Chain$.main(Chain.scala:30)
	at Chain.main(Chain.scala)
 */
