/*
https://www.youtube.com/watch?v=9qknm7YhQDk

Currying

Return function
Method takes one arg and returns a function
 */
object ReturnFunctionCurrying {
  // Return function
  def add(a: Int): Int => Int = {
    (b: Int) => a + b
  }

  def main(args: Array[String]): Unit = {
    println("# Return function")
    val addTwo = add(2)
    val twoPlusThree = addTwo(3)
    println(twoPlusThree)

    println("# assign a function to a variable")
    // assign a function to a variable
    val getStringLength: String => Int = (s: String) => s.length
    val length = getStringLength("hello, world.")
    println(length)
  }
}
/*
# Return function
5
# assign a function to a variable
13
 */
