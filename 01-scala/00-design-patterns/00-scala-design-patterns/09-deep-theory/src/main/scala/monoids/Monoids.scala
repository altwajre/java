package monoids

/*
Monoids

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/400abebc-75b4-4f3f-bc72-acb5b27c2dc1.xhtml

Monoids can be naturally used with lists and collections.

- reduce and fold
https://www.youtube.com/watch?v=bnOTEfNEQzw

 */

object Monoids {

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
  }

  def main(args: Array[String]) = {
    val numbers = List(1, 2, 3, 4, 5)
    println("# reduceLeft - input and return types need to be the same")
    println(numbers.reduceLeft((a, b) => {
      println(a + "," + b)
      a + b
    }))

    println("# foldLeft - input and return types are the same")
    println(numbers.foldLeft(0)(_ + _))

    println("# foldLeft - input and return types can be different")
    // input type is Int, return type is String
    println(numbers.foldLeft("")(_ + _ + ","))

    println("# foldLeft - Monoids addition")
    println(numbers.foldLeft(intAddition.zero)(intAddition.op))

    println(MonoidOperations.fold(numbers, intAddition))
  }
}
/*
# reduceLeft - input and return types need to be the same
1,2
3,3
6,4
10,5
15
# foldLeft - input and return types are the same
15
# foldLeft - input and return types can be different
1,2,3,4,5,
# foldLeft - Monoids addition
15
15
 */
