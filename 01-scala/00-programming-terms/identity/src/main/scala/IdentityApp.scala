
/*
identity

https://stackoverflow.com/questions/28407482/what-does-predef-identity-do-in-scala

identity equals x => x simply returns its argument.
For example:
List(1, 2, 3).map(identity) returns List(1, 2, 3) <- identity is 1, 2, 3
List(1, 2, 3).map(x => x)   returns List(1, 2, 3)
 */
object IdentityApp {

  def id[A](x : A) : A = { x }

  def squareIf(test: Boolean): List[Int] = List(1, 2, 3).map(if (test) x => x * x else identity)

  def main(args: Array[String]): Unit = {
    println("# identity")
    println(List(1, 2, 3).map(identity))

    println("# id")
    println(List(1, 2, 3).map(id))

    println("# x => x")
    println(List(1, 2, 3).map(x => x))

    println("# x => x * 2")
    println(List(1, 2, 3).map(x => x * 2))

    println("# squareIf(true)")
    println(squareIf(true))
    println("# squareIf(false)")
    println(squareIf(false))
  }
}
/*
# identity
List(1, 2, 3)
# id
List(1, 2, 3)
# x => x
List(1, 2, 3)
# x => x * 2
List(2, 4, 6)
# squareIf(true)
List(1, 4, 9)
# squareIf(false)
List(1, 2, 3)
 */
