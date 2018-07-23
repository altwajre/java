/*
Matching One or More Exceptions with try/catch

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s17.html
 */
object _16_MatchTryCatchExceptions {
  def main(args: Array[String]): Unit = {
    val s = "Foo"
    try {
      val i = s.toInt
    }
    catch {
      case e: Exception =>
        println("# Exception occurs:")
        e.printStackTrace
    }
  }
}
/*
java.lang.NumberFormatException: For input string: "Foo"
# Exception occurs:
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Integer.parseInt(Integer.java:580)
	at java.lang.Integer.parseInt(Integer.java:615)
	at scala.collection.immutable.StringLike.toInt(StringLike.scala:301)
	at scala.collection.immutable.StringLike.toInt$(StringLike.scala:301)
	at scala.collection.immutable.StringOps.toInt(StringOps.scala:29)
	at MatchTryCatchExceptions$.main(MatchTryCatchExceptions.scala:10)
	at MatchTryCatchExceptions.main(MatchTryCatchExceptions.scala)
 */
