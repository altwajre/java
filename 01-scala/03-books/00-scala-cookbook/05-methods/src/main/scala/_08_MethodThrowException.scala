/*
Declaring That a Method Can Throw an Exception

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s09.html
 */
object _08_MethodThrowException {
  @throws(classOf[Exception])
  def play: Unit = {
    println("play method")
    throw new Exception
  }
  def main(args: Array[String]): Unit = {
    play
  }
}
/*
Exception in thread "main" java.lang.Exception
	at MethodThrowException$.play(MethodThrowException.scala:10)
play method
	at MethodThrowException$.main(MethodThrowException.scala:13)
	at MethodThrowException.main(MethodThrowException.scala)
 */
