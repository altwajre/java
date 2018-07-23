/*
Using Case Classes in Match Expressions

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s13.html
 */
object _12_MatchCaseClasses {
  trait Animal
  case class Dog(name: String) extends Animal
  case class Cat(name: String) extends Animal
  case object Woodpecker extends Animal

  def determineType(x: Animal): String = x match {
    case Dog(moniker) => s"Dog name: $moniker"
    case _:Cat => s"Cat (ignoring the name)"
    case Woodpecker => "A Woodpecker"
    case _ => "Unknown"
  }

  def main(args: Array[String]): Unit = {
    println(determineType(new Dog("Rocky")))
    println(determineType(new Cat("Rusty the Cat")))
    println(determineType(Woodpecker))

  }
}
/*
Dog name: Rocky
Cat (ignoring the name)
A Woodpecker
 */
