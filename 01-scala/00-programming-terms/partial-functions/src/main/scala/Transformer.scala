/*
Partial function

https://alvinalexander.com/scala/how-to-define-use-partial-functions-in-scala-syntax-examples

Transformer: (A) => B can be interpreted as a function that transforms a type A into a resulting type B
 */

object Transformer {
  val convertNumToString = new PartialFunction[Int, String] {
    val nums = Array("one", "two", "three")
    def apply(i: Int) = nums(i-1)
    def isDefinedAt(i: Int) = i > 0 && i < 4
  }
  def main(args: Array[String]): Unit = {
    if(convertNumToString.isDefinedAt(2)) {
      println(convertNumToString(2))
    }

    convertNumToString(8)

  }
}
/*
two
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 7
	at Transformer$$anon$1.apply(Transformer.scala:12)
	at Transformer$$anon$1.apply(Transformer.scala:10)
	at Transformer$.main(Transformer.scala:20)
	at Transformer.main(Transformer.scala)
 */
