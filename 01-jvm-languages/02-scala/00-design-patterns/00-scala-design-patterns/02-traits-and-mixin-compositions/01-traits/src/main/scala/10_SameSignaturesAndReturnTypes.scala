/*
Same signatures and return types

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/a8f37c16-5620-42fb-ba9d-2cc38b911678.xhtml

Mixes both formal and informal greetings, and implement the method once.
 */
trait FormalGreeting {
  def hello(): String
}

trait InformalGreeting {
  def hello(): String
}

class Greeter extends FormalGreeting with InformalGreeting {
  override def hello(): String = "Hello, world!"
}

object GreeterUser {
  def main(args: Array[String]): Unit = {
    val greeter = new Greeter()
    println(greeter.hello())
  }
}
/*
Hello, world!
 */
