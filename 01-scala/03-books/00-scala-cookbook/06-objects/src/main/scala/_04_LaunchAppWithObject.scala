/*
Launching an Application with an Object

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s05.html
 */
object Hello extends App {
  println("Hello")
}
/*
Hello
 */

object Hi {
  def main(args: Array[String]): Unit = {
    println("Hi")
  }
}
/*
Hi
 */
