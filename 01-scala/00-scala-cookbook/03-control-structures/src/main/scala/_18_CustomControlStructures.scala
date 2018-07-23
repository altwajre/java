import scala.annotation.tailrec

/*
Creating Your Own Control Structures

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s19.html

Custom while loop control structure
1, define a function named whilst that takes two parameter lists.
2, the first parameter list handles the test condition
3, the second parameter list is the block of code the user wants to run
 */
object _18_CustomControlStructures {
  def whilstWhile(testConition: => Boolean)(codeBlock: => Unit): Unit = {
    while(testConition) {
      codeBlock
    }
  }

  @tailrec
  def whilstTailrec(testCodition: => Boolean)(codeBlock: => Unit): Unit = {
    if(testCodition) {
      codeBlock
      whilstTailrec(testCodition) (codeBlock)
    }
  }
  def main(args: Array[String]): Unit = {
    whileLoop

    tailrec
  }

  private def tailrec = {
    println("# tailrec")
    var i = 0
    whilstTailrec(i < 3) {
      println(i)
      i += 1
    }
  }

  private def whileLoop = {
    println("# while loop")
    var i = 0
    whilstWhile(i < 3) {
      println(i)
      i += 1
    }
  }
}
/*
# while loop
0
1
2
# tailrec
0
1
2
 */
