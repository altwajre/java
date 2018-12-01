/*
Declaring a Type When Creating a Collection

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s06.html
 */
object _05_DeclareType {
  trait Animal
  trait FurryAnimal extends Animal
  case class Dog(name: String) extends Animal
  case class Cat(name: String) extends Animal

  def main(args: Array[String]): Unit = {
    val numbers = List[Number](1, 2.0, 33D, 400L)
    println(s"List[Number]: $numbers")

    val x = List[AnyVal](1, 2.0, 33D, 400L)
    println(s"List[AnyVal]: $x")

    println("# Array[Animal]")
    val animals = Array[Animal](Dog("Rocky"), Cat("Felix"))
    animals.foreach(println)
  }
}
/*
List[Number]: List(1, 2.0, 33.0, 400)
List[AnyVal]: List(1, 2.0, 33.0, 400)
# Array[Animal]
Dog(Rocky)
Cat(Felix)
 */
