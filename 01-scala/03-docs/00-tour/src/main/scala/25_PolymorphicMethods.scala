/*
Polymorphic Methods

https://docs.scala-lang.org/tour/polymorphic-methods.html

Methods in Scala can be parameterized by type as well as value.
The syntax is similar to that of generic classes.

 */

case class Bar4()
object PolymorphicMethods {
  def listOfDuplicates[A](x: A, length: Int): List[A] = {
    if (length < 1)
      List.empty
    else
      x :: listOfDuplicates(x, length - 1)
  }
  def main(args: Array[String]) = {
    println(listOfDuplicates[Int](3, 4))
    println(listOfDuplicates("La", 3))
  }
}
/*
List(3, 3, 3, 3)
List(La, La, La)
 */
