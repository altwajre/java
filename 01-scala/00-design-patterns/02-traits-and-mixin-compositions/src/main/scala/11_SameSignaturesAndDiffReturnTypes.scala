/*
Same signatures and different return types traits

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/a03c9107-7b5d-422d-a9c3-8ff9c33ddd59.xhtml

The compiler will not allow us the different return types for getTime()
 */
trait Greeting1 {
  def getTime(): String
}
trait Greeting2 {
  def getTime(): Int
}

//class Greeter1 extends Greeting1 with Greeting2 {
//  override def getTime(): Int = ???
//}
