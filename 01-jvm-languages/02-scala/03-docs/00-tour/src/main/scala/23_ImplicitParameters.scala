
/*

Implicit Parameters

https://docs.scala-lang.org/tour/implicit-parameters.html

Monoids (generic)

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/400abebc-75b4-4f3f-bc72-acb5b27c2dc1.xhtml

All monoids, monads, and functors are derived from mathematics. One thing about this subject is that similarly to programming,
it tries to find abstractions. If we try to map mathematics to programming, we can think about the different datatypes
we have—Int, Double, Long, or custom datatypes. Each type can be characterized by the operations it supports and the laws of these operations,
and this is called the algebra of the type.

Now, if we think about it, we can identify the operations that are shared by multiple types, for example, addition, multiplication, subtraction,
and so on. Different types can share the same operations and they can conform to exactly the same laws.
This is something we can take advantage of because this allows us to write generic programs that apply to different types that follow some specific rules.
 */

abstract class Monoid[A] {
  def add(x: A, y: A): A
  def unit: A
}

object ImplicitParameters {
  /*
  To show how implicit parameters work, we first define monoids StringMonoid and IntMonoid for strings and integers, respectively.
  The implicit keyword indicates that the corresponding object can be used implicitly.
   */
  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def add(x: String, y: String): String = x concat y

    override def unit: String = ""
  }
  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    override def add(x: Int, y: Int): Int = x + y

    override def unit: Int = 0
  }

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A = {
    if(xs.isEmpty) m.unit
    else {
      println(s"head=${xs.head}")
      println(s"tail=${xs.tail}")
      m.add(xs.head, sum(xs.tail))
    }
  }

  def main(args: Array[String]) = {
    println(sum(List(1, 2, 3)))       // uses IntMonoid implicitly
    println(sum(List("a", "b", "c"))) // uses StringMonoid implicitly
  }
}
/*
6
abc
 */
