/*
Assigning the Result of a Match Expression to a Variable

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s10.html
 */
object _09_AssignMatchResultToVar {
  def isTrue(a: Any) = a match {
    case 0 | "" => false
    case _ => true
  }
  def main(args: Array[String]): Unit = {
    val someNumber = 3
    val evenOrOdd = someNumber match {
      case 1 | 3 | 5 => println("odd")
      case 2 | 4 => println("even")
    }
    evenOrOdd

    println("# method")
    println(isTrue(0))
  }

}
/*
odd
# method
false
 */
