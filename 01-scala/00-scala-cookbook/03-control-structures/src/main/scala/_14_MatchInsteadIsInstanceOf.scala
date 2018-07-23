/*
Using a Match Expression Instead of isInstanceOf

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s15.html
 */
object _14_MatchInsteadIsInstanceOf {
  trait SentientBeing
  trait Animal extends SentientBeing
  case class Dog(name: String) extends Animal
  case class Person(name: String, age: Int) extends SentientBeing

  def isPerson(x: Any): Boolean = x match {
    case p: Person => true
    case _ => false
  }

  def printInfo(x: SentientBeing) = x match {
    case Person(name, age) => println("Handle Person")
    case Dog(name) => println("Handle Dog")
  }

  def main(args: Array[String]): Unit = {
    println("# is an instance of a Person")
    println(s"isPerson: ${isPerson(Person("Tom", 28))}")

    println("# take different actions based on the exact subtype")
    printInfo(Person("Tommy", 18))
    printInfo(Dog("Rocky"))

  }
}
/*
true
 */
