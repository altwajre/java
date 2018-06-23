package abstract_types

/*
Generics

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/fe7ae0e5-ee9c-4c9b-8b72-dc3db8f7eea8.xhtml

Generics are similar to ad-hoc polymorphism

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0a26f810-f5ef-43da-a167-93806c33cdd8.xhtml

Parametric polymorphism

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/f97ca94d-452a-4813-b121-f82c0ef76819.xhtml

 */

// different numerical data types
trait Adder {
  def sum[T](a: T, b: T)(implicit numeric: Numeric[T]): T = {
    numeric.plus(a, b)
  }
}

// contain any kind of data
class Container[T](data: T) {
  def compare(other: T) = data.equals(other)
}

object Generics extends Adder {
  def main(args: Array[String]) = {
    println(s"1 + 3 = ${sum(1, 3)}")
    println(s"1.2 + 6.7 = ${sum(1.2, 6.7)}")

    val intContainer = new Container(10)
    println(s"Comparing with int: ${intContainer.compare(11)}")

    val stringContainer = new Container("some text")
    println(s"Comparing with string: ${stringContainer.compare("some text")}")
  }

}
/*
1 + 3 = 4
1.2 + 6.7 = 7.9
Comparing with int: false
Comparing with string: true
 */
