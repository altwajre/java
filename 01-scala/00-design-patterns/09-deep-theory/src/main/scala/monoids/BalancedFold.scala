package monoids

/*
Monoids and parallel computations

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/444d5bb1-9c92-4bf1-ac3d-215f2d156a76.xhtml

chain multiple operations

op(op(1, 2), op(3, 4))

 */
object BalancedFold {
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
    println(s"4! is: ${MonoidOperations.balancedFold(numbers, intMultiplication)(identity)}")
  }
}
/*
4! is: 24
 */
