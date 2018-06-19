package polymorphism

/*
Ad hoc polymorphism

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0a26f810-f5ef-43da-a167-93806c33cdd8.xhtml

It allows us to extend our code without modifying the base classes. This is useful for external libraries.
It is powerful and is evaluated in compile time, which makes sure the program will work as expected.
It allows us to provide function definitions for types that we have no access to.
 */
trait Adder[T] {
  def sum(a: T, b: T): T
}

object Adder {
  def sum[T: Adder](a: T, b: T): T = implicitly[Adder[T]].sum(a, b)

  implicit val intAdder: Adder[Int] = new Adder[Int] {
    override def sum(a: Int, b: Int): Int = a + b
  }

  // same implementation as above, but allowed when the trait has a single method
  implicit val stringAdder: Adder[String] = (a: String, b: String) => s"$a concatenated with $b"
}

object AdhocPolymorphism {
  import Adder._
  def main(args: Array[String]) = {
    println(s"The sum of 1 + 2 is ${sum(1, 2)}")
    println(s"The of abc + def is ${sum("abc", "def")}")
  }

}
/*
The sum of 1 + 2 is 3
The of abc + def is abc concatenated with def
 */
