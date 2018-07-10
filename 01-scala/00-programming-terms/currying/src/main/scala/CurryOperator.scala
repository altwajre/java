/*
https://lukajcb.github.io/blog/scala/2016/03/08/a-real-world-currying-example.html
 */
object CurryOperator {
  // generic function that will turn any operator into a curried function
  def curryOperator[A](operator: (A, A) => A): A => (A => A) = {
    def curry(a: A): A => A = {
      (b: A) => {
        println(s"operator: $operator, a: $a, b: $b")
        operator(a, b)
      }
    }

    curry
  }

  def add(a: Int, b: Int): Int = a + b // (Int, Int) => Int
  def multiply(a: Int, b: Int): Int = a * b // (Int, Int) => Int

  def main(args: Array[String]): Unit = {
    val addCurried = curryOperator(add)
    val addTwo = addCurried(2)
    val twoPlusThree = addTwo(3)
    println(twoPlusThree)

    val multiplyCurried = curryOperator(multiply)
    val multiplyTwo = multiplyCurried(2)
    val twoMultiplyThree = multiplyTwo(3)
    println(twoMultiplyThree)
  }
}
/*
operator: Currying$$$Lambda$1/189568618@7e0babb1, a: 2, b: 3
5
operator: Currying$$$Lambda$8/1513712028@3cb5cdba, a: 2, b: 3
6
 */
