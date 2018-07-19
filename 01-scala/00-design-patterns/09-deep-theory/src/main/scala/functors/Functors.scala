package functors

/*
Functors

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/8a98fa02-df24-4f25-9dcc-3cc4ec1cf8a2.xhtml

A functor is a class that has a map method and conforms to a few laws.

- Identity: Whenever the identity function is mapped over some data, it doesn't change it, in other words, map(x)(i => i) == x.
- Composition: Multiple maps must compose together. It should make no difference if we do this operation: map(map(x)(i => y(i)))(i => z(i)) or map(x)(i => z(y(i))).
- The map function preserves the structure of the data, for example, it does not add or remove elements, change their order, and so on. It just changes the representation.
 */
object Functors {
  trait Functor[F[_]] {
    def map[T, Y](l: F[T])(f: T => Y): F[Y]
  }

  val listFunctor = new Functor[List] {
    override def map[T, Y](l: List[T])(f: T => Y): List[Y] = l.map(f)
  }
  def main(args: Array[String]) = {
    val numbers = List(1, 2, 3, 4, 5)
    val mapping = Map(
      1 -> "one",
      2 -> "two",
      3 -> "three",
      4 -> "four",
      5 -> "five"
    )

    println(s"The numbers doubled are: ${listFunctor.map(numbers)(_ * 2)}")
    println(s"The numbers with strings are: ${listFunctor.map(numbers)(i => (i, mapping(i)))}")
  }
}
/*
The numbers doubled are: List(2, 4, 6, 8, 10)
The numbers with strings are: List((1,one), (2,two), (3,three), (4,four), (5,five))
 */
