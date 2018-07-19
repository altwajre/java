package monoids

import monoids.BalancedFold.Monoid

/*
Monoids and composition

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/38f498ab-94ea-42ef-8aa1-54e3e7171de0.xhtml

Monoids support composition; if A and B are monoids, then their product (A, B) is also a monoid.
 */
object Composition {
  trait Monoid[T] {
    def op(l: T, r: T): T

    def zero: T
  }

  val intAddition: Monoid[Int] = new Monoid[Int] {
    override def op(l: Int, r: Int): Int = l + r

    override def zero: Int = 0
  }

  val intMultiplication: Monoid[Int] = new Monoid[Int] {
    val zero: Int = 1

    override def op(l: Int, r: Int): Int = l * r
  }

  val stringConcatenation: Monoid[String] = new Monoid[String] {
    val zero: String = ""

    override def op(l: String, r: String): String = l + r
  }

  def compose[T, Y](a: Monoid[T], b: Monoid[Y]): Monoid[(T, Y)] = {
    new Monoid[(T, Y)] {
      val zero: (T, Y) = (a.zero, b.zero)

      override def op(l: (T, Y), r: (T, Y)): (T, Y) = (a.op(l._1, r._1), b.op(l._2, r._2))
    }
  }

  object MonoidOperations {
    def fold[T](list: List[T], m: Monoid[T]): T = list.foldLeft(m.zero)(m.op)
    def balancedFold[T, Y](list: IndexedSeq[T], m: Monoid[Y])(f: T => Y): Y = {
      if(list.length == 0) {
        m.zero
      }
      else if(list.length == 1) {
        f(list(0))
      }
      else {
        val (left, right) = list.splitAt(list.length / 2)
        m.op(balancedFold(left, m)(f), balancedFold(right, m)(f))
      }
    }
  }

  def main(args: Array[String]) = {
    val numbers = Array(1, 2, 3, 4)
    // (1+2+3+4=10, 1*2*3*4=24)
    val sumAndProduct = compose(intAddition, intMultiplication)
    println(s"The sum and product is: ${MonoidOperations.balancedFold(numbers, sumAndProduct)(i => (i, i))}")
  }
}
/*
The sum and product is: (10,24)
 */
